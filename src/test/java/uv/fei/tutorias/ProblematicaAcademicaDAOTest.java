package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ProblematicaAcademicaDAOTest {
    @Test
    public void addProblematicaAcademica(){
        System.out.println("problematicaAcademicaDAO.addProblematicaAcademica()");
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica("No entraba a clases", 1);
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        boolean result = problematicaAcademicaDAO.addProblematicaAcademica(problematicaAcademica);
        assertTrue(result);
    }

    @Test
    public void buscarProblematicaAcademicaPorDescripcion(){
        System.out.println("problematicaAcademicaDAO.findProblematicaAcademicaByDescription()");
        String searchname = "No entraba a clases";
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica(2,searchname,1);
        List<ProblematicaAcademica> expectedResult = new ArrayList<>();
        expectedResult.add(problematicaAcademica);
        List<ProblematicaAcademica> result = problematicaAcademicaDAO.findProblematicaAcademicaByDescription("No entraba a clases");
        boolean listasIguales = false;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(expectedResult.get(i))){
                listasIguales=true;
            }
        }
        for (ProblematicaAcademica pA :
                result) {
            System.out.println(pA.getIdProblematicaAcademica() +" " + pA.getDescripcion() + " " + pA.getIdExperienciaEducativa());
        }  for (ProblematicaAcademica pA :
                expectedResult) {
            System.out.println(pA.getIdProblematicaAcademica() +" " + pA.getDescripcion() + " " + pA.getIdExperienciaEducativa());
        }
        assertTrue(listasIguales);
    }

    @Test
    public void deleteProblematicaAcademicaById() {
        System.out.println("problematicaAcademmicaDAO.deleteProblematicaAcademicaById()");
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        boolean result = problematicaAcademicaDAO.deleteProblematicaAcademicaById(2);
        assertTrue(result);
    }

    @Test
    public void findProblematicaAcademicaById(){
        System.out.println(" ProblematicaAcdemicaDAO.findProblematicaAcademicById");
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica(4,"No entraba a clases",1);
        assertTrue(problematicaAcademica.equals(problematicaAcademicaDAO.findProblematicaAcademicaById(4)));
    }



}
