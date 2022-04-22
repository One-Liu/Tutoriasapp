package uv.fei.tutorias;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import uv.fei.tutorias.bussinesslogic.SolucionAProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;

public class SolucionAProblematicaacademicaDAOTest {
    @Test
    public void addSolucionAProblematicaAcademica(){
        SolucionAProblematicaAcademica solucionAProblematicaAcademica = new SolucionAProblematicaAcademica(1,"Despediremos al profesor");
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        assertTrue(solucionAProblematicaAcademicaDAO.addSolucionProblematicaAcademica(solucionAProblematicaAcademica));
    }
    @Test
    public void deleteSolucionAProblematicaAcademica(){
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        assertTrue(solucionAProblematicaAcademicaDAO.deleteSolucionAProblematicaAcademicaById(1));
    }
    @Test
    public void serchSolucionAProblematicaAcademica(){
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        assertNotNull(solucionAProblematicaAcademicaDAO.findSolucionAProblematicaAcademicaById(1));
    }

}
