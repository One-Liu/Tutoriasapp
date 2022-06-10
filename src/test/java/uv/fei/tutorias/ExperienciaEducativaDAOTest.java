package uv.fei.tutorias;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class ExperienciaEducativaDAOTest {
    @Test
    public void agregarExperienciasEducativas(){
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa("Phyton","1234",0);
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        boolean result = experienciaEducativaDAO.agregarExperienciaEducativa(experienciaEducativa);
        assertTrue(result);
    }
    @Test
    public void buscarExperienciasEducativasByName(){
        System.out.println("experienciasEducativasDAO.buscarExperienciasEducativasPorNombre");
        String searchName = "Mate Discretas";
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        List<ExperienciaEducativa> expectedResult = new ArrayList<>();
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa(1, "Mate Discretas",7);
        expectedResult.add(experienciaEducativa);
        List<ExperienciaEducativa> result = experienciaEducativaDAO.buscarExperienciasEducativasPorNombre(searchName);
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
    @Test
    public void deleteExperienciaEducativa(){
        System.out.println("experienciaEducativaDAO.eliminarExperienciaEducativa()");
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        boolean result = experienciaEducativaDAO.eliminarExperienciaEducativa(2);
        assertFalse(result);
    }

    @Test
    public void findExperienciaEducativaById(){
        System.out.println("experienciaEducativaDAO.obtenerExperienciaEducativaPorId()");
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa(1, "Mate Discretas", 7);
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        assertTrue(experienciaEducativa.equals(experienciaEducativaDAO.obtenerExperienciaEducativaPorId(1)));


    }

}
