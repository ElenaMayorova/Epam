package pages;

import config.WebsiteConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;

import static webFactory.BaseHooks.getClickableElement;
import static webFactory.BaseHooks.waitInvisibleElement;

public class EpamMain extends AbstractPage {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(EpamMain.class);

    // локаторы
    private final By EVENTS = By.xpath("//*[@href='/events']");
    private final By VIDEO = By.xpath("//*[@href='/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']");
    private final By ACCEPTCOOKIS = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    private final By LOADER = By.xpath("//*[contains(@class, 'evnt-global-loader')]");

    public EpamMain(WebDriver driver) {
        super(driver);
    }

    //Открываем сайт https://events.epam.com/
    @Step("Открытие сайта EpamEvents")
    @DisplayName("Открытие сайта Epam")
    @Description("Открытие сайта Epam, принимаем cookis")
    public EpamMain open() {
        driver.get(config.epamUrl());
        logger.info("Запущен сайт", config.epamUrl());
        getClickableElement(ACCEPTCOOKIS).sendKeys(Keys.ENTER);
        Allure.addAttachment("Открываем сайт Epam", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        return this;
    }

    //Переход на вклалку Events
    @Step("Переход на вкладку Events")
    @DisplayName("Переход на вкладку Events")
    public void openEvents() {
        waitInvisibleElement(LOADER);
        getClickableElement(EVENTS).sendKeys(Keys.ENTER);
        logger.info("Перешли на вклалку Events");
        Allure.addAttachment("Переход на вкладку Events", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    //Переход на вклалку Video
    @Step("Переход на вкладку Video")
    @DisplayName("Переход на вкладку Video")
    public void openVideo() {
        waitInvisibleElement(LOADER);
        getClickableElement(VIDEO).sendKeys(Keys.ENTER);
        logger.info("Перешли на вклалку Video");
        Allure.addAttachment("Переход на вкладку Events", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        waitInvisibleElement(LOADER);
    }
}

