package uv.fei.tutorias;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void findExperienciasEducativasByName(){
        System.out.println("experienciasEducativasDAO.findExperienciasEducativasByName");
        String searchName = "Mate Discretas";
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        List<ExperienciaEducativa> expectedResult = new ArrayList<>();
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa(1, "Mate Discretas",7);
        expectedResult.add(experienciaEducativa);
        List<ExperienciaEducativa> result = experienciaEducativaDAO.findExperienciasEducativasByName(searchName);
        boolean listasIguales = false;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(expectedResult.get(i))){
                listasIguales = true;
            }
        }
        for (ExperienciaEducativa eE:
             result) {
            System.out.println(eE.getIdExperienciaEducativa() + " " + eE.getNombre() + " " + eE.getIdProfesor());
        }
        for (ExperienciaEducativa eE :
                expectedResult) {
            System.out.println(eE.getIdExperienciaEducativa() + " " + eE.getNombre() + " " + eE.getIdProfesor());

        }
        assertTrue(listasIguales);
    }
}
