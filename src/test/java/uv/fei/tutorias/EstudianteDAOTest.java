package uv.fei.tutorias;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Persona persona2;
    private Estudiante estudiante2;
    private Persona persona3;
    private Estudiante estudiante3;
    private Persona persona4;
    private Estudiante estudiante4;
    private EstudianteDAO estudianteDAO;
    
    @Before
    public void inicializar() {
        personaNueva = new Persona("VICTOR AUGUSTO","CUEVAS","BARRADAS",4);
        estudianteNuevo = new Estudiante("S20020853",personaNueva);
        persona1 = new Persona("JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ",4);
        estudiante1 = new Estudiante(1,"S20015728",false,2,persona1);
        persona2 = new Persona("PAULO CÉSAR","HERNÁNDEZ","ROSADO",4);
        estudiante2 = new Estudiante(2,"S20020854",false,2,persona2);
        persona3 = new Persona("ALFREDO","TORRES","ESTOPIER",4);
        estudiante3 = new Estudiante(3,"S19014028",false,2,persona3);
        persona4 = new Persona("VICTOR AUGUSTO","CUEVAS","BARRADAS",4);
        estudiante4 = new Estudiante(4,"S20020853",false,0,persona4);
        estudianteDAO = new EstudianteDAO();
    }

    @Test
    public void testObtenerEstudiantes() throws SQLException {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudiante1);
        estudiantesEsperados.add(estudiante2);
        estudiantesEsperados.add(estudiante3);
        estudiantesEsperados.add(estudiante4);
        ObservableList<Estudiante> estudiantesObtenidos = FXCollections.observableArrayList();
        estudiantesObtenidos.addAll(estudianteDAO.obtenerEstudiantes());
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
    public void testModificarAsignacionDeTutor() throws SQLException {
        assertTrue(estudianteDAO.modificarAsignacionDeTutor(estudiante1));
    }
    
    @Test
    public void testObtenerEstudiantesDeTutor() throws SQLException {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudiante1);
        estudiantesEsperados.add(estudiante2);
        estudiantesEsperados.add(estudiante3);
        ObservableList<Estudiante> estudiantesObtenidos = FXCollections.observableArrayList();
        estudiantesObtenidos.addAll(estudianteDAO.obtenerEstudiantesDeTutor(2));
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testObtenerEstudiantesSinTutorAsignado() throws SQLException {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudiante4);
        ObservableList<Estudiante> estudiantesObtenidos = FXCollections.observableArrayList();
        estudiantesObtenidos.addAll(estudianteDAO.obtenerEstudiantesSinTutorAsignado());
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
    
    @Test
    public void testObtenerEstudiantesConTutorAsignado() throws SQLException {
        ObservableList<Estudiante> estudiantesEsperados = FXCollections.observableArrayList();
        estudiantesEsperados.add(estudiante1);
        estudiantesEsperados.add(estudiante2);
        estudiantesEsperados.add(estudiante3);
        ObservableList<Estudiante> estudiantesObtenidos = FXCollections.observableArrayList();
        estudiantesObtenidos.addAll(estudianteDAO.obtenerEstudiantesConTutorAsignado());
        assertTrue(estudiantesEsperados.equals(estudiantesObtenidos));
    }
}
