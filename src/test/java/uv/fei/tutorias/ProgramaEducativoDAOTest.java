package uv.fei.tutorias;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.ProgramaEducativoDAO;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class ProgramaEducativoDAOTest {
    ProgramaEducativo programaEducativoNuevo;
    ProgramaEducativo programaEducativo1;
    ProgramaEducativo programaEducativo2;
    ProgramaEducativoDAO programaEducativoDAO;
    
    @Before
    public void init() {
        programaEducativoNuevo = new ProgramaEducativo("");
        programaEducativo1 = new ProgramaEducativo("INGENIERÍA DE SOFTWARE");
        programaEducativo2 = new ProgramaEducativo("TECNOLOGÍAS COMPUTACIONALES");
        programaEducativoDAO = new ProgramaEducativoDAO();
    }

    @Test
    public void testFindProgramasEducativosByName() {
        String searchName = "I";
        ArrayList<ProgramaEducativo> programasEducativosEsperados = new ArrayList<>();
        programasEducativosEsperados.add(programaEducativo1);
        programasEducativosEsperados.add(programaEducativo2);
        ArrayList<ProgramaEducativo> programasEducativosObtenidos = programaEducativoDAO.findProgramasEducativosByName(searchName);
        assertTrue(programasEducativosEsperados.equals(programasEducativosObtenidos));
    }
    
    @Test
    public void testFindProgramaEducativoById() {
        int idProgramaEducativo = 1;
        ProgramaEducativo programaEducativoObtenido = programaEducativoDAO.findProgramaEducativoById(idProgramaEducativo);
        assertTrue(programaEducativo1.equals(programaEducativoObtenido));
    }
    
    @Test
    public void testAddProgramaEducativo() {
        assertTrue(programaEducativoDAO.addProgramaEducativo(programaEducativoNuevo));
    }

    @Test
    public void testDeleteProgramaEducativoById() {
        int idProgramaEducativo = 0;
        assertFalse(programaEducativoDAO.deleteProgramaEducativoById(idProgramaEducativo));
    } 
}
