package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.TutorAcademico;
import uv.fei.tutorias.domain.Persona;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.junit.Assert.*;

// author @liu
public class TutorAcademicoDAOTest {
    private Persona personaNueva;
    private TutorAcademico tutorAcademicoNuevo;
    private Persona persona1;
    private TutorAcademico tutorAcademico1;
    private Persona persona2;
    private TutorAcademico tutorAcademico2;
    private TutorAcademicoDAO tutorAcademicoDAO;
    
    @Before
    public void inicializar(){
        personaNueva = new Persona("","","",4);
        tutorAcademicoNuevo = new TutorAcademico(personaNueva);
        persona1 = new Persona("MAX WILLIAM","MILLÁN","MARTÍNEZ",4);
        tutorAcademico1 = new TutorAcademico(1,persona1);
        persona2 = new Persona("ÁNGEL JUAN","SÁNCHEZ","GARCÍA",4);
        tutorAcademico2 = new TutorAcademico(2,persona2);
        tutorAcademicoDAO = new TutorAcademicoDAO();
    }

    @Test
    public void testObtenerTutoresAcademicos() throws SQLException {
        ObservableList<TutorAcademico> tutoresAcademicosEsperados = FXCollections.observableArrayList();
        tutoresAcademicosEsperados.add(tutorAcademico1);
        tutoresAcademicosEsperados.add(tutorAcademico2);
        ObservableList<TutorAcademico> tutoresAcademicosObtenidos = FXCollections.observableArrayList();
        tutoresAcademicosEsperados.addAll(tutorAcademicoDAO.obtenerTutoresAcademicos());
        assertTrue(tutoresAcademicosEsperados.equals(tutoresAcademicosObtenidos));
    }

    @Test
    public void testObtenerTutorAcademicoPorId() throws SQLException {
        int idTutorAcademico = 1;
        TutorAcademico tutorAcademicoObtenido = tutorAcademicoDAO.obtenerTutorAcademicoPorId(idTutorAcademico);
        assertTrue(tutorAcademico1.equals(tutorAcademicoObtenido));
    }
    
    @Test
    public void testAgregarTutorAcademico() throws SQLException{
        assertTrue(tutorAcademicoDAO.agregarTutorAcademico(tutorAcademicoNuevo));
    }

    @Test
    public void testEliminarTutorAcademicoById() throws SQLException {
        int idTutorAcademico = 0;
        assertFalse(tutorAcademicoDAO.eliminarTutorAcademicoPorId(idTutorAcademico));
    }

    @Test
    public void testModificarTutorAcademico() throws SQLException {
        assertTrue(tutorAcademicoDAO.modificarTutorAcademico(tutorAcademico1));
    }
    
    @Test
    public void testBuscarTutorAcademicoPorElIdDeUsuario()throws SQLException{
        TutorAcademico tutorAcademicoObtenido = tutorAcademicoDAO.buscarTutorAcademicoPorElIdDeUsuario(2);
        assertTrue(tutorAcademico2.equals(tutorAcademicoObtenido));
    }
}
