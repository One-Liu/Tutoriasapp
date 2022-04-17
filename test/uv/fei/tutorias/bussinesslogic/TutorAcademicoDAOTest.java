package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class TutorAcademicoDAOTest {
    @Test
    public void testFindTutoresAcademicosByName() {
        System.out.println("findTutoresAcademicosByName");
        String searchName = "A";
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        List<TutorAcademico> tutoresAcademicosEsperados = new ArrayList<>();
        Persona personaTutorAcademico1 = new Persona("MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico1 = new TutorAcademico(personaTutorAcademico1);
        tutoresAcademicosEsperados.add(tutorAcademico1);
        Persona personaTutorAcademico2 = new Persona("ANGEL JUAN","SANCHEZ","GARCIA","angesanchez@uv.mx","angelSanchez@gmail.com");
        TutorAcademico tutorAcademico2 = new TutorAcademico(personaTutorAcademico2);
        tutoresAcademicosEsperados.add(tutorAcademico2);
        List<TutorAcademico> tutoresAcademicosObtenidos = tutorAcademicoDao.findTutoresAcademicosByName(searchName);
        boolean listasIguales = true;
        for (int i=0; i<tutoresAcademicosObtenidos.size(); i++) {
            if (tutoresAcademicosObtenidos.get(i).equals(tutoresAcademicosEsperados.get(i)) == false) {
                listasIguales = false;
            }
        }
        assertTrue(listasIguales);
    }
    
    @Test
    public void testFindTutorAcademicoById() {
        System.out.println("findTutorAcademicoById");
        int idTutorAcademico = 2;
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        Persona personaTutorAcademicoEsperado = new Persona("ANGEL JUAN","SANCHEZ","GARCIA","angesanchez@uv.mx","angelSanchez@gmail.com");
        TutorAcademico tutorAcademicoEsperado = new TutorAcademico(personaTutorAcademicoEsperado);
        TutorAcademico tutorAcademicoObtenido = tutorAcademicoDao.findTutorAcademicoById(idTutorAcademico);
        assertTrue(tutorAcademicoEsperado.equals(tutorAcademicoObtenido));
    }
    
    @Test
    public void testAddTutorAcademico() {
        System.out.println("addTutorAcademico");
        Persona personaTutorAcademico = new Persona("TUTOR1","DE","PRUEBA","tutor1@uv.mx","tutorPrueba1@gmail.com");
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        boolean result = tutorAcademicoDao.addTutorAcademico(personaTutorAcademico);
        assertTrue(result);
    }

    @Test
    public void testDeleteTutorAcademicoById() {
        System.out.println("deleteTutorAcademicoById");
        int idTutorAcademico = 7;
        TutorAcademicoDAO tutorAcademicoDao = new TutorAcademicoDAO();
        boolean result = tutorAcademicoDao.deleteTutorAcademicoById(idTutorAcademico);
        assertFalse(result);
    }
}
