package uk.co.lucasweb.example;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * @author Richard Lucas
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Swarm container = new Swarm(args);
        container.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class, "swarm-485-example.war");
        deployment.addAsLibrary(container.createDefaultDeployment());
        deployment.setContextRoot("/swarm-485-example");
        deployment.addAllDependencies();
        container.deploy(deployment);
    }
}
