package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.Persona;

// author @liu
public class CoordinadorDAOTest {
    
    public CoordinadorDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindCoordinadorByName() {
        System.out.println("findCoordinadorByName");
        String searchName = "MARÍA";
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        ArrayList<Coordinador> coordinadoresEsperados = new ArrayList<>();
        Persona persona = new Persona("MARÍA DE LOS ÁNGELES","ARENAS","VALDEZ");
        Coordinador coordinador1 = new Coordinador(1, persona, 1);
        coordinadoresEsperados.add(coordinador1);
        ArrayList<Coordinador> coordinadoresObtenidos = coordinadorDao.findCoordinadorByName(searchName);
        assertTrue(coordinadoresEsperados.equals(coordinadoresObtenidos));
    }

    @Test
    public void testFindCoordinadorById() {
        System.out.println("findCoordinadorById");
        int idCoordinador = 1;
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        Persona persona = new Persona(7,"MARÍA DE LOS ÁNGELES","ARENAS","VALDEZ");
        Coordinador coordinadorEsperado = new Coordinador(idCoordinador, persona, 1);
        Coordinador coordinadorObtenido = coordinadorDao.findCoordinadorById(idCoordinador);
        assertTrue(coordinadorEsperado.equals(coordinadorObtenido));
    }

    @Test
    public void testGetCoordinador() {
        System.out.println("getCoordinador");
        int idCoordinador = 1;
        Persona persona = new Persona(7,"MARÍA DE LOS ÁNGELES","ARENAS","VALDEZ");
        Coordinador coordinadorEsperado = new Coordinador(idCoordinador, persona, 1);
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        Coordinador coordinadorObtenido = new Coordinador();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM Coordinador C LEFT JOIN Persona P ON P.idPersona = C.idPersona WHERE idCoordinador = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinador);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("Coordinador not found");
            }
            coordinadorObtenido = coordinadorDao.getCoordinador(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(CoordinadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(coordinadorEsperado.equals(coordinadorObtenido));
    }

    @Test
    public void testAddCoordinador() {
        System.out.println("addCoordinador");
        Persona persona = new Persona(0,"","","");
        Coordinador coordinador = new Coordinador(persona, 1);
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        boolean result = coordinadorDao.addCoordinador(coordinador);
        assertTrue(result);

    }

    @Test
    public void testDeleteCoordinadorById() {
        System.out.println("deleteCoordinadorById");
        int idCoordinador = 2;
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        boolean result = coordinadorDao.deleteCoordinadorById(idCoordinador);
        assertFalse(result);
    }
}
