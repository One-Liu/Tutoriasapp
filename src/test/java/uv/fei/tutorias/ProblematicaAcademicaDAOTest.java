package uv.fei.tutorias;

import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ProblematicaAcademica;

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

}
