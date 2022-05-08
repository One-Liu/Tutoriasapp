package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.PersonaDAO;
import uv.fei.tutorias.domain.Persona;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PersonaDAOTest {

    @Test
    public void findPersonsByName() {
        System.out.println("personaDao.findPersonsByName");
        String searchName = "Dani";
        PersonaDAO personaDAO = new PersonaDAO();
        List<Persona> expectedResult = new ArrayList<>();
        Persona persona1 = new Persona(7,"Daniela","Gutierrez", "Hernandez", "cxczxc@sdasd.com"," ");
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
            System.out.println(persona.getIdPersona() + " " + persona.getNombre() + " " + persona.getApellidoPaterno()
                    + " " + persona.getApellidoMaterno() + " " + persona.getCorreoPersonal() + " " + persona.getCorreoInstitucional() + "\n" );

        }
        System.out.println("RESULTADOS ESPERADOS \n");
        for (Persona persona :
                expectedResult) {
            System.out.println(persona.getIdPersona() + " " + persona.getNombre() + " " + persona.getApellidoPaterno()
                    + " " + persona.getApellidoMaterno() + " " + persona.getCorreoPersonal() + " " + persona.getCorreoInstitucional() + "\n" );
        }
        assertTrue(listasIguales);


    }

    @Test
    public void findPersonById() {
        System.out.println("personaDao.findPersonById");
        Persona persona = new Persona("Paulo", "Gutierrez", "Rosado", "zs20020854@estudiantes.uv.mx", "paulocesarhero@gmail.com");
        PersonaDAO personaDAO = new PersonaDAO();
        assertTrue(persona.equals(personaDAO.findPersonById(77)));
    }

    @Test
    public void addPerson() {
        System.out.println("personaDao.addPerson");
        Persona persona = new Persona("Joshua", "Hernandez", "Rocord", "zs20020854@estudiantes.uv.mx", "paulocesarhero@gmail.com");
        PersonaDAO personaDAO = new PersonaDAO();
        boolean result = personaDAO.addPerson(persona);
        assertTrue(result);
    }

    @Test
    public void deletePersonById() {
        System.out.println("personaDao.deletePersonByID");
        PersonaDAO personaDAO = new PersonaDAO();
        boolean result = personaDAO.deletePersonById(75);
        assertTrue(result);
    }
    @Test
    public void addPersonReturnId(){
        System.out.println("personaDao.addPersonReturnId");
        Persona persona = new Persona("Paulo", "Blandon", "Rocord", "zs20020854@estudiantes.uv.mx", "paulocesarhero@gmail.com");
        PersonaDAO personaDAO = new PersonaDAO();
        int result = personaDAO.addPersonaReturnId(persona);
        assertEquals(result, 79);

    }

    @Test
    public void findIdPersona(){
        System.out.println("personaDao.findIdPersona");
        Persona persona = new Persona("Daniela", "Gutierrez" ,"Hernandez" ,"cxczxc@sdasd.com","");
        PersonaDAO personaDAO = new PersonaDAO();
        int result = personaDAO.findIdPersona(persona);
        assertEquals(result, 7);
    }







}