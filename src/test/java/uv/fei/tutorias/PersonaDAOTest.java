package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonaDAOTest {

    @Test
    public void findPersonById() {
        System.out.println("personaDao.findPersonById");
        Persona persona = new Persona(27,"Paulo", "Hernandez", "Rosado", "2282522839", "zs20020854");
        PersonaDAO personaDAO = new PersonaDAO();
        assertTrue(persona.equals(personaDAO.findPersonById(27)));
    }

    @Test
    public void addPerson() {
        System.out.println("personaDao.addPerson");
        Persona persona = new Persona("Paulo", "Gutierrez", "Rosado", "2282522839", "zs20020854@estudiantes.uv.mx", "paulocesarhero@gmail.com");
        PersonaDAO personaDAO = new PersonaDAO();
        boolean result = personaDAO.addPerson(persona);
        assertTrue(result);
    }

    @Test
    public void deletePersonById() {
        System.out.println("personaDao.deletePersonByID");
        PersonaDAO personaDAO = new PersonaDAO();
        boolean result = personaDAO.deletePersonById(74);
        assertTrue(result);


    }


    @Test
    public void findPersonsByName() {
        System.out.println("personaDao.findPersonsByName");
        String searchName = "Dani";
        PersonaDAO personaDAO = new PersonaDAO();
        List<Persona> expectedResult = new ArrayList<>();
        Persona persona1 = new Persona(7,"Daniela","Hernandez", "Gutierrez", "2112", "cxczxc@sdasd.com");
        expectedResult.add(persona1);
        List<Persona> result = personaDAO.findPersonsByName(searchName);
        boolean listasIguales = false;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(expectedResult.get(i))){
                listasIguales = true;
            }
        }
        for (Persona persona :
                result) {
            System.out.println(persona.getNombre());
            System.out.println(persona.getIdPersona());
        }for (Persona persona :
                expectedResult) {
            System.out.println(persona.getNombre());
            System.out.println();
        }
        assertTrue(listasIguales);


    }



}