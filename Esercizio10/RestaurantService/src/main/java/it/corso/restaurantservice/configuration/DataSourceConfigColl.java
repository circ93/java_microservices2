package it.corso.restaurantservice.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("coll")
public class DataSourceConfigColl {

    public DataSourceConfigColl() {

        System.out.println("Into DataSourceConfigColl class");

    }
}
