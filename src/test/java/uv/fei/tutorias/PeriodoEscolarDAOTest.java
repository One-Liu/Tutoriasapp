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
    private PeriodoEscolar periodoEscolar3;
    private PeriodoEscolar periodoEscolar4;
    private PeriodoEscolarDAO periodoEscolarDAO;
    
    @Before
    public void inicializar() {
        periodoEscolarNuevo = new PeriodoEscolar("2024-02-01","2024-07-01");
        periodoEscolar1 = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        periodoEscolar2 = new PeriodoEscolar(2,"2022-08-01","2023-01-01");
        periodoEscolar3 = new PeriodoEscolar(3,"2023-02-01","2023-07-01");
        periodoEscolar4 = new PeriodoEscolar(4,"2023-08-01","2024-01-01");
        periodoEscolarDAO = new PeriodoEscolarDAO();
    }

    @Test
    public void testObtenerPeriodosEscolares() throws SQLException {
        ArrayList<PeriodoEscolar> periodosEscolaresEsperados = new ArrayList<>();
        periodosEscolaresEsperados.add(periodoEscolar1);
        periodosEscolaresEsperados.add(periodoEscolar2);
        periodosEscolaresEsperados.add(periodoEscolar3);
        periodosEscolaresEsperados.add(periodoEscolar4);
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
/*
    @Test
    public void testEliminarPeriodoEscolarPorId() throws SQLException {
        int idPeriodoEscolar = 2;
        assertTrue(periodoEscolarDAO.eliminarPeriodoEscolarPorId(idPeriodoEscolar));
    }
    
    @Test
    public void testModificarPeriodoEscolar() throws SQLException {
        assertTrue(periodoEscolarDAO.modificarPeriodoEscolar(periodoEscolar1));
    }
    */
}
