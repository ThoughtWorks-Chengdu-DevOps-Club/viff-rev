package io.viff.comparator.config;

import io.viff.comparator.endpoints.ComparatorEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ComparatorEndpoint.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}
