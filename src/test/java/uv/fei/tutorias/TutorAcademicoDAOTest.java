package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.TutorAcademico;
import uv.fei.tutorias.domain.Persona;
import java.util.ArrayList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import uv.fei.tutorias.domain.Usuario;

// author @liu

public class TutorAcademicoDAOTest {
    private TutorAcademico tutorAcademicoNuevo;
    private Persona persona1;
    private TutorAcademico tutorAcademico1;
    private TutorAcademicoDAO tutorAcademicoDAO;
    
    @Before
    public void init(){
        tutorAcademicoNuevo = new TutorAcademico("","","");
        persona1 = new Persona("MAX WILLIAM","MILLÁN","MARTÍNEZ");
        tutorAcademicoDAO = new TutorAcademicoDAO();
    }

    @Test
    public void testFindTutorAcademicoByName() {
        String searchName = "MARÍA";
        ArrayList<TutorAcademico> tutoresAcademicosEsperados = new ArrayList<>();
        tutoresAcademicosEsperados.add(tutorAcademico1);
        ArrayList<TutorAcademico> tutoresAcademicosObtenidos = tutorAcademicoDAO.findTutoresAcademicosByName(searchName);
        assertTrue(tutoresAcademicosEsperados.equals(tutoresAcademicosObtenidos));
    }

    @Test
    public void testFindTutorAcademicoById() {
        int idTutorAcademico = 1;
        TutorAcademico tutorAcademicoObtenido = tutorAcademicoDAO.findTutorAcademicoById(idTutorAcademico);
        assertTrue(tutorAcademico1.equals(tutorAcademicoObtenido));
    }
    
    @Test
    public void testAddTutorAcademico(){
        assertTrue(tutorAcademicoDAO.addTutorAcademico(tutorAcademicoNuevo));
    }

    @Test
    public void testDeleteTutorAcademicoById() {
        int idTutorAcademico = 0;
        assertFalse(TutorAcademicoDAO.deleteTutorAcademicoById(idTutorAcademico));
    }
}
