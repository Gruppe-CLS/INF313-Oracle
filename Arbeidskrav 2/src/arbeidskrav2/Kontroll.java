package arbeidskrav2;
import java.sql.*;
import oracle.jdbc.OracleTypes;
import java.sql.DriverManager;
/**
 *
 * @Skrevet av Christian
 */
public class Kontroll {
    
    public static final Kontroll kontroll = new Kontroll();
    String oracleURL = "jdbc:oracle:thin:@158.36.139.21:1521:ORCL";
    Connection conn = null;
    Statement setning;
    ResultSet resultat;
    CallableStatement callableStatement = null;
    
     public void initialiserDB() {
        try {
            conn = DriverManager.getConnection(oracleURL, "C##STAALE2", "staale_313");
        } catch (Exception e) {
            System.out.println("conn fungerer ikke.");
        }
    }
    
    private Kontroll() {
        initialiserDB();
    }
    
    
    /*
    == Her begynner funksjonene som skal legge inn ny data i databasen vår
    ===========================================================================
    */
    public void nyStudent(String fornavn, String etternavn, String epost) throws SQLException {
        try {
            callableStatement = conn.prepareCall("{ call NYSTUDENT(?, ? , ?) }");
            callableStatement.setString(1,fornavn);
            callableStatement.setString(2, etternavn);
            callableStatement.setString(3, epost);
            callableStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Klarte ikke legge inn ny student.");
        }
    } // Slutt metode nyStudent
    
    
    /*
    == Her begynner funksjonene som skal gi data til "Oversikt"-siden i programmet
    ==================================================================================
    */
    public ResultSet hentAlleStudenter() throws SQLException {
        try {
            String query = "{ ? = call GETSTUDENTER() }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.CURSOR);
            callableStatement.execute();
            ResultSet rs = (ResultSet)callableStatement.getObject(1);
            return rs;
        } catch (SQLException ex) {
            System.out.println("noe gikk galt med funksjonen hentAlleStudneter().");
            return null;
        }
    } 
    
    public int getAntallStudenter() throws SQLException {
        try {
            String query = "{ ? = call GETANTALLSTUDENTER() }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.INTEGER);
            callableStatement.execute();
            int rs = (int)callableStatement.getObject(1);
            return rs;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int getStrykprosent() throws SQLException {
        try {
            String query = "{ ? = call GETSTRYKPROSENT() }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.INTEGER);
            callableStatement.execute();
            int rs = (int)callableStatement.getObject(1);
            return rs;
        } catch (Exception e) {
            return 0;
        }
    }
}
