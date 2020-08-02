package dbScripts;

import org.apache.log4j.Logger;
import services.JDBCService;

public class Customers {
    public static Logger logger = Logger.getLogger(Customers.class);

    JDBCService jdbcService;

    public Customers(JDBCService jdbcService) {
        this.jdbcService = jdbcService;
    }

    public void createCustomersTable() {
        String createTableSQL = "CREATE TABLE customers (" +
                "ID SERIAL PRIMARY KEY, " +
                "FirstName CHARACTER VARYING(30), " +
                "LastName CHARACTER VARYING(30), " +
                "Email CHARACTER VARYING(30), " +
                "Age INTEGER" +
                ");";

        jdbcService.executeQuery(createTableSQL);
    }

    public void addCustomer(String firstname, String lastname, String email, int age) {
        String insertTableSQL = "INSERT INTO public.customers(" +
                "firstname, lastname, email, age)" +
                "VALUES ('" + firstname + "', '" + lastname + "', '" + email + "', " + age + ");";

        jdbcService.executeQuery(insertTableSQL);
    }

    public void updateCustomer(String lastname) {
        String updateTableSQL = "UPDATE public.customers\n" +
                "\tSET lastname='" + lastname + "'\n" +
                "\tWHERE lastname = 'Duk';";

        jdbcService.executeQuery(updateTableSQL);
    }

    public void deleteCustomer(int id) {
        String deleteTableSQL = "DELETE FROM public.customers\n" +
                "\tWHERE \"id\" =" + id + ";";

        jdbcService.executeQuery(deleteTableSQL);

    }
}
