package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.ProgramaEducativo;
import uv.fei.tutorias.domain.TutorAcademico;

// author @liu
public class EstudianteDAOTest {

    // Test of findEstudianteByName method, of class EstudianteDAO.
    @Test
    public void testFindEstudianteByName() {
        System.out.println("findEstudianteByName");
        // ¿POR QUÉ ME MARCA BIEN LA PRUEBA?
        String searchName = "MAX";
        Persona personaEstudiante1 = new Persona("JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico1 = new Persona("MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico1 = new TutorAcademico(personaTutorAcademico1);
        ProgramaEducativo programaEducativo1 = new ProgramaEducativo("INGENIERÍA DE SOFTWARE");
        Estudiante estudianteEsperado1 = new Estudiante("S20015728", personaEstudiante1, tutorAcademico1, programaEducativo1);
        List<Estudiante> estudiantesEsperados = new ArrayList<>();
        estudiantesEsperados.add(estudianteEsperado1);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        List<Estudiante> estudiantesObtenidos = estudianteDao.findEstudianteByName(searchName);
        boolean listasIguales = true;
        for (int i=0; i<estudiantesObtenidos.size(); i++) {
            if (estudiantesObtenidos.get(i).equals(estudiantesEsperados.get(i)) == false) {
                listasIguales = false;
            }
        }
        assertTrue(listasIguales);
    }
    
    // Test of findEstudianteById method, of class EstudianteDAO.
    @Test
    public void testFindEstudianteById() {
        System.out.println("findEstudianteById");
        int idEstudiante = 1;
        Persona personaEstudiante = new Persona("JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico = new Persona("MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo("INGENIERÍA DE SOFTWARE");
        Estudiante estudianteEsperado = new Estudiante("S20015728", personaEstudiante, tutorAcademico, programaEducativo);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        Estudiante estudianteObtenido = estudianteDao.findEstudianteById(idEstudiante);
        assertTrue(estudianteEsperado.equals(estudianteObtenido));
    }

    // Test of addEstudiante method, of class EstudianteDAO.
    @Test
    public void testAddEstudiante() {
        System.out.println("addEstudiante");
        Persona personaEstudiante = new Persona("JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ","zS20015728@estudiantes.uv.mx","lesterhero2002@gmail.com");
        Persona personaTutorAcademico = new Persona("MAX WILLIAM","MILLAN","MARTINEZ","mmillan@uv.mx","maxMillan@gmail.com");
        TutorAcademico tutorAcademico = new TutorAcademico(1, personaTutorAcademico);
        ProgramaEducativo programaEducativo = new ProgramaEducativo(1, "INGENIERÍA DE SOFTWARE");
        Estudiante estudiante = new Estudiante("S20015728", personaEstudiante, tutorAcademico, programaEducativo);
        EstudianteDAO estudianteDao = new EstudianteDAO();
        boolean result = estudianteDao.addEstudiante(estudiante);
        assertTrue(result);
    }

    // Test of deleteEstudianteById method, of class EstudianteDAO.
    @Test
    public void testDeleteEstudianteById() {
        System.out.println("deleteEstudianteById");
        int idEstudiante = 7;
        EstudianteDAO estudianteDao = new EstudianteDAO();
        boolean result = estudianteDao.deleteEstudianteById(idEstudiante);
        assertFalse(result);
    }
}
