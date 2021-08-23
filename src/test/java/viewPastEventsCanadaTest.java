import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.EpamMain;
import pages.Events;
import webFactory.BaseHooks;

public class viewPastEventsCanadaTest extends BaseHooks {
    private EpamMain epamMain = new EpamMain(driver);
    private Events events = new Events(driver);
    private static final Logger logger = LogManager.getLogger(viewPastEventsCanadaTest.class);

    @Test
    @Feature("Просмотр прошедших мероприятий Канады")
    public void viewPastEventsCanada() {
//      Открываем сайт мероприятий EPAM
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
//      Открываем вкладку мероприятий
        epamMain.openEvents();
//      Открываем прошедших мероприятия
        events.openPastEvents();
//      Выбираем город проведение -Канада
        events.clickLocationCanada();
//      Проверяем наличие карточек мероприятий на ЭФ
        events.getCards();
//      Проверяем,что даты мероприятий меньше текущей даты
        events.checkDataPastEvents();
//      Проверяем количество карточек на ЭФ
        events.assertUpcomingPastEvents();
    }

    }
