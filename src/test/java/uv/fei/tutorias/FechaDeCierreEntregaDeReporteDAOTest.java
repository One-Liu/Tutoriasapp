package uv.fei.tutorias;

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

import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public class FechaDeCierreEntregaDeReporteDAOTest {
    
    public FechaDeCierreEntregaDeReporteDAOTest() {
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
    public void testFindFechaDeCierreEntregaDeReporteByFecha() {
        System.out.println("findFechaDeCierreEntregaReporteByFecha");
        String searchDate = "2022";
        FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaDeReporteDAO();
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporteEsperadas = new ArrayList<>();
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte1 = new FechaDeCierreEntregaDeReporte(1,"2022-04-01",1);
        fechasDeCierreEntregaReporteEsperadas.add(fechaDeCierreEntregaReporte1);
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporteObtenidas = fechaDeCierreEntregaReporteDao.findFechasDeCierreEntregaDeReporteByFecha(searchDate);
        assertTrue(fechasDeCierreEntregaReporteEsperadas.equals(fechasDeCierreEntregaReporteObtenidas));
    }
    
    @Test
    public void testFindFechaDeCierreEntregaDeReporteById() {
        System.out.println("findFechaDeCierreEntregaReporteById");
        int idFechaDeCierreEntregaReporte = 1;
        FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaDeReporteDAO();
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporteEsperada = new FechaDeCierreEntregaDeReporte(idFechaDeCierreEntregaReporte,"2022-04-01",1);
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporteObtenida = fechaDeCierreEntregaReporteDao.findFechaDeCierreEntregaDeReporteById(idFechaDeCierreEntregaReporte);
        assertTrue(fechaDeCierreEntregaReporteEsperada.equals(fechaDeCierreEntregaReporteObtenida));
    }
    
    @Test
    public void testGetFechaDeCierreEntregaDeReporte() {
        System.out.println("getFechaDeCierreEntregaReporte");
        int idFechaDeCierreEntregaReporte = 1;
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporteEsperada = new FechaDeCierreEntregaDeReporte(idFechaDeCierreEntregaReporte,"2022-04-01",1);
        FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaDeReporteDAO();
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporteObtenida = new FechaDeCierreEntregaDeReporte();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT * FROM FechaDeCierreEntregaDeReporte WHERE idFechaDeCierreEntregaDeReporte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            }
            fechaDeCierreEntregaReporteObtenida = fechaDeCierreEntregaReporteDao.getFechaDeCierreEntregaDeReporte(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaDeReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(fechaDeCierreEntregaReporteEsperada.equals(fechaDeCierreEntregaReporteObtenida));
    }

    @Test
    public void testAddFechaDeCierreEntregaDeReporte() {
        System.out.println("addFechaDeCierreEntregaReporte");
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaDeReporte("",0);
        FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaDeReporteDAO();
        boolean result = fechaDeCierreEntregaReporteDao.addFechaDeCierreEntregaDeReporte(fechaDeCierreEntregaReporte);
        assertTrue(result);
    }

    @Test
    public void testDeleteFechaDeCierreEntregaDeReporteById() {
        System.out.println("deleteFechaDeCierreEntregaReporteById");
        int idFechaDeCierreEntregaReporte = 0;
        FechaDeCierreEntregaDeReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaDeReporteDAO();
        boolean result = fechaDeCierreEntregaReporteDao.deleteFechaDeCierreEntregaDeReporteById(idFechaDeCierreEntregaReporte);
        assertFalse(result);
    }
}
