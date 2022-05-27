package uv.fei.tutorias;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.Persona;

// author @liu
public class EstudianteDAOTest {
    private Persona personaNueva;
    private Estudiante estudianteNuevo;
    private Persona persona1;
    private Estudiante estudiante1;
    private EstudianteDAO estudianteDAO;
    
    @Before
    public void init() {
        personaNueva = new Persona("Mario","Peña","Gonzales");
        estudianteNuevo = new Estudiante("s20020400",personaNueva,0,0);
        persona1 = new Persona("","","");
        estudiante1 = new Estudiante("",persona1,1,1);
        estudianteDAO = new EstudianteDAO();
    }

    @Test
    public void testFindEstudianteByName() {
        String searchName = "HERNÁNDEZ";
        ArrayList<Estudiante> estudiantesEsperados = new ArrayList<>();
        estudiantesEsperados.add(estudiante1);
        ArrayList<Estudiante> estudiantesObtenidos = estudianteDAO.findEstudianteByName(searchName);
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testFindEstudianteById() {
        int idEstudiante = 1;
        Estudiante estudianteObtenido = estudianteDAO.findEstudianteById(idEstudiante);
        assertTrue(estudiante1.equals(estudianteObtenido));
    }
    
    @Test
    public void testAddEstudiante() {
        assertTrue(estudianteDAO.addEstudiante(estudianteNuevo));
    }
    
    @Test
    public void testDeleteEstudianteById() {
        int idEstudiante = 7;
        assertFalse(estudianteDAO.deleteEstudianteById(idEstudiante));
    }
}
