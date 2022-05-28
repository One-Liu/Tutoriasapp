package uv.fei.tutorias;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class PersonaDAOTest {
    private Persona persona;
    private PersonaDAO personaDAO;

    @Before
    public void inicio(){
        persona = new Persona("Mariano","Jasso","Gutierrez");
        personaDAO = new PersonaDAO();
    }

    @Test
    public void findPersonasByName() {
        MatcherAssert.assertThat(personaDAO.findPersonasByName("Luz"), not(IsEmptyCollection.empty()));

    }

    @Test
    public void findPersonaById() {
        persona.setIdPersona(36);
        assertEquals(personaDAO.findPersonaById(36),persona);

    }

    @Test
    public void addPersona() {
        assertTrue(personaDAO.addPersona(persona));
    }

    @Test
    public void deletePersonaById() {
        assertTrue(personaDAO.deletePersonaById(36));

    }

    @Test
    public void addpersonaReturnId(){
        assertEquals(37,personaDAO.addPersonaReturnId(persona));

    }

}