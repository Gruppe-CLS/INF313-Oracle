package arbeidskrav2;
import java.sql.*;
import oracle.jdbc.OracleTypes;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public String nyStudent(String fornavn, String etternavn, String epost) throws SQLException {
        try {
            callableStatement = conn.prepareCall("{ call NYSTUDENT(?, ? , ? , ?) }");
            callableStatement.setString(1,fornavn);
            callableStatement.setString(2, etternavn);
            callableStatement.setString(3, epost);
            callableStatement.registerOutParameter(4, java.sql.Types.VARCHAR);
            callableStatement.executeUpdate();
            String tilbakemelding = callableStatement.getString(4);
            return tilbakemelding;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "Klarte ikke å legge til ny student.";
        }
    } // Slutt metode nyStudent
    
    
    // Nytt FAG
    public String nyttFag(int fagnr, String fagnavn, int studiepoeng) throws SQLException {
        try {
            callableStatement = conn.prepareCall("{ call NYTTFAG(?, ? , ?, ?) }");
            callableStatement.setInt(1,fagnr);
            callableStatement.setString(2, fagnavn);
            callableStatement.setInt(3, studiepoeng);
            callableStatement.registerOutParameter(4, java.sql.Types.INTEGER);
            callableStatement.executeUpdate();
            int res = callableStatement.getInt(4);
            if(res == 0) {
                return "Klarte ikke å legge inn nytt fag. ";
            } else {
                return "Nytt fag har blitt lagt inn. ";
            }            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    } // Slutt metode nyttFag
    
    // Ny KARAKTER
    public String nyKarakter(String dato, int fagID, int studentnr, String karakter) throws SQLException {
        try {
            callableStatement = conn.prepareCall("{ call UPDATEKARAKTER(?, ?, ?, ?, ?) }");
            callableStatement.setString(1, dato);
            callableStatement.setInt(2, fagID);
            callableStatement.setInt(3, studentnr);
            callableStatement.setString(4, karakter);
             callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
            callableStatement.executeUpdate();
            int res = callableStatement.getInt(5);
            if(res == 1) {
                return "Ny karakter ble lagt inn.";
            } else {
                return "Beklager, men klarte ikke legge inn ny karakter.";
            }   
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    } // Slutt metode nyKarakter
    
    
    // getFagKode() - Tar imot fagnavn og returnerer fagnr som int
    // Brukes ved innlegging av ny karakter
    public int getFagKode(String fagnavn) throws SQLException {
        try {
            String query = "{ ? = call GETFAGKODE(?) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,Types.NUMERIC);
            callableStatement.setString(2, fagnavn);
            callableStatement.execute();
            int fkode = callableStatement.getInt(1);
            return fkode;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    // Gyldig dato
    public boolean gyldigDato(String input) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
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
    
    
    /*
    == Funksjonene til SØK-siden i programmet. Info om en enkelt student
    ==================================================================================
    */
    public String getKaraktersnitt(int nr) throws SQLException {
        try {
            String query = "{ ? = call GETGJENNOMSNITTSTUDENT(?) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,Types.VARCHAR);
            callableStatement.setInt(2, nr);
            callableStatement.execute();
            String snitt = callableStatement.getString(1);
            return snitt;

        } catch (SQLException e) {
            System.out.println("Kastet ut av getKaraktersnitt().");
            return "Ingen resultater.";
        }
    }
    
    // Henter alle KURS studenten er oppmeld i, og IKKE har fått karakter i
    public ResultSet getAlleFagTilStudent(int nr) throws SQLException {
        try {
            String query = "{ call ? := GETKURSSTUDENT(?) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.CURSOR);
            callableStatement.setInt(2, nr);
            callableStatement.execute();
            ResultSet rs = (ResultSet)callableStatement.getObject(1);
            return rs;
        } catch (SQLException e) {
            System.out.println("noe gikk galt med funksjonen getAlleFagTilStudent()");
            return null;
        }
    }
    
    public String getNavn(int nr) throws SQLException {
        try {
            String query = "{ ? = call GETNAVN(?) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.VARCHAR);
            callableStatement.setInt(2, nr);
            callableStatement.execute();
            String navn = callableStatement.getString(1);
            return navn;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "Fant ikke navn.";
        }
    }
    
    public ResultSet searchEtternavn(String navn) throws SQLException {
        try {
            String query = "{ call ? := SEARCHETTERNAVN(?) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.CURSOR);
            callableStatement.setString(2, navn);
            callableStatement.execute();
            ResultSet rs = (ResultSet)callableStatement.getObject(1);
            return rs;
        } catch (SQLException e) {
            System.out.println("noe gikk galt med funksjonen getAlleFagTilStudent()");
            return null;
        }
    }
    
    public String getKarakterFag(int studnr, int fagkode) {
        try {
            String query = "{ call ? := GETKARAKTERKURS(?, ?) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.VARCHAR);
            callableStatement.setInt(2, studnr);
            callableStatement.setInt(3, fagkode);
            callableStatement.execute();
            String karakter = callableStatement.getString(1);
            return karakter;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public ResultSet getAlleFullforte(int studentnr) throws SQLException {
        try {
            String query = "{ ? = call GETFULLFØRT( ? ) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.CURSOR);
            callableStatement.setInt(2, studentnr);
            callableStatement.execute();
            ResultSet rs = (ResultSet)callableStatement.getObject(1);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    
    /*
    == OPPMELDING
    ================
    == Vi har besluttet å la programmet få melde opp studenter som allerede har en 
    == karakter på nytt. Altså gir vi muligheten til å prøve på nytt i et fag.
    ==
    == Til oppmelding trenger vi blant annet å liste ut alle fag, uavhenig om studenten
    == har karakter eller ikke.
    ==
    == Men vi ønsker ikke å la brukeren melde opp studenter som allerede ER meldt opp
    == uten karakter. 
    ==================================================================================
    */
    
    // getAlleFag henter ALLE fag som studenten IKKE er oppmeldt i
    // Altså ikke fag studenten står oppført på uten karkater
    public ResultSet getAlleFag(int studentnr) throws SQLException {
        try {
            String query = "{ ? = call GETALLEKURS( ? ) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.CURSOR);
            callableStatement.setInt(2, studentnr);
            callableStatement.execute();
            ResultSet rs = (ResultSet)callableStatement.getObject(1);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    // Returnerer Karakteren studenten fikk i aktuelt fag. Tar imot studnr og fagkode
    public String sjekkKarakter(int studnr, int fagkode) {
        try {
            String query = "{ ? = call SJEKKURS( ? , ? ) }";
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(1,OracleTypes.CHAR);
            callableStatement.setInt(2, studnr);
            callableStatement.setInt(3, fagkode);
            callableStatement.execute();
            String karakter = callableStatement.getString(1);
            return karakter;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    // Oppdater karakter - Brukes med oppmelding på nytt
    public String oppdaterKarakter(int fagID, int studentnr) throws SQLException {
        try {
            callableStatement = conn.prepareCall("{ call OPPMELDINGKAR(?, ?, ?) }");
            callableStatement.setInt(1, fagID);
            callableStatement.setInt(2, studentnr);
            callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
            callableStatement.executeUpdate();
            int res = callableStatement.getInt(3);
            if(res == 0) {
                return "Ny oppmelding mislyktes.";
            } else {
                return "Ny oppmelding velykket";
            }     
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    } // Slutt metode oppdaterKarakter
    
    
    // For de som ikke har en karakter fra før
    public String nyOppmelding(int fagID, int studentnr) throws SQLException {
        try {
            callableStatement = conn.prepareCall("{ call NYKARAKTER(?, ?, ?, ?, ?) }");
            callableStatement.setDate(1, null);
            callableStatement.setInt(2, fagID);
            callableStatement.setInt(3, studentnr);
            callableStatement.setString(4, null);
            callableStatement.registerOutParameter(5, java.sql.Types.INTEGER);
            callableStatement.executeUpdate();
            int res = callableStatement.getInt(5);
            if(res == 0) {
                return "Klarte ikke å melde opp til fag.";
            } else {
                return "Student er oppmeldt.";
            }     
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
    } // Slutt metode nyOppmelding
    
    
            
            
}
