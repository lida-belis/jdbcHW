import baseTest.BaseTest;
import dbScripts.Customers;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlTest extends BaseTest {

    String sql = "SELECT * FROM public.\"customers\";";

    @Test
    public void addCustomers() {
        logger.info("Test is started!");

        Customers customers = new Customers(jdbcService);
        customers.addCustomer("Natasha", "Duk", "nata@mail", 39);

        ResultSet rs = jdbcService.executeQuery(sql);

        try {
            while (rs.next()) {
                String userid = rs.getString("ID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                logger.info("userid : " + userid);
                logger.info("firstname : " + firstname);
                logger.info("lastname : " + lastname);
                logger.info("email : " + email);
                logger.info("age : " + age);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Test(dependsOnMethods = "addCustomers")
    public void updateCustomers() {
        Customers customers = new Customers(jdbcService);
        customers.updateCustomer();

        ResultSet rs = jdbcService.executeQuery(sql);

        try {
            while (rs.next()) {
                String userid = rs.getString("ID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                logger.info("userid : " + userid);
                logger.info("firstname : " + firstname);
                logger.info("lastname : " + lastname);
                logger.info("email : " + email);
                logger.info("age : " + age);
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Test(dependsOnMethods = "updateCustomers")
    public void deleteCustomer() {
        Customers customers = new Customers(jdbcService);
        customers.deleteCustomer();
        ResultSet rs = jdbcService.executeQuery(sql);

        try {
            while (rs.next()) { String userid = rs.getString("ID");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                int age = rs.getInt("age");

                logger.info("userid : " + userid);
                logger.info("firstname : " + firstname);
                logger.info("lastname : " + lastname);
                logger.info("email : " + email);
                logger.info("age : " + age);//logger.info("The Customer was deleted!");
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
