package uv.fei.tutorias;

import java.sql.SQLException;
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
    private Persona personaNueva;
    private Profesor profesorNuevo;
    private Persona persona1;
    private Profesor profesor1;
    private Persona persona2;
    private Profesor profesor2;
    private Persona persona3;
    private Profesor profesor3;
    private Persona persona4;
    private Profesor profesor4;
    private Persona persona5;
    private Profesor profesor5;
    private ProfesorDAO profesorDAO;
    
    @Before
    public void init(){
        personaNueva = new Persona("Rosa","Maria","Rivera",4);
        profesorNuevo = new Profesor(personaNueva);
        persona1 = new Persona("MARÍA DE LOS ÁNGELES","ARENAS","VALDÉZ",4);
        profesor1 = new Profesor(1,persona1);
        persona2 = new Persona("MAX WILLIAM","MILLÁN","MARTÍNEZ",4);
        profesor2 = new Profesor(2,persona2);
        persona3 = new Persona("SERGIO LUIS","CASTILLO","VALERIO",4);
        profesor3 = new Profesor(3,persona3);
        persona4 = new Persona("JUAN CARLOS","PÉREZ","ARRIAGA",4);
        profesor4 = new Profesor(4,persona4);
        persona5 = new Persona("ÁNGEL JUAN","SÁNCHEZ","GARCÍA",4);
        profesor5 = new Profesor(5,persona5);
        profesorDAO = new ProfesorDAO();
    }
    
    @Test
    public void agregarPersonaYProfesor() throws SQLException {
        assertTrue(profesorDAO.addProfesorandPersona(profesorNuevo));
    }
    @Test
    public void eliminarProfesor(){
        assertTrue(profesorDAO.deleteProfesorById(27));
    }

    @Test
    public void buscarProfesoresPorNombre(){
        MatcherAssert.assertThat(profesorDAO.findProfesoresByName("MAX"), not(IsEmptyCollection.empty()));
    }
    @Test
    public void BuscarProfesoresPorId(){
        assertTrue(profesorDAO.findProfesorById(1).equals(profesor1));
    }
}