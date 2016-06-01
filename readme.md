# Swarm-485 Example

A simple JAX-RS application built using Wildfly Swarm 1.0.0.CR1 that demonstrates [swarm-485](https://issues.jboss.org/browse/SWARM-485) 
 

### Build

```
mvn clean package
```

### Run

 ```
java -jar target/swarm-485-example-1.0-SNAPSHOT-swarm.jar 
```

### Known Issues

The application should start successfully but fails with the following error:

```
2016-06-01 11:05:00,620 ERROR [stderr] (main) Caused by: org.wildfly.swarm.container.DeploymentException: {"WFLYCTL0080: Failed services" => {"jboss.undertow.deployment.default-server.default-host./swarm-485-example" => "org.jboss.msc.service.StartException in service jboss.undertow.deployment.default-server.default-host./swarm-485-example: java.lang.NoClassDefFoundError: Failed to link uk/co/lucasweb/example/CustomJsonProvider (Module \"deployment.swarm-485-example.war:main\" from Service Module Loader): com/fasterxml/jackson/jaxrs/json/JacksonJaxbJsonProvider
2016-06-01 11:05:00,620 ERROR [stderr] (main)     Caused by: java.lang.NoClassDefFoundError: Failed to link uk/co/lucasweb/example/CustomJsonProvider (Module \"deployment.swarm-485-example.war:main\" from Service Module Loader): com/fasterxml/jackson/jaxrs/json/JacksonJaxbJsonProvider"}}
```
