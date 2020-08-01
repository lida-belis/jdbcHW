package baseTest;

import dbScripts.Customers;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import services.JDBCService;

public class BaseTest {
    public static Logger logger = Logger.getLogger(BaseTest.class);

    public JDBCService jdbcService;

    @BeforeTest
    public void setupTest() {
        org.apache.log4j.BasicConfigurator.configure();

        jdbcService = new JDBCService();
        jdbcService.dropAllTables();

        Customers customers = new Customers(jdbcService);
        customers.createCustomersTable();
    }

    @AfterTest
    public void tearDown() {
        jdbcService.closeStatement();
        jdbcService.closeConnection();
    }
}
