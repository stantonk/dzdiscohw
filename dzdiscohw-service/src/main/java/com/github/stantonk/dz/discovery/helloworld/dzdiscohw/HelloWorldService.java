package com.github.stantonk.dz.discovery.helloworld.dzdiscohw;

import io.dropwizard.Application;
import io.dropwizard.discovery.DiscoveryBundle;
import io.dropwizard.discovery.DiscoveryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.github.stantonk.dz.discovery.helloworld.dzdiscohw.conf.HelloWorldConfiguration;
import com.github.stantonk.dz.discovery.helloworld.dzdiscohw.health.TemplateHealthCheck;
import com.github.stantonk.dz.discovery.helloworld.dzdiscohw.resource.HelloWorldResource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pnajda
 */
public class HelloWorldService extends Application<HelloWorldConfiguration> {

    private final DiscoveryBundle<HelloWorldConfiguration> discoveryBundle = new DiscoveryBundle<HelloWorldConfiguration>(){

        @Override
        public DiscoveryFactory getDiscoveryFactory(HelloWorldConfiguration configuration) {
            return configuration.getDiscoveryFactory();
        }

    };

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        super.initialize(bootstrap);
        bootstrap.addBundle(discoveryBundle);
    }

    public static void main(String args[]) throws Exception {
        new HelloWorldService().run(args);
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {

        final String template = configuration.getTemplate();
        final String defaultName = configuration.getDefaultName();
        environment.healthChecks().register("example health check", new TemplateHealthCheck(template));
        environment.jersey().register(new HelloWorldResource(template, defaultName));
    }

}
