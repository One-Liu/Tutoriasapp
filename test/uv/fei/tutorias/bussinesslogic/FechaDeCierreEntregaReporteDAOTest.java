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
import uv.fei.tutorias.domain.FechaDeCierreEntregaReporte;
import uv.fei.tutorias.domain.PeriodoEscolar;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class FechaDeCierreEntregaReporteDAOTest {
    
    public FechaDeCierreEntregaReporteDAOTest() {
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
    public void testFindFechaDeCierreEntregaReporteByFecha() {
        System.out.println("findFechaDeCierreEntregaReporteByFecha");
        String searchDate = "2022";
        FechaDeCierreEntregaReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaReporteDAO();
        List<FechaDeCierreEntregaReporte> fechasDeCierreEntregaReporteEsperadas = new ArrayList<>();
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(1,"2022-03-09",periodoEscolar);
        Persona personaTutorAcademico = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1,personaTutorAcademico);
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica(1,"Primer reporte de tutoría registrado",sesionDeTutoriaAcademica,tutorAcademico);
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte1 = new FechaDeCierreEntregaReporte(1,"2022-04-01",reporteDeTutoriaAcademica);
        fechasDeCierreEntregaReporteEsperadas.add(fechaDeCierreEntregaReporte1);
        List<FechaDeCierreEntregaReporte> fechasDeCierreEntregaReporteObtenidas = fechaDeCierreEntregaReporteDao.findFechasDeCierreEntregaReporteByFecha(searchDate);
        assertTrue(fechasDeCierreEntregaReporteEsperadas.equals(fechasDeCierreEntregaReporteObtenidas));
    }
    
    @Test
    public void testFindFechaDeCierreEntregaReporteById() {
        System.out.println("findFechaDeCierreEntregaReporteById");
        int idFechaDeCierreEntregaReporte = 1;
        FechaDeCierreEntregaReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaReporteDAO();
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(1,"2022-03-09",periodoEscolar);
        Persona personaTutorAcademico = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1,personaTutorAcademico);
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica(1,"Primer reporte de tutoría registrado",sesionDeTutoriaAcademica,tutorAcademico);
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporteEsperada = new FechaDeCierreEntregaReporte(idFechaDeCierreEntregaReporte,"2022-04-01",reporteDeTutoriaAcademica);
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporteObtenida = fechaDeCierreEntregaReporteDao.findFechaDeCierreEntregaReporteById(idFechaDeCierreEntregaReporte);
        assertTrue(fechaDeCierreEntregaReporteEsperada.equals(fechaDeCierreEntregaReporteObtenida));
    }
    
    @Test
    public void testGetFechaDeCierreEntregaReporte() {
        System.out.println("getFechaDeCierreEntregaReporte");
        int idFechaDeCierreEntregaReporte = 1;
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(1,"2022-03-09",periodoEscolar);
        Persona personaTutorAcademico = new Persona(5,"MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1,personaTutorAcademico);
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica(1,"Primer reporte de tutoría registrado",sesionDeTutoriaAcademica,tutorAcademico);
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporteEsperada = new FechaDeCierreEntregaReporte(idFechaDeCierreEntregaReporte,"2022-04-01",reporteDeTutoriaAcademica);
        FechaDeCierreEntregaReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaReporteDAO();
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporteObtenida = new FechaDeCierreEntregaReporte();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try(Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT FDCEDR.idFechaDeCierreEntregaDeReporte, FDCEDR.fecha AS fechaFechaDeCierreEntregaDeReporte, " +
                "FDCEDR.idReporteDeTutoriaAcademica " +
                "FROM FechaDeCierreEntregaDeReporte FDCEDR " +
                "WHERE FDCEDR.idFechaDeCierreEntregaDeReporte = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFechaDeCierreEntregaReporte);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next() == false) {
                throw new SQLException("FechaDeCierreEntregaReporte not found");
            }
            fechaDeCierreEntregaReporteObtenida = fechaDeCierreEntregaReporteDao.getFechaDeCierreEntregaReporte(resultSet);
        } catch(SQLException ex) {
            Logger.getLogger(FechaDeCierreEntregaReporteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(fechaDeCierreEntregaReporteEsperada.equals(fechaDeCierreEntregaReporteObtenida));
    }

    @Test
    public void testAddFechaDeCierreEntregaReporte() {
        System.out.println("addFechaDeCierreEntregaReporte");
        PeriodoEscolar periodoEscolar = new PeriodoEscolar(0,"","");
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica(0,"",periodoEscolar);
        Persona personaTutorAcademico = new Persona(0,"","","","","");
        TutorAcademico tutorAcademico = new TutorAcademico(0,personaTutorAcademico);
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica(0,"",sesionDeTutoriaAcademica,tutorAcademico);
        FechaDeCierreEntregaReporte fechaDeCierreEntregaReporte = new FechaDeCierreEntregaReporte(0,"",reporteDeTutoriaAcademica);
        FechaDeCierreEntregaReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaReporteDAO();
        boolean result = fechaDeCierreEntregaReporteDao.addFechaDeCierreEntregaReporte(fechaDeCierreEntregaReporte);
        assertTrue(result);
    }

    @Test
    public void testDeleteFechaDeCierreEntregaReporteById() {
        System.out.println("deleteFechaDeCierreEntregaReporteById");
        int idFechaDeCierreEntregaReporte = 0;
        FechaDeCierreEntregaReporteDAO fechaDeCierreEntregaReporteDao = new FechaDeCierreEntregaReporteDAO();
        boolean result = fechaDeCierreEntregaReporteDao.deleteFechaDeCierreEntregaReporteById(idFechaDeCierreEntregaReporte);
        assertFalse(result);
    }
}
