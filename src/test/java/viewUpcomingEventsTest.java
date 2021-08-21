import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.EpamMain;
import pages.Events;
import webFactory.BaseHooks;


public class viewUpcomingEventsTest extends BaseHooks {
    private EpamMain epamMain = new EpamMain(driver);
    private Events events = new Events(driver);
    private static final Logger logger = LogManager.getLogger(viewUpcomingEventsTest.class);

    @Test
    public void viewUpcomingEvents() {

        //Открываем сайт мероприятий EPAM
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
        //Открываем вкладку мероприятий
        epamMain.openEvents();
//        Открываем предстоящие мероприятия
        events.openUpcomingEvents();
//        Проверяем наличие карточек мероприятий на ЭФ
                events.getCards();
//       Проверяем,что количество карточек равно счетчику на кнопке Upcoming Events
        events.assertUpcomingEvents();
    }

}

