package uv.fei.tutorias;

import org.junit.Test;

import uv.fei.tutorias.bussinesslogic.SolucionAProblematicaAcademicaDAO;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class SolucionAProblematicaacademicaDAOTest {
    @Test
    public void agregarSolucionAProblematicaAcademica() throws SQLException {
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        assertEquals(true, solucionAProblematicaAcademicaDAO.agregarSolucionProblematicaAcademica("botellita de jerez"));
    }
    @Test
    public void eliminarSolucionAProblematicaAcademica() throws SQLException {
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        assertTrue(solucionAProblematicaAcademicaDAO.eliminarSolucionAProblematicaAcademicaById(1));
    }
    @Test
    public void buscarSolucionAProblematicaAcademica(){
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        assertNotNull(solucionAProblematicaAcademicaDAO.buscarSolucionAProblematicaAcademicaById(1));
    }

}
