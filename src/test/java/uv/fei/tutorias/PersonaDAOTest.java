package uv.fei.tutorias;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

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
    public void buscarPersonasPorNombre() {
        MatcherAssert.assertThat(personaDAO.obtenerPersonaPorNombre("Luz"), not(IsEmptyCollection.empty()));

    }

    @Test
    public void findPersonaById() {
        persona.setIdPersona(36);
        assertEquals(personaDAO.obtenerPersonaPorId(36),persona);

    }

    @Test
    public void addPersona() {
        assertEquals(personaDAO.agregarPersona(persona),-1);
    }

    @Test
    public void deletePersonaById() {
        assertTrue(personaDAO.eliminarPersonaPorId(36));

    }

    @Test
    public void addpersonaReturnId(){
        assertEquals(37,personaDAO.agregarPersona(persona));

    }

}