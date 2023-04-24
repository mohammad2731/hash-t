Processor is a handler provider for each request. Spring integration uses Spring functionality to scan the class-path to
find the request-handlers.
Based on a class which is annotated by `@EnableProcessor`, Spring will scan class-path to find class which
implement `RequestHandler`. Default base-package is the package of the class annotated by `@EnableProcessor`. Value of
@EnableProcessor overrides the default value of base-packages for scan.

note: requestHandler's scope would be `singleton`

For more information please look at samples and tests.
