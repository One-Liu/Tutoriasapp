package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class ProgramaEducativoDAOTest {
    
    // Test of findProgramasEducativosByName method, of class ProgramaEducativoDAO.
    @Test
    public void testFindProgramasEducativosByName() {
        System.out.println("findProgramasEducativosByName");
        String searchName = "I";
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        List<ProgramaEducativo> expResult = new ArrayList<>();
        ProgramaEducativo programaEducativo1 = new ProgramaEducativo("INGENIERÍA DE SOFTWARE");
        expResult.add(programaEducativo1);
        ProgramaEducativo programaEducativo2 = new ProgramaEducativo("TECNOLOGÍAS COMPUTACIONALES");
        expResult.add(programaEducativo2);
        List<ProgramaEducativo> result = programaEducativoDao.findProgramasEducativosByName(searchName);
        boolean listasIguales = true;
        for (int i=0; i<result.size(); i++) {
            if (result.get(i).equals(expResult.get(i)) == false) {
                listasIguales = false;
            }
        }
        assertTrue(listasIguales);
    }

    // Test of findProgramaEducativoById method, of class ProgramaEducativoDAO.
    @Test
    public void testFindProgramaEducativoById() {
        System.out.println("findProgramaEducativoById");
        int idProgramaEducativo = 1;
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        ProgramaEducativo programaEducativoEsperado = new ProgramaEducativo("INGENIERÍA DE SOFTWARE");
        ProgramaEducativo programaEducativoObtenido = programaEducativoDao.findProgramaEducativoById(idProgramaEducativo);
        assertTrue(programaEducativoEsperado.equals(programaEducativoObtenido));
    }

    // Test of addProgramaEducativo method, of class ProgramaEducativoDAO.
    @Test
    public void testAddProgramaEducativo() {
        System.out.println("addProgramaEducativo");
        ProgramaEducativo programaEducativo = new ProgramaEducativo("TECNOLOGÍAS COMPUTACIONALES");
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        boolean result = programaEducativoDao.addProgramaEducativo(programaEducativo);
        assertTrue(result);
    }

    // Test of deleteProgramaEducativoById method, of class ProgramaEducativoDAO.
    @Test
    public void testDeleteProgramaEducativoById() {
        System.out.println("deleteProgramaEducativoById");
        int idProgramaEducativo = 0;
        ProgramaEducativoDAO instance = new ProgramaEducativoDAO();
        boolean result = instance.deleteProgramaEducativoById(idProgramaEducativo);
        assertFalse(result);
    }    
}