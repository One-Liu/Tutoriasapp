package uv.fei.tutorias;

import domain.Persona;
import domain.TutorAcademico;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class TutorAcademicoDAOTest {
    
    private TutorAcademico tutorAcademicoNuevo;
    private Persona persona1;
    private TutorAcademico tutorAcademico1;
    private TutorAcademicoDAO tutorAcademicoDAO;
    
    @Before
    public void inicializar(){
        tutorAcademicoNuevo = new TutorAcademico("","","");
        persona1 = new Persona("MAX WILLIAM","MILLÁN","MARTÍNEZ");
        tutorAcademicoDAO = new TutorAcademicoDAO();
    }

    @Test
    public void testObtenerTutorAcademico() throws SQLException {
        ArrayList<TutorAcademico> tutoresAcademicosEsperados = new ArrayList<>();
        tutoresAcademicosEsperados.add(tutorAcademico1);
        ArrayList<TutorAcademico> tutoresAcademicosObtenidos = tutorAcademicoDAO.obtenerTutoresAcademicos();
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
}
