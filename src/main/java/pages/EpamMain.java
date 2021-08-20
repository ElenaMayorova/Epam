package pages;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static webFactory.BaseHooks.getClickableElement;

public class EpamMain extends AbstractPage {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(EpamMain.class);

    // локаторы
  private final By EVENTS = By.xpath("//a[@class='nav-link' and @href='/events']");

    public EpamMain(WebDriver driver) {
        super(driver);
    }
//Открываем сайт https://events.epam.com/
    public EpamMain open() {
        driver.get(config.epamUrl());
        logger.info("Запущен сайт", config.epamUrl());
        return this;
    }
//Переход на вклалку Events
    public void openEvents() {
        getClickableElement(EVENTS).sendKeys(Keys.ENTER);
        logger.info("Перешли на вклалку Events");
    }
}

