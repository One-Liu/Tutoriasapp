package uv.fei.tutorias;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.EstudiantesProblematicasAcademicasDAO;
import uv.fei.tutorias.domain.EstudiantesProblematicasAcademicas;


import java.sql.SQLException;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class EstudianteProblematicaAcademicaDAOTest {
    private EstudiantesProblematicasAcademicas estudiantesProblematicasAcademicas;
    private EstudiantesProblematicasAcademicasDAO estudiantesProblematicasAcademicasDAO;
    @Before
    public void inicio(){
        estudiantesProblematicasAcademicas = new EstudiantesProblematicasAcademicas(2,2);
        estudiantesProblematicasAcademicasDAO = new EstudiantesProblematicasAcademicasDAO();

    }

    @Test
    public void agregarEstudianteProblematicaAcademicaDAO() throws SQLException {
        assertTrue(estudiantesProblematicasAcademicasDAO.agregarEstudianteProblematicaAcademica(estudiantesProblematicasAcademicas));
    }
    @Test
    public void eliminarEstudianteProblematicaAcademicaDAO() throws SQLException {
        assertTrue(estudiantesProblematicasAcademicasDAO.eliminarEstudianteProblematicaAcademicaPorId(9));
    }
    //este test busca comprobar que una lista no este vacia con la implementacion de codigo de Hamcrest
    @Test
    public void buscarEstudiantesProblematicaAcademica() throws SQLException {
        MatcherAssert.assertThat(estudiantesProblematicasAcademicasDAO.obtenerEstudianteProblematicaAcademicaPorId(13), not(IsEmptyCollection.empty()));
    }
    @Test
    public void existeEstudianteProblematicaAcademica()throws SQLException{
        assertTrue(estudiantesProblematicasAcademicasDAO.existeEstudianteProblematicaAcademica(estudiantesProblematicasAcademicas));
    }

}
