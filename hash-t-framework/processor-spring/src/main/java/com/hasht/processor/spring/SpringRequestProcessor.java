package com.hasht.processor.spring;

import com.hasht.processor.core.AbstractRequestProcessor;
import com.hasht.processor.core.DouplicatedHandlerException;
import com.hasht.processor.core.Request;
import com.hasht.processor.core.RequestHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class SpringRequestProcessor extends AbstractRequestProcessor {

    private final Map<Class<?>, RequestHandler<?>> handlerCache = new HashMap<>();

    private final ApplicationContext context;

    public SpringRequestProcessor(ApplicationContext context) {
        this.context = context;
    }

    @Override
    protected <T, R extends Request<T>> RequestHandler<R> getHandler(R request) {
        if (handlerCache.containsKey(request.getClass())) {
            return (RequestHandler<R>) handlerCache.get(request.getClass());
        }
        final RequestHandler<R> handler = (RequestHandler<R>) Arrays.stream(
                        context.getBeanNamesForType(
                                ResolvableType.forType(ParameterizedTypeReference.forType(getHandlerType(request)))))
                .map(context::getBean)
                .reduce((o, o2) -> {throw new DouplicatedHandlerException(request.getClass());})
                .orElseThrow(() -> new NoHandlerFoundException(request.getClass()));
        handlerCache.put(request.getClass(), handler);
        return handler;
    }

    private <T, R extends Request<T>> ParameterizedType getHandlerType(R request) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { request.getClass() };
            }

            @Override
            public Type getRawType() {
                return RequestHandler.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

}
