package pages;

import config.WebsiteConfig;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static webFactory.BaseHooks.getClickableElement;
import static webFactory.BaseHooks.getVisibilityElement;

public class Events extends EpamMain {

    private final WebsiteConfig config = ConfigFactory.create(WebsiteConfig.class);
    private static final Logger logger = LogManager.getLogger(Events.class);
    protected SoftAssertions softAssertions;
    // локаторы
    private final By UPCOMINGEVENTS = By.xpath("//span[contains(text(),'Upcoming events')]");
    private final By PASTGEVENTS = By.xpath("//span[contains(text(),'Past Events')]");
    private final By UPCOMINGEVENTSCOUNT = By.xpath("//span[contains(@class,'evnt-tab-counter evnt-label small white')]");
    private final By COUNTCARD = By.cssSelector("div.evnt-event-card");
    private final By FIRSTCARD = By.xpath("/html/body/div[2]/div[1]/main/section[3]/div/div/div[2]/div/div/div/div[1]/div/a");
    private final By EVENTLANGUAGE = By.xpath("//p[contains(@class,'language')]/span");
    private final By EVENTNAME = By.xpath("//div[contains(@class,'evnt-event-name')]//h1");
    private final By EVENTDATE = By.xpath("//div[contains(@class,'evnt-event-dates')]//span[contains(@class,'date')]");
    private final By REGISTRATIONINFO = By.xpath("//*[@class=\"evnt-dates-cell dates\"]//span[@class=\"status reg-close\"]");
    private final By SPEAKERS = By.xpath("//*[@class=\"speakers-wrapper\"]");

    public Events(WebDriver driver) {
        super(driver);
    }

