import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.EpamMain;
import pages.Events;
import webFactory.BaseHooks;

public class viewEventCardsTest extends BaseHooks {
    private EpamMain epamMain = new EpamMain(driver);
    private Events events = new Events(driver);
    private static final Logger logger = LogManager.getLogger(viewEventCardsTest.class);

    @Test
    public void viewEventCards (){
        //Открываем сайт мероприятий EPAM
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
        //Открываем вкладку мероприятий
        epamMain.openEvents();
//        Открываем прошедших мероприятия
        events.openPastEvents();
//Проверяем,что на странице отображаются карточки мероприятий
    }
}
