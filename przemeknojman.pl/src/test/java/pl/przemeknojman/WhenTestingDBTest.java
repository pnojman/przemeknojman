package pl.przemeknojman;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.*;
import pl.przemeknojman.builders.JbnaActionLogsExtensionsBuilder;
import pl.przemeknojman.dao.JbnaActionLogsExtensionsDAO;
import pl.przemeknojman.dto.JbnaActionLogsExtensionsDTO;

import java.util.Optional;

public class WhenTestingDBTest extends TestConfig {

    @BeforeMethod
    public void setUp() {
        getDBConnection();
    }

    @AfterMethod
    public void tearDown() {
        closeDbConnection();
    }

    @Test
    public void testDatabaseQuery() {

        JbnaActionLogsExtensionsDTO initializedTestData = new JbnaActionLogsExtensionsBuilder(new Faker()).withRandomExtension().build();
        String extension = initializedTestData.getExtension();

        JbnaActionLogsExtensionsDAO initializedActions = new JbnaActionLogsExtensionsDAO(connection);
        initializedActions.insert(initializedTestData);

        Optional<JbnaActionLogsExtensionsDTO> selectResult = initializedActions.selectByField("extension", extension);

        if (selectResult.isPresent()) {

            JbnaActionLogsExtensionsDTO jbnaActionLogsExtensionsDTO1 = selectResult.get();
            String extensionInserted = jbnaActionLogsExtensionsDTO1.getExtension();

            Assert.assertEquals(extension, extensionInserted);

            String extensionToUpdate = extensionInserted + "something";
            jbnaActionLogsExtensionsDTO1.setExtension(extensionToUpdate);

            initializedActions.update(jbnaActionLogsExtensionsDTO1);

            selectResult = initializedActions.selectByField("extension", extensionToUpdate);

            if (selectResult.isPresent()) {
                jbnaActionLogsExtensionsDTO1 = selectResult.get();
                String extensionUpdated = jbnaActionLogsExtensionsDTO1.getExtension();

                Assert.assertEquals(extensionToUpdate, extensionUpdated);
            }
        }
    }
}


