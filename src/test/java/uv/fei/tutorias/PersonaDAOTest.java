package uv.fei.tutorias;

import java.sql.SQLException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class PersonaDAOTest {
    private Persona personaNueva;
    private Persona persona1;
    private Persona persona2;
    private Persona persona3;
    private Persona persona4;
    private Persona persona5;
    private Persona persona6;
    private Persona persona7;
    private Persona persona8;
    private Persona persona9;
    private Persona persona10;
    private PersonaDAO personaDAO;

    @Before
    public void inicio(){
        personaNueva = new Persona("","","",4);
        persona1 = new Persona(1,"MARÍA DE LOS ÁNGELES","ARENAS","VALDÉZ",4);
        persona2 = new Persona(2,"MAX WILLIAM","MILLÁN","MARTÍNEZ",4);
        persona3 = new Persona(3,"SERGIO LUIS","CASTILLO","VALERIO",4);
        persona4 = new Persona(4,"JUAN CARLOS","PÉREZ","ARRIAGA",4);
        persona5 = new Persona(5,"PAULO CÉSAR","HERNÁNDEZ","ROSADO",4);
        persona6 = new Persona(6,"JOSHUA ELIUD","HERNÁNDEZ","SUÁREZ",4);
        persona7 = new Persona(7,"ALFREDO","TORRES","ESTOPIER",4);
        persona8 = new Persona(8,"ÁNGEL JUAN","SÁNCHEZ","GARCÍA",4);
        persona9 = new Persona(9,"JORGE OCTAVIO","OCHARÁN","HERNÁNDEZ",4);
        persona10 = new Persona(10,"VICTOR AUGUSTO","CUEVAS","BARRADAS",4);
        personaDAO = new PersonaDAO();
    }


    @Test
    public void obtenerPersonaPorId() throws SQLException {
        personaNueva.setIdPersona(11);
        assertEquals(personaDAO.obtenerPersonaPorId(11),personaNueva);

    }

    @Test
    public void agregarPersona() throws SQLException {
        assertEquals(personaDAO.agregarPersona(personaNueva),12);
    }

    @Test
    public void eliminarPersonaPorId() throws SQLException {
        assertTrue(personaDAO.eliminarPersonaPorId(12));

    }

}