import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import pages.EpamMain;
import webFactory.BaseHooks;
import org.junit.*;


public class Test extends BaseHooks {
    private EpamMain epamMain = new EpamMain(driver);
    private static final Logger logger = LogManager.getLogger(Test.class);

    @org.junit.Test
    public void loginEpam() {

        //Открываем сайт Otus.ru
        epamMain.open();
        Assert.assertEquals("Events Portal", driver.getTitle());
    }

}

