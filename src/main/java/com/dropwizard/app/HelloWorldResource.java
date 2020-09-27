package com.dropwizard.app;


import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;


    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}