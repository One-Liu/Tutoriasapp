package uv.fei.tutorias;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

// author @liu
public class SesionDeTutoriaAcademicaDAOTest {
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademicaNueva;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica1;
    private SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO;
    
    @Before
    public void setUp() {
        sesionDeTutoriaAcademicaNueva = new SesionDeTutoriaAcademica("",0);
        sesionDeTutoriaAcademica1 = new SesionDeTutoriaAcademica("2022-03-09",1);
        sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    }

    @Test
    public void testFindSesionesDeTutoriaAcademicaByFecha() {
        String searchDate = "2022";
        ArrayList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademicaEsperadas = new ArrayList<>();
        sesionesDeTutoriaAcademicaEsperadas.add(sesionDeTutoriaAcademica1);
        ArrayList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademicaObtenidas = sesionDeTutoriaAcademicaDAO.findSesionesDeTutoriaAcademicaByFecha(searchDate);
        assertTrue(sesionesDeTutoriaAcademicaEsperadas.equals(sesionesDeTutoriaAcademicaObtenidas));
    }

    @Test
    public void testFindSesionDeTutoriaAcademicaById() {
        int idSesionDeTutoriaAcademica = 1;
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaObtenida = sesionDeTutoriaAcademicaDAO.findSesionDeTutoriaAcademicaById(idSesionDeTutoriaAcademica);
        assertTrue(sesionDeTutoriaAcademica1.equals(sesionDeTutoriaAcademicaObtenida));
    }
    
    @Test
    public void testAddSesionDeTutoriaAcademica() {
        assertTrue(sesionDeTutoriaAcademicaDAO.addSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaNueva));
    }

    @Test
    public void testDeleteSesionDeTutoriaAcademicaById() {
        int idSesionDeTutoriaAcademica = 0;
        assertFalse(sesionDeTutoriaAcademicaDAO.deleteSesionDeTutoriaAcademicaById(idSesionDeTutoriaAcademica));
    }
}
