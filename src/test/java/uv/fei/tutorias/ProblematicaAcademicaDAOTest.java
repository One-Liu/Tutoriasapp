package uv.fei.tutorias;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ProblematicaAcademicaDAOTest {
    private ProblematicaAcademicaDAO problematicaAcademicaDAO;
    private ProblematicaAcademica problematicaAcademica ;


    @Before
    public void inicio(){
        problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        problematicaAcademica = new ProblematicaAcademica("Problemas puntualidad del maestro","No entraba a clases", 1,0,1,1);

    }

    @Test
    public void agregarProblematicaAcademica() throws SQLException {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        boolean result = problematicaAcademicaDAO.agregarProblematicaAcademica(problematicaAcademica);
        assertTrue(result);
    }

    @Test
    public void eliminarProblematicaAcademicaById() throws SQLException {
        System.out.println("problematicaAcademmicaDAO.deleteProblematicaAcademicaById()");
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        boolean result = problematicaAcademicaDAO.eliminarProblematicaAcademicaPorId(2);
        assertTrue(result);
    }

    @Test
    public void buscarProblematicaAcademicaById() throws SQLException {
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        assertTrue(problematicaAcademica.equals(problematicaAcademicaDAO.obtenerProblematicaAcademicaPorId(4)));
    }



}