    // Открытие предстоящих мероприятий
    @Step("Upcoming events")
    @Feature("Просмотр предстоящих мероприятий")
    @DisplayName("Переход на вкладку предстоящих мероприятий")
    public void openUpcomingEvents() {
        getClickableElement(UPCOMINGEVENTS).click();
        logger.info("Перешли на будущие мероприятия");
        Allure.addAttachment("Просмотр предстоящих мероприятий", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

    }

    // Открытие прошедших мероприятий
    @Step("Past events")
    @Feature("Просмотр прошедших мероприятий")
    @DisplayName("Переход на вкладку прошедших мероприятий")
    public void openPastEvents() {
        getClickableElement(PASTGEVENTS).click();
        logger.info("Перешли на прошлые мероприятия");
        Allure.addAttachment("Просмотр прошедших мероприятий", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

    }

    //Смотрим сколько мероприятий на кнопке Upcoming Events
    public String getUpcomingEventsCount() {
        logger.info("Cмотрим сколько мероприятий на кнопке Upcoming Events");
        return getClickableElement(UPCOMINGEVENTSCOUNT).getText();
    }

    //На странице отображаются карточки  мероприятий.
    @Step("Наличие карточек на ЭФ")
    @DisplayName("Наличие карточек на ЭФ при переходе на прошедшие/будущие мероприятия")
    public void getCards() {
        Assertions.assertNotEquals(driver.findElements(COUNTCARD).size(), 0);
        logger.info("На странице отображаются карточки  мероприятий");
    }

    //проверяем сколько карточек на ЭФ
    public Integer getEventsCardsCount() {
        logger.info("Смотрим сколько карточек отображается на ЭФ");
        return driver.findElements(COUNTCARD).size();
    }


    //    Количество карточек равно счетчику на кнопке Upcoming Events
    @Step("Проверка количества карточек на ЭФ и на элементе")
    @Feature("Просмотр предстоящих мероприятий")
    @DisplayName("Выполняем проверку количества карточек, оно равно счетчику на кнопке")
    public void assertUpcomingEvents() {
        Assertions.assertEquals(getUpcomingEventsCount(),
                String.valueOf(getEventsCardsCount()));
        logger.info("Выполнена проверка количества карточек, оно равно счетчику на кнопке");
    }

    //Выбираем первую карточку прошедших мероприятий
    public void selectFirstcard() {
        getClickableElement(FIRSTCARD);
        logger.info("Выбрали в списке первую карточку");
    }

    //    Язык мероприятия
    @Step("Язык мероприятия")
    @DisplayName("Проверяем,что язык мероприятия не пустой")
    public String checkLanguage() {
        return getVisibilityElement(EVENTLANGUAGE).getText();
    }

    //Названия мероприятия
    @Step("Названия мероприятия")
    @DisplayName("Проверяем,что названия мероприятия не пустой")
    public String checkNameOfEvent() {

        return getVisibilityElement(EVENTNAME).getText();
    }

    //Дата мероприятия
    @Step("Дата мероприятия")
    @DisplayName("Проверяем,что дата мероприятия не пустой")
    public String checkDateOfEvent() {
        return getVisibilityElement(EVENTDATE).getText();
    }

    //Информация о регистрации
    @Step("Информация о регистрации")
    @DisplayName("Проверяем,что информация о регистрации не пустая")
    public String checkRegistrationInfo() {
        return getVisibilityElement(REGISTRATIONINFO).getText();
    }

    //Спикеры
    @Step("Информация о спикерах")
    @DisplayName("Проверяем,что информация о спикерах не пустая")
    public String checkSpeaker() {
        return getVisibilityElement(SPEAKERS).getText();
    }

    //Проверка полей карточки
    @Step("Проверка полей карточки")
    @Feature("Просмотр прошедших мероприятий")
    @DisplayName("Проверка  заполнения полей:язык, название, дата. регистрация.сискеры в карточке")
    public void checkCard() {
        selectFirstcard();
        Assert.assertNotEquals(checkLanguage(), "");
        logger.info("выполнена проверка наличия языка мероприятия     " + checkLanguage());
        Assert.assertNotEquals(checkNameOfEvent(), "");
        logger.info("выполнена проверка наличия названия мероприятия    " + checkNameOfEvent());
        Assert.assertNotEquals(checkDateOfEvent(), "");
        logger.info("выполнена проверка наличия даты мероприятия    " + checkDateOfEvent());
        Assert.assertNotEquals(checkRegistrationInfo(), "");
        logger.info("выполнена проверка наличия информации регистрации мероприятия     " + checkRegistrationInfo());
        Assert.assertNotEquals(checkSpeaker(), "");
        logger.info("выполнена проверка наличия спикеров мероприятия    " + checkSpeaker());
        Allure.addAttachment("Проверка полей карточки", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Получение датыа мероприятия первой карточки")
    @DisplayName("Получение датыа мероприятия первой карточки")
    public String getCardDate() {
        logger.info(getVisibilityElement(EVENTDATE).getText());
        return getVisibilityElement(EVENTDATE).getText();
    }

        @Step("Преобразование второй даты,если диапазон")
    public Date getDateEnd(String dateString) {
        Date dateEnd = null;
        String dateSgtingEnd = null;
        if (dateString.contains("-")) {
            dateSgtingEnd = dateString.split("-")[1];
        }
        try {

            dateEnd = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(dateSgtingEnd);
        } catch (ParseException ex) {
            logger.info(ex.getMessage());
        }
        logger.info("dateStringEnd=  " + dateSgtingEnd);
        return dateEnd;
    }


    @Step("Преобразование первой даты,если диапазон")
    public Date getDateBegin(String dateString) {
        Date dateBegin = null;
        String dateStringBegin = null;
        if (dateString.contains("-")) {
            dateStringBegin = dateString.split("-")[0];
            dateStringBegin = dateStringBegin +( getDateEnd(dateString).getYear()+1900);
        }
        try {
            dateBegin = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).parse(dateStringBegin);
        } catch (ParseException ex) {
            logger.info(ex.getMessage());
        }
        logger.info("dateStringBegin=  " + dateStringBegin);
        return dateBegin;
    }


    @Step("Проверка дат мероприятия")
    @Feature("Валидация дат предстоящих мероприятий")
    @DisplayName("Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)")
    public void checkDataEvents() {
        String dateString;
        Date cardDateBegin;
        Date cardDateEnd;
        Date dateNow = new Date();
        softAssertions = new SoftAssertions();
        dateString = getCardDate();
        cardDateBegin = getDateBegin(dateString);
        cardDateEnd = getDateEnd(dateString);
        softAssertions.assertThat(cardDateEnd.getDate()).isGreaterThanOrEqualTo(dateNow.getDate());
        softAssertions.assertThat(cardDateBegin.getDate()).isLessThanOrEqualTo(dateNow.getDate());
        logger.info("Даты проведения мероприятий больше или равны текущей дате (или текущая дата находится в диапазоне дат проведения)");

    }

}
