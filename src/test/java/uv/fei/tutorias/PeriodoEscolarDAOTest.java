package uv.fei.tutorias;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAOTest {
    
    private PeriodoEscolar periodoEscolarNuevo;
    private PeriodoEscolar periodoEscolar1;
    private PeriodoEscolar periodoEscolar2;
    private PeriodoEscolarDAO periodoEscolarDAO;
    
    @Before
    public void inicializar() {
        periodoEscolarNuevo = new PeriodoEscolar("","");
        periodoEscolar1 = new PeriodoEscolar("2022-02-01","2022-07-01");
        periodoEscolar2 = new PeriodoEscolar("2022-08-01","2023-01-01");
        periodoEscolarDAO = new PeriodoEscolarDAO();
    }

    @Test
    public void testObtenerPeriodosEscolares() throws SQLException {
        ArrayList<PeriodoEscolar> periodosEscolaresEsperados = new ArrayList<>();
        periodosEscolaresEsperados.add(periodoEscolar1);
        periodosEscolaresEsperados.add(periodoEscolar2);
        ArrayList<PeriodoEscolar> periodosEscolaresObtenidos = periodoEscolarDAO.obtenerPeriodosEscolares();
        assertTrue(periodosEscolaresEsperados.equals(periodosEscolaresObtenidos));
    }

    @Test
    public void testObtenerPeriodoEscolarPorId() throws SQLException {
        int idPeriodoEscolar = 1;
        PeriodoEscolar periodoEscolarObtenido = periodoEscolarDAO.obtenerPeriodoEscolarPorId(idPeriodoEscolar);
        assertTrue(periodoEscolar1.equals(periodoEscolarObtenido));
    }

    @Test
    public void testAgregarPeriodoEscolar() throws SQLException {
        assertTrue(periodoEscolarDAO.agregarPeriodoEscolar(periodoEscolarNuevo));
    }

    @Test
    public void testEliminarPeriodoEscolarPorId() throws SQLException {
        int idPeriodoEscolar = 2;
        assertTrue(periodoEscolarDAO.eliminarPeriodoEscolarPorId(idPeriodoEscolar));
    }
    
    @Test
    public void testModificarPeriodoEscolar() throws SQLException {
        assertTrue(periodoEscolarDAO.modificarPeriodoEscolar(periodoEscolar1));
    }
}
