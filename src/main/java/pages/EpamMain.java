package pages;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class EpamMain extends AbstractPage {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(EpamMain.class);

    public EpamMain(WebDriver driver) {
        super(driver);
    }

    public EpamMain open() {
        driver.get(config.epamUrl());
        logger.info("Запущен сайт", config.epamUrl());
        return this;
    }
}

