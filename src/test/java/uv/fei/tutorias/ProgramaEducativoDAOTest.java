package uv.fei.tutorias;

import uv.fei.tutorias.bussinesslogic.ProgramaEducativoDAO;
import uv.fei.tutorias.domain.ProgramaEducativo;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class ProgramaEducativoDAOTest {
    
    ProgramaEducativo programaEducativoNuevo;
    ProgramaEducativo programaEducativo1;
    ProgramaEducativo programaEducativo2;
    ProgramaEducativoDAO programaEducativoDAO;
    
    @Before
    public void inicializar() {
        programaEducativoNuevo = new ProgramaEducativo("");
        programaEducativo1 = new ProgramaEducativo("INGENIERÍA DE SOFTWARE");
        programaEducativo2 = new ProgramaEducativo("TECNOLOGÍAS COMPUTACIONALES");
        programaEducativoDAO = new ProgramaEducativoDAO();
    }

    @Test
    public void testObtenerProgramasEducativos() throws SQLException {
        ArrayList<ProgramaEducativo> programasEducativosEsperados = new ArrayList<>();
        programasEducativosEsperados.add(programaEducativo1);
        programasEducativosEsperados.add(programaEducativo2);
        ArrayList<ProgramaEducativo> programasEducativosObtenidos = new ArrayList<>();
        programasEducativosObtenidos.addAll(programaEducativoDAO.obtenerProgramasEducativos());
        assertTrue(programasEducativosEsperados.equals(programasEducativosObtenidos));
    }
    
    @Test
    public void testObtenerProgramaEducativoPorId() throws SQLException {
        int idProgramaEducativo = 1;
        ProgramaEducativo programaEducativoObtenido = programaEducativoDAO.obtenerProgramaEducativoPorId(idProgramaEducativo);
        assertTrue(programaEducativo1.equals(programaEducativoObtenido));
    }
}
