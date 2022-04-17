package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ProfesorDao;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Profesor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfesorDaoTest {

    @Test
    public void addProfesor() {
        System.out.println("profesorDao.addProfesor");
        ProfesorDao profesorDao = new ProfesorDao();
        Persona persona1 = new Persona("Maria","Jose","Fernandez","zdasd12@uv.mx","Majod@gmail.com");
        boolean result = profesorDao.addProfesor(persona1);
        assertTrue(result);
    }

    @Test
    public void findProfesorById() {
        System.out.println("profesorDao.findProfesorById");
        ProfesorDao profesorDao = new ProfesorDao();
        Persona expected = new Persona("Jose","Lopez","Perez","zs2ds122@uv.mx","jus2d@gmail.com");
        Persona result = profesorDao.findProfesorById(7).getPersona();
        assertTrue(expected.equals(result));
    }

    @Test
    public void findProfesoresByName() {
        System.out.println("profesorDao.findProfesorsByname");
        ProfesorDao profesorDao = new ProfesorDao();
        Persona expected1 = new Persona("Jose","Lopez","Perez","zs2ds122@uv.mx","jus2d@gmail.com");
        Persona expected2 = new Persona("Jose","Hernandez","Gutierrez","zs2saa@uv.mx","jusdsdsd@gmail.com");
        List<Persona> listExpected = new ArrayList<>();
        listExpected.add(expected1);
        listExpected.add(expected2);
        List<Profesor> result = profesorDao.findProfesoresByName("Jose");
        boolean listasIguales = false;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getPersona().equals(listExpected.get(i))){
                listasIguales = true;
            }
        }
        for (Persona persona :
                listExpected) {
            System.out.println(persona.getIdPersona() + " " + persona.getNombre() + " " + persona.getApellidoPaterno()
                    + " " + persona.getApellidoMaterno() + " " + persona.getCorreoPersonal() + " " + persona.getCorreoInstitucional() + "\n" );

        }for (Profesor persona :
                result) {
            System.out.println(persona.getPersona().getIdPersona() + " " + persona.getPersona().getNombre() + " " + persona.getPersona().getApellidoPaterno()
                    + " " + persona.getPersona().getApellidoMaterno() + " " + persona.getPersona().getCorreoPersonal() + " " + persona.getPersona().getCorreoInstitucional() + "\n" );

        }

        assertTrue(listasIguales);



    }



    @Test
    public void deleteProfesorById() {
        System.out.println("profesorDao.eliminarProfesor");
        ProfesorDao profesorDao = new ProfesorDao();
        profesorDao.deleteProfesorById(9);
    }
}