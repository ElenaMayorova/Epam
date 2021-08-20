package pages;

import config.WebsiteConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static webFactory.BaseHooks.getClickableElement;

public class Events extends EpamMain {
    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(Events.class);
    // локаторы
    private final By UPCOMINGEVENTS = By.xpath("//span[contains(text(),'Upcoming events')]");
    private final By UPCOMINGEVENTSCOUNT = By.xpath("//span[contains(@class,'evnt-tab-counter evnt-label small white')]");
    private final By COUNTCARD = By.cssSelector("div.evnt-event-card");
    private final By ACCEPTCOOKIS = By.xpath("//button[@id='onetrust-accept-btn-handler']");

    public Events(WebDriver driver) {
        super(driver);
    }

    // Открытие предстоящих мероприятий
    public void openUpcomingEvents() {
        getClickableElement(ACCEPTCOOKIS).sendKeys(Keys.ENTER);
        getClickableElement(UPCOMINGEVENTS).click();
        logger.info("Перешли на будущие мероприятия");
    }

    //смотрим сколько мероприятий на кнопке Upcoming Events
    public String getUpcomingEventsCount() {
        return getClickableElement(UPCOMINGEVENTSCOUNT).getText();
    }

    public Integer getEventsCardsCount() {
        return driver.findElements(COUNTCARD).size();
    }


//    Количество карточек равно счетчику на кнопке Upcoming Events

    public void assertUpcomingEvents() {
        Assertions.assertEquals(getUpcomingEventsCount(),
                String.valueOf(getEventsCardsCount()));
        logger.info("выполнена проверка количества карточек, оно равно счетчику на кнопке");

    }
}
