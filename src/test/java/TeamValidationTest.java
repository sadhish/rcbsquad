import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TeamValidationTest extends CommonUtils {

    protected static final Logger logger = LogManager.getLogger(TeamValidationTest.class);
    private List<Map<String, String>> getPlayersList;

    @BeforeClass
    void getTestData() throws Exception {
        getPlayersList = dataRequest();
    }

    @Test(description = "test validates that team has equal or less than four foreign players")
    void testForeignPlayersCount() throws Exception {

        boolean foreignPlayerCount = getPlayersList.stream()
                .filter(e -> !e.get("country").equalsIgnoreCase("india"))
                .count() <= 4 ? true : false;

        Assert.assertEquals(true, foreignPlayerCount, "Foreign Players should be less than or equal to 4");
        logger.info("Team consists of four or less than four foriegn players");

    }

    @Test(description = "test validates that team has atleast one wicket keeper")
    void testAtleastOneWicketKeeperExist() throws IOException {
        boolean wicketKeeperCount = getPlayersList.stream()
                .filter(e -> e.get("role").equalsIgnoreCase("Wicket-keeper"))
                .count() > 0 ? true : false;
        Assert.assertEquals(true, wicketKeeperCount, "One Wicket Keeper should be in the Team");
        logger.info("Team consists of atleast on wicketkeeper");
    }

}
