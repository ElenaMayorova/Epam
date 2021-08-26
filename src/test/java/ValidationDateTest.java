import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.EpamMain;
import pages.Events;
import webFactory.BaseHooks;

public class ValidationDateTest extends BaseHooks {
    private EpamMain epamMain = new EpamMain(driver);
    private Events events = new Events(driver);
    private static final Logger logger = LogManager.getLogger(ValidationDateTest.class);


    @Test
    @Feature("Валидация дат")
    public void viewUpcomingEvents() {
//        Открываем сайт мероприятий EPAM
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
//        Открываем вкладку мероприятий
        epamMain.openEvents();
//        Открываем предстоящие мероприятия
        events.openUpcomingEvents();
//        Проверяем наличие карточек мероприятий на ЭФ
        events.getCards();
//        Проверяем,что дата мероприятия больше текущей даты
        events.checkDataEvents();
    }
}

