package com.hasht.processor.spring;

import com.hasht.processor.core.RequestHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
public class HandlerConfiguration implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof GenericApplicationContext context) {
            final ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context);
            final List<String> packageNames = context.getBeansWithAnnotation(EnableProcessor.class)
                    .values().stream().map(Object::getClass)
                    .map(ClassUtils::getUserClass)
                    .map(clazz -> {
                        final EnableProcessor annotation = clazz.getAnnotation(EnableProcessor.class);
                        if (annotation != null && annotation.value().length > 0) {
                            return Arrays.asList(annotation.value());
                        }
                        return Collections.singletonList(clazz.getPackage().getName());
                    }).flatMap(Collection::stream)
                    .distinct()
                    .toList();
            scanner.addIncludeFilter(new AssignableTypeFilter(RequestHandler.class));
            scanner.scan(StringUtils.toStringArray(packageNames));
        }

    }

}
