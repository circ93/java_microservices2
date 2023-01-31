package it.corso.restaurantservice.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"prod","dev"})
public class DataSourceConfigMix {

    public DataSourceConfigMix() {

        System.out.println("Into DataSourceConfigMix class");

    }
}
