package uk.co.lucasweb.example;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * @author Richard Lucas
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Container container = new Container();
        container.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class, "swarm-485-example.war");
        deployment.addAsLibrary(container.createDefaultDeployment());
        deployment.setContextRoot("/swarm-485-example");
        deployment.addAllDependencies();

        // start workaround for swarm-485
//        deployment.addDependency("com.fasterxml.jackson.module:jackson-module-jaxb-annotations:2.5.4");
//        deployment.addDependency("com.fasterxml.jackson.jaxrs:jackson-jaxrs-base:2.5.4");
//        deployment.addDependency("com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:2.5.4");
        // end workaround for swarm-485

        container.deploy(deployment);
    }
}
