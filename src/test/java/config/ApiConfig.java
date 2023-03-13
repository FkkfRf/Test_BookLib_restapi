package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/api.properies"
})

public interface ApiConfig extends Config {
    @Key("baseUri")
    String baseUri();
}
