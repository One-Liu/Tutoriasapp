package uv.fei.tutorias;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.EstudianteProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.EstudianteProblematicaAcademica;


import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class EstudianteProblematicaAcademicaDAOTest {
    private EstudianteProblematicaAcademica estudianteproblematicaAcademica;
    private EstudianteProblematicaAcademicaDAO estudianteProblematicaAcademicaDAO;
    @Before
    public void inicio(){
        estudianteproblematicaAcademica = new  EstudianteProblematicaAcademica(1,1);
        estudianteProblematicaAcademicaDAO = new EstudianteProblematicaAcademicaDAO();

    }

    @Test
    public void addEstudianteProblematicaAcademicaDAO(){
        assertTrue(estudianteProblematicaAcademicaDAO.addEstudianteProblematicaAcademica(estudianteproblematicaAcademica));
    }
    @Test
    public void deleteEstudianteProblematicaAcademicaDAO(){
        assertTrue(estudianteProblematicaAcademicaDAO.deleteEstudinateProblematicaAcademicaById(2));
    }
    //este test busca comprobar que una lista no este vacia con la implementacion de codigo de Hamcrest
    @Test
    public void findestudiantesProblematicaAcademica(){
        MatcherAssert.assertThat(estudianteProblematicaAcademicaDAO.findEstudiantesProblematicaAcademicaById(1), not(IsEmptyCollection.empty()));
    }

}
