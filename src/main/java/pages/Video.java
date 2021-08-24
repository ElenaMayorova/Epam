package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;

import static webFactory.BaseHooks.*;

public class Video extends EpamMain {
    private static final Logger logger = LogManager.getLogger(Events.class);

    public Video(WebDriver driver) {
        super(driver);
    }

    //    Локаторы
    private final By MOREFILTER = By.xpath("//span[contains(text(),'More Filters')]");
    private final By LOADER = By.xpath("//*[contains(@class, 'evnt-global-loader')]");
    private final By CATEGORYINPUT = By.xpath("//div[@aria-labelledby='filter_category']/div[@class='evnt-filter-menu-search-wrapper']/input");
    private final By CATEGORY = By.xpath("//div[@id='filter_category']");
    private final By LOCATION = By.xpath("//div[@id='filter_location']");
    private final By LANGUAGE = By.xpath("//div[@id='filter_language']");
    private final By LANGUAGEINCARD = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']/span");
    private final By LOCATIONCARD = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']/span");
    private final By CATEGORYCARD = By.xpath("//div[@class='evnt-topics-wrapper']//label");
    private final By FIRSTCARD = By.xpath("//div[contains(@class, 'evnt-talks-column')]");
    private final By INPUTTEXT = By.xpath("//input[@class='evnt-text-fields form-control evnt-search']");
    private final By CARDNAME = By.xpath("//div[@class='evnt-content-text']/h1");


    @Step("More Filter")
    @DisplayName("Открываем расширенный фильтр")
    public void openMoreFilter() {
        waitInvisibleElement(LOADER);
        getClickableElement(MOREFILTER).click();
        logger.info("Открываем расширенный фильтр");
        Allure.addAttachment("Открываем расширенный фильтр", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        waitInvisibleElement(LOADER);
    }

    public void selectFilterValue(By filter, String value) {
        getVisibilityElement(filter).click();
        getClickableElement(By.xpath("//*[@data-value='" + value + "']")).click();
        Allure.addAttachment("Выбираем значение фильтра" + filter + "=" + value, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Filter")
    @DisplayName("Пользователь выбирает: Category – Testing, Location – Belarus, Language – English")
    public void filter() {
        selectFilterValue(CATEGORY, "Testing");
        logger.info("Выбираем категорию тестирование");
        Allure.addAttachment("Выбираем категорию тестирование", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        selectFilterValue(LOCATION, "Belarus");
        logger.info("Выбираем локацию Белоруссия");
        Allure.addAttachment("Выбираем локацию Белоруссия", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        selectFilterValue(LANGUAGE, "ENGLISH");
        logger.info("Выбираем английский язык ");
        Allure.addAttachment("Выбираем английский язык ", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    //Проваливаемся в первую карточку
    public void selectFirstcard() {
        waitInvisibleElement(LOADER);
        getClickableElement(FIRSTCARD).click();
        waitInvisibleElement(LOADER);
        logger.info("Выбрали в списке первую карточку");
    }

    //Проверка полей карточки

    @Step("Извлекаем язык из карточки доклада")
    public String checkCardLanguage() {
        return getVisibilityElement(LANGUAGEINCARD).getText();
    }

    @Step("Извлекаем локацию из карточки доклада")
    public String checkCardLocation() {
        return getVisibilityElement(LOCATIONCARD).getText();
    }

    @Step("Извлекаем категорию из карточки доклада")
    public String checkCardCategory() {
        return getVisibilityElement(CATEGORYCARD).getText();
    }

    @Step("Извлекаем название из карточки доклада")
    public String checkCardName() {
        return getVisibilityElement(CARDNAME).getText();
    }

    //Проверка  заполнения полей:язык, категория, локация в карточке
    @Step("Проверка полей карточки")
    @Feature("Фильтрация докладов по категориям")
    @DisplayName("Проверка  заполнения полей:язык, категория, локация в карточке")
    public void checkCard() {
        waitInvisibleElement(LOADER);
        selectFirstcard();
        Assert.assertTrue(checkCardLanguage().contains("ENGLISH"));
        logger.info("выполнена проверка наличия языка мероприятия     " + checkCardLanguage());
        Assert.assertTrue(checkCardLocation().contains("Belarus"));
        logger.info("выполнена проверка наличия локации мероприятия     " + checkCardLocation());
        Assert.assertTrue(checkCardCategory().contains("Testing"));
        logger.info("выполнена проверка наличия локации мероприятия     " + checkCardCategory());
    }

    //Проверка , что после фильтрации наименование доклада содержит слово из фильтра
    @Step("Проверка полей карточки")
    @Feature("Поиск докладов по ключевому слову")
    @DisplayName("Проверка , что после фильтрации наименование доклада содержит слово из фильтра")
    public void checkCardByWord() {
        selectFirstcard();
        Assert.assertTrue(checkCardName().contains("QA"));
        logger.info("выполнена проверка наличия в названии мероприятия слова из фильтра     " + checkCardLanguage());
    }

    //Проверка  заполнения полей:язык, категория, локация в карточке
    @Step("Поиск по ключевому слову")
    @DisplayName("Фильтруем доклады по ключевому слову")
    public void filterByWord(String value) {
        waitInvisibleElement(LOADER);
        getClickableElement(INPUTTEXT).sendKeys(value);
    }

}
