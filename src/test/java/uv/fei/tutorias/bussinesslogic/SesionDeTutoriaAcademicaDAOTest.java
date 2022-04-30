package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAOTest {
    
    public SesionDeTutoriaAcademicaDAOTest() {
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
    public void testFindSesionesDeTutoriaAcademicaByFecha() {
        System.out.println("findSesionesDeTutoriaAcademicaByFecha");
        String searchDate = "2022";
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        List<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademicaEsperadas = new ArrayList<>();
        PeriodoEscolar periodoEscolar1 = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica1 = new SesionDeTutoriaAcademica(1,"2022-03-09",periodoEscolar1);
        sesionesDeTutoriaAcademicaEsperadas.add(sesionDeTutoriaAcademica1);
        List<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademicaObtenidas = sesionDeTutoriaAcademicaDao.findSesionesDeTutoriaAcademicaByFecha(searchDate);
        assertTrue(sesionesDeTutoriaAcademicaEsperadas.equals(sesionesDeTutoriaAcademicaObtenidas));
    }

    @Test
    public void testFindSesionDeTutoriaAcademicaById() {
        System.out.println("findSesionDeTutoriaAcademicaById");
        int idSesionDeTutoriaAcademica = 1;
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaEsperada = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,"2022-03-09",periodoEscolar);
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaObtenida = sesionDeTutoriaAcademicaDao.findSesionDeTutoriaAcademicaById(idSesionDeTutoriaAcademica);
        assertTrue(sesionDeTutoriaAcademicaEsperada.equals(sesionDeTutoriaAcademicaObtenida));
    }

    @Test
    public void testGetSesionDeTutoriaAcademica() {
        System.out.println("getSesionDeTutoriaAcademica");
        int idSesionDeTutoriaAcademica = 1;
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaEsperada = new SesionDeTutoriaAcademica(idSesionDeTutoriaAcademica,"2022-03-09",periodoEscolar);
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaObtenida = new SesionDeTutoriaAcademica();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT SDTA.idSesionDeTutoriaAcademica, SDTA.fecha AS fechaSesionDeTutoriaAcademica, SDTA.idPeriodoEscolar " +
                "FROM SesionDeTutoriaAcademica SDTA " +
                "WHERE SDTA.idSesionDeTutoriaAcademica = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idSesionDeTutoriaAcademica);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("SesionDeTutoriaAcademica not found");
            }
            sesionDeTutoriaAcademicaObtenida = sesionDeTutoriaAcademicaDao.getSesionDeTutoriaAcademica(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(SesionDeTutoriaAcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(sesionDeTutoriaAcademicaEsperada.equals(sesionDeTutoriaAcademicaObtenida));
    }
    
    @Test
    public void testAddSesionDeTutoriaAcademica() {
        System.out.println("addSesionDeTutoriaAcademica");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        boolean result = sesionDeTutoriaAcademicaDao.addSesionDeTutoriaAcademica(sesionDeTutoriaAcademica);
        assertTrue(result);
    }

    @Test
    public void testDeleteSesionDeTutoriaAcademicaById() {
        System.out.println("deleteSesionDeTutoriaAcademicaById");
        int idSesionDeTutoriaAcademica = 0;
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDao = new SesionDeTutoriaAcademicaDAO();
        boolean result = sesionDeTutoriaAcademicaDao.deleteSesionDeTutoriaAcademicaById(idSesionDeTutoriaAcademica);
        assertFalse(result);
    }
}
