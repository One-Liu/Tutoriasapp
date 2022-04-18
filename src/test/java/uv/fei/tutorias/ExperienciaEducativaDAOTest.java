package uv.fei.tutorias;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import static org.junit.Assert.*;
public class ExperienciaEducativaDAOTest {
    @Test
    public void addExperienciasEducativas(){
        System.out.println("experienciaEducativaDao.addExperienciaEducativa()");
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa("Mate Discretas",7);
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        boolean result = experienciaEducativaDAO.addExperienciaEducativa(experienciaEducativa);
        assertTrue(result);
    }
}
