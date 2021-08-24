package pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

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
    private final By CATEGORY = By.xpath("//span[contains(text(),'Category')]");
    private final By LOCATION = By.xpath("//span[contains(text(),'Location')]");
    private final By LANGUAGE = By.xpath("//span[contains(text(),'Language')]");
    private final By LANGUAGEINCARD = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']/span");
    private final By LOCATIONCARD = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']/span");
    private final By CATEGORYCARD = By.xpath("//div[@class='evnt-topics-wrapper']//label");
    private final By FIRSTCARD = By.xpath("/html/body/div[2]/div[1]/main/section[3]/div/div/div/div[2]/div[1]/div/a");
//    private final By INPUTTEXT = By.xpath("//div[@id='evnt-filter-menu evnt-dropdown-menu dropdown-menu with-arrow show']//input");

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
        getVisibilityElement(By.xpath("//div[@class='evnt-checkbox form-check']//label[contains(@data-value,'" + value + "')]")).click();
        Allure.addAttachment("Выбираем значение фильтра" + filter + "=" + value, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("Filter")
    @DisplayName("Пользователь выбирает: Category – Testing, Location – Belarus, Language – English")
    public void filter() {

        selectFilterValue(CATEGORY, "Design");
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
        getClickableElement(FIRSTCARD).sendKeys(Keys.ENTER);
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
        Assert.assertTrue(checkCardCategory().contains("Design"));
        logger.info("выполнена проверка наличия локации мероприятия     " + checkCardCategory());
    }

}
