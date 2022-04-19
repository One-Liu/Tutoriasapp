package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ProgramaEducativo;

// author @liu
public class ProgramaEducativoDAOTest {
    
    public ProgramaEducativoDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindProgramasEducativosByName() {
        System.out.println("findProgramasEducativosByName");
        String searchName = "I";
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        List<ProgramaEducativo> programasEducativosEsperados = new ArrayList<>();
        ProgramaEducativo programaEducativo1 = new ProgramaEducativo(1,"INGENIERÍA DE SOFTWARE");
        programasEducativosEsperados.add(programaEducativo1);
        ProgramaEducativo programaEducativo2 = new ProgramaEducativo(2,"TECNOLOGÍAS COMPUTACIONALES");
        programasEducativosEsperados.add(programaEducativo2);
        List<ProgramaEducativo> programasEducativosObtenidos = programaEducativoDao.findProgramasEducativosByName(searchName);
        assertTrue(programasEducativosEsperados.equals(programasEducativosObtenidos));
    }
    
    @Test
    public void testFindProgramaEducativoById() {
        System.out.println("findProgramaEducativoById");
        int idProgramaEducativo = 1;
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        ProgramaEducativo programaEducativoEsperado = new ProgramaEducativo(idProgramaEducativo, "INGENIERÍA DE SOFTWARE");
        ProgramaEducativo programaEducativoObtenido = programaEducativoDao.findProgramaEducativoById(idProgramaEducativo);
        assertTrue(programaEducativoEsperado.equals(programaEducativoObtenido));
    }
    
    @Test
    public void testGetProgramaEducativo() {
        System.out.println("getProgramaEducativo");
        int idProgramaEducativo = 1;
        ProgramaEducativo programaEducativoEsperado = new ProgramaEducativo(idProgramaEducativo, "INGENIERÍA DE SOFTWARE");
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        ProgramaEducativo programaEducativoObtenido = new ProgramaEducativo();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "SELECT idProgramaEducativo, nombre AS nombreProgramaEducativo " +
                "FROM ProgramaEducativo " +
                "WHERE idProgramaEducativo = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProgramaEducativo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false) {
                throw new SQLException("Programa Educativo not found");
            }
            programaEducativoObtenido = programaEducativoDao.getProgramaEducativo(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(ProgramaEducativoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        assertTrue(programaEducativoEsperado.equals(programaEducativoObtenido));
    }
    
    @Test
    public void testAddProgramaEducativo() {
        System.out.println("addProgramaEducativo");
        ProgramaEducativo programaEducativo = new ProgramaEducativo("TECNOLOGÍAS COMPUTACIONALES");
        ProgramaEducativoDAO programaEducativoDao = new ProgramaEducativoDAO();
        boolean result = programaEducativoDao.addProgramaEducativo(programaEducativo);
        assertTrue(result);
    }

    @Test
    public void testDeleteProgramaEducativoById() {
        System.out.println("deleteProgramaEducativoById");
        int idProgramaEducativo = 0;
        ProgramaEducativoDAO instance = new ProgramaEducativoDAO();
        boolean result = instance.deleteProgramaEducativoById(idProgramaEducativo);
        assertFalse(result);
    }    
}