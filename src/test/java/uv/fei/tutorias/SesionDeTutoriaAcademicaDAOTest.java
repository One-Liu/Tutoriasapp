package uv.fei.tutorias;

import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class SesionDeTutoriaAcademicaDAOTest {
    
    private Date fechaNueva;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademicaNueva;
    private Date fecha1;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica1;
    private SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO;
    
    @Before
    public void inicializar() {
        fechaNueva = (Date) java.sql.Date.valueOf(LocalDate.of(2016, 8, 19));
        sesionDeTutoriaAcademicaNueva = new SesionDeTutoriaAcademica(fechaNueva,false,0);
        fecha1 = new Date();
        sesionDeTutoriaAcademica1 = new SesionDeTutoriaAcademica(1,fecha1,false,1);
        sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    }

    @Test
    public void testObtenerSesionesDeTutoriaAcademica() throws SQLException {
        ArrayList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademicaEsperadas = new ArrayList<>();
        sesionesDeTutoriaAcademicaEsperadas.add(sesionDeTutoriaAcademica1);
        ArrayList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademicaObtenidas = (ArrayList<SesionDeTutoriaAcademica>) sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademica();
        assertFalse(sesionesDeTutoriaAcademicaEsperadas.equals(sesionesDeTutoriaAcademicaObtenidas));
    }

    @Test
    public void testObtenerSesionDeTutoriaAcademicaPorId() throws SQLException {
        int idSesionDeTutoriaAcademica = 1;
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaObtenida = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorId(idSesionDeTutoriaAcademica);
        assertTrue(sesionDeTutoriaAcademica1.equals(sesionDeTutoriaAcademicaObtenida));
    }
    
    @Test
    public void testAgregarSesionDeTutoriaAcademica() throws SQLException {
        assertTrue(sesionDeTutoriaAcademicaDAO.agregarSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaNueva));
    }

    @Test
    public void testEliminarSesionDeTutoriaAcademicaPorId() throws SQLException {
        int idSesionDeTutoriaAcademica = 0;
        assertFalse(sesionDeTutoriaAcademicaDAO.eliminarSesionDeTutoriaAcademicaPorId(idSesionDeTutoriaAcademica));
    }
    
    @Test
    public void testModificarSesionDeTutoriaAcademica() throws SQLException {
        assertTrue(sesionDeTutoriaAcademicaDAO.modificarFechaDeSesionDeTutoriaAcademica(sesionDeTutoriaAcademica1));
    }
    @Test
    public void testobtenerSesionDeTutoriaAcademicaPorPeriodoEscolar()throws SQLException{
        assertNotNull(sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorPeriodoEscolar(8));
    }
}
