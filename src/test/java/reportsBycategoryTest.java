import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.EpamMain;
import pages.Video;
import webFactory.BaseHooks;

public class reportsBycategoryTest extends BaseHooks {
    private EpamMain epamMain = new EpamMain(driver);
    private Video video = new Video(driver);
    private static final Logger logger = LogManager.getLogger(reportsBycategoryTest.class);


    @Test
    @Feature("Фильтрация докладов по категориям")
    public void viewUpcomingEvents() {
        //        Открываем сайт мероприятий EPAM
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
//        Открываем вкладку докладов
        epamMain.openVideo();
//        Открываем рассширенный фильтр
        video.openMoreFilter();
//        Устанавливаем фильтр по локации, языку, категории
        video.filter();
//       Проверяем в карточкечто фильтр отработал корректно (локации, языку, категории = фильтру)
        video.checkCard();
    }


}
