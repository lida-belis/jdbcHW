import baseTest.BaseTest;
import dbScripts.Customers;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlTest extends BaseTest {
//    @Test
//    public void selectAllCustomers() {
//        String sql = "SELECT * FROM public.\"Customers\";";
//        ResultSet rs = jdbcService.executeQuery(sql);
//
//        try {
//            while (rs.next()) {
//                int id = rs.getInt("ID");
//                String forename = rs.getString("forename");
//                String surname = rs.getString("surname");
//                String email = rs.getString("email");
//                int age = rs.getInt("age");
//
//                logger.info("id: " + id);
//                logger.info("forename: " + forename);
//                logger.info("surname: " + surname);
//                logger.info("email: " + email);
//                logger.info("age: " + age);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

    @Test
    public void addCustomers() {
        logger.info("Test is started!");

        Customers customers = new Customers(jdbcService);
        customers.addCustomer("Natasha", "Duk", "nata@mail", 39);

        String sql = "SELECT * FROM public.\"customers\";";
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
}
