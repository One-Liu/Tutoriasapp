package uv.fei.tutorias;

import domain.Estudiante;
import domain.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class EstudianteDAOTest {
    
    private Persona personaNueva;
    private Estudiante estudianteNuevo;
    private Persona persona1;
    private Estudiante estudiante1;
    private EstudianteDAO estudianteDAO;
    
    @Before
    public void inicializar() {
        personaNueva = new Persona("","","");
        estudianteNuevo = new Estudiante("",personaNueva,0,0);
        persona1 = new Persona("","","");
        estudiante1 = new Estudiante("",persona1,1,1);
        estudianteDAO = new EstudianteDAO();
    }

    @Test
    public void testObtenerEstudiantes() throws SQLException {
        ArrayList<Estudiante> estudiantesEsperados = new ArrayList<>();
        estudiantesEsperados.add(estudiante1);
        ArrayList<Estudiante> estudiantesObtenidos = estudianteDAO.obtenerEstudiantes();
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testObtenerEstudiantePorId() throws SQLException {
        int idEstudiante = 1;
        Estudiante estudianteObtenido = estudianteDAO.obtenerEstudiantePorId(idEstudiante);
        assertTrue(estudiante1.equals(estudianteObtenido));
    }
    
    @Test
    public void testAgregarEstudiante() throws SQLException {
        assertTrue(estudianteDAO.agregarEstudiante(estudianteNuevo));
    }
    
    @Test
    public void testEliminarEstudiantePorId() throws SQLException {
        int idEstudiante = 7;
        assertFalse(estudianteDAO.eliminarEstudiantePorId(idEstudiante));
    }
    
    @Test
    public void testModificarEstudiante() throws SQLException {
        assertTrue(estudianteDAO.modificarEstudiante(estudiante1));
    }
}
