package teddserv;
import static com.sun.corba.se.impl.orbutil.CorbaResourceUtil.getText;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DB {
 
    
    /**
     * Metoda odpowiedzialna za połączenie z bazą
     * jeśli bazy nie ma to zostaje utworzona
     */
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:Historia.db";
   
    
    private static Connection conn;
    private static  Statement stat;
 
    public static Connection DB() {
        try {
            Class.forName(DB.DRIVER);
            System.out.println("Baza połączona ! ");
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
 
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
        createTable();
        return conn;
    }
   public static boolean createTable()  {
		String Hist = "CREATE TABLE IF NOT EXISTS Historia ( nick varchar(50),tekst varchar(255) )";

		try {
			stat.execute(Hist);
                        System.out.println("Tabela zrobina !");

		} catch (SQLException e) {
			System.err.println("Error during creating table.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
     public static boolean dodajDane(String nick, String tekst ){
		try {
			PreparedStatement prepStmt = conn.prepareStatement(
					"insert into Historia values (?, ?);");
			prepStmt.setString(1, nick);
			prepStmt.setString(2, tekst);
			prepStmt.execute();
                     
      
		} catch (SQLException e) {
                    System.out.println("Blad przy wstawianiu");
			System.err.println("Blad przy wstawianiu");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
