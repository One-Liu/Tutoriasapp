package uv.fei.tutorias;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class ProfesorDaoTest {
    private Profesor profesor;
    private ProfesorDAO profesorDAO;
    @Before
    public void init(){
        profesor = new Profesor("Rosa","Maria","Rivera");
        profesorDAO = new ProfesorDAO();
    }
    @Test
    public void addPersonaandProfesor(){
        assertTrue(profesorDAO.addProfesorandPersona(profesor));
    }
    @Test
    public void deleteProfesor(){
        assertTrue(profesorDAO.deleteProfesorById(27));
    }
    @Test
    public void findProfesoresByName(){
        MatcherAssert.assertThat(profesorDAO.findProfesoresByName("Rosa"), not(IsEmptyCollection.empty()));
    }
    @Test
    public void findProfesorById(){
        assertNotNull(profesorDAO.findProfesorById(28));
        System.out.println(profesorDAO.findProfesorById(28));
    }
}