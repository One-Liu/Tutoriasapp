package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAOTest {
    
    public PeriodoEscolarDAOTest() {
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
    public void testFindPeriodosEscolaresByFechaInicio() {
        System.out.println("findPeriodosEscolaresByFechaInicio");
        String date = "2022";
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        List<PeriodoEscolar> periodosEscolaresEsperados = new ArrayList<>();
        PeriodoEscolar periodoEscolar1 = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        periodosEscolaresEsperados.add(periodoEscolar1);
        PeriodoEscolar periodoEscolar2 = new PeriodoEscolar(2,"2022-08-01","2023-01-01");
        periodosEscolaresEsperados.add(periodoEscolar2);
        List<PeriodoEscolar> periodosEscolaresObtenidos = periodoEscolarDao.findPeriodosEscolaresByFechaInicio(date);
        assertTrue(periodosEscolaresEsperados.equals(periodosEscolaresObtenidos));
    }

    @Test
    public void testFindPeriodoEscolarById() {
        System.out.println("findPeriodoEscolarById");
        int idPeriodoEscolar = 1;
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        PeriodoEscolar periodoEscolarEsperado = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        PeriodoEscolar periodoEscolarObtenido = periodoEscolarDao.findPeriodoEscolarById(idPeriodoEscolar);
        assertTrue(periodoEscolarEsperado.equals(periodoEscolarObtenido));
    }
    
    @Test
    public void testGetPeriodoEscolar() {
        System.out.println("getPeriodoEscolar");
        int idPeriodoEscolar = 1;
        PeriodoEscolar periodoEscolarEsperado = new PeriodoEscolar(idPeriodoEscolar,"2022-02-01","2022-07-01");
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        PeriodoEscolar periodoEscolarObtenido = new PeriodoEscolar();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT PE.idPeriodoEscolar,PE.fechaInicio AS fechaInicioPeriodoEscolar, " + 
                "PE.fechaTermino AS fechaTerminoPeriodoEscolar FROM PeriodoEscolar PE WHERE idPeriodoEscolar = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idPeriodoEscolar);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Periodo Escolar not found");
            }
            periodoEscolarObtenido = periodoEscolarDao.getPeriodoEscolar(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(PeriodoEscolarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(periodoEscolarEsperado.equals(periodoEscolarObtenido));
    }

    @Test
    public void testAddPeriodoEscolar() {
        System.out.println("addPeriodoEscolar");
        PeriodoEscolar periodoEscolar = new PeriodoEscolar("2022-08-01","2023-01-01");
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        boolean result = periodoEscolarDao.addPeriodoEscolar(periodoEscolar);
        assertTrue(result);
    }

    @Test
    public void testDeletePeriodoEscolarById() {
        System.out.println("deletePeriodoEscolarById");
        int idPeriodoEscolar = 2;
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        boolean result = periodoEscolarDao.deletePeriodoEscolarById(idPeriodoEscolar);
        assertTrue(result);
    }
}
