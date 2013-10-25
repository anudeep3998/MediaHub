import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLite {
    private Statement stmt;
    private Connection c;

    public void setupConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt=c.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

}
