import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.EpamMain;
import pages.Video;
import webFactory.BaseHooks;

public class findVideoTest extends BaseHooks {

    private EpamMain epamMain = new EpamMain(driver);
    private Video video = new Video(driver);
    private static final Logger logger = LogManager.getLogger(reportsBycategoryTest.class);

    @Test
    @Feature("Фильтрация докладов по категориям")
    public void findVideoBeWord() {
//      Открываем сайт мероприятий EPAM
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
//      Открываем вкладку докладов
        epamMain.openVideo();
//      Фильтруем по ключевому слову
        video.filterByWord("QA");
//      Проверяем,что в названии доклада содержится сдово из фильтра
        video.checkCardByWord();
    }
}
