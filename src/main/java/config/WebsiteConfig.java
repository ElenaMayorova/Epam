package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:websites_config.properties")
public interface WebsiteConfig extends Config {
    @Key("epam_url")
    String epamUrl();

    @Key("browser")
    String browser();

}