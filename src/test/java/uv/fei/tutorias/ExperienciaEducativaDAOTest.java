package uv.fei.tutorias;
import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.main.UtilidadVentana;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class ExperienciaEducativaDAOTest {
    private ExperienciaEducativaDAO experienciaEducativaDAO;
    private ExperienciaEducativa experienciaEducativa;
    private List<ExperienciaEducativa> expectedResult = new ArrayList<>();
    private ExperienciaEducativa experienciaEducativa1;
    private ExperienciaEducativa experienciaEducativa2;

    @Before
    public void inicio(){
         experienciaEducativaDAO = new ExperienciaEducativaDAO();
         experienciaEducativa = new ExperienciaEducativa("Phyton","1234",0);
         experienciaEducativa1 = new ExperienciaEducativa(1, "Mate Discretas",7);
         experienciaEducativa2 = new ExperienciaEducativa(1, "Mate Discretas", 7);



    }

    @Test
    public void agregarExperienciasEducativas() throws SQLException {
        assertTrue(experienciaEducativaDAO.agregarExperienciaEducativa(experienciaEducativa));
    }
   
    @Test
    public void deleteExperienciaEducativa() throws SQLException {
        boolean result = experienciaEducativaDAO.eliminarExperienciaEducativa(2);
        assertFalse(result);
    }

    @Test
    public void findExperienciaEducativaById(){
        try {
            assertEquals(experienciaEducativa2, experienciaEducativaDAO.obtenerExperienciaEducativaPorId(1));
        } catch (SQLException e) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

}
