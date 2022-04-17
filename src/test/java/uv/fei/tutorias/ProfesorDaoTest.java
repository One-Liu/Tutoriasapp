package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ProfesorDao;
import uv.fei.tutorias.domain.Persona;
import static org.junit.Assert.assertTrue;

public class ProfesorDaoTest {

    @Test
    public void addProfesor() {
        System.out.println("profesorDao.addProfesor");
        ProfesorDao profesorDao = new ProfesorDao();
        Persona persona1 = new Persona("Paulo","Hernandez","Rosado","pauloCesar@gmail.com","zs20020854");
        Persona persona = new Persona(24,"Tutor","Frijolito","Macho","2112","ZS20020854@ESTUDINTES.UV.MX");
        boolean result = profesorDao.addProfesor(persona1);
        assertTrue(result);
    }

    @Test
    public void findProfesoresByName() {
    }

    @Test
    public void findProfesorById() {
    }



    @Test
    public void deleteProfesorById() {

    }
}