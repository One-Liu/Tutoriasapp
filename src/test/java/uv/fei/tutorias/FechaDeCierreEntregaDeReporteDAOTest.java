package uv.fei.tutorias;

import domain.FechaDeCierreEntregaDeReporte;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class FechaDeCierreEntregaDeReporteDAOTest {
    
    private FechaDeCierreEntregaDeReporte fechaDeCierreNueva;
    private FechaDeCierreEntregaDeReporte fechaDeCierre1;
    private FechaDeCierreEntregaDeReporteDAO fechaDeCierreDAO;
    
    @Before
    public void inicializar() {
        fechaDeCierreNueva = new FechaDeCierreEntregaDeReporte("");
        fechaDeCierre1 = new FechaDeCierreEntregaDeReporte("28-04-2022");
        fechaDeCierreDAO = new FechaDeCierreEntregaDeReporteDAO();
    }
    
    @Test
    public void testObtenerFechasDeCierreEntregaDeReporte() throws SQLException {
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporteEsperadas = new ArrayList<>();
        fechasDeCierreEntregaReporteEsperadas.add(fechaDeCierre1);
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporteObtenidas = fechaDeCierreDAO.obtenerFechasDeCierreEntregaDeReporte();
        assertTrue(fechasDeCierreEntregaReporteEsperadas.equals(fechasDeCierreEntregaReporteObtenidas));
    }
    
    @Test
    public void testObtenerFechaDeCierreEntregaDeReportePorId() throws SQLException {
        int idFechaDeCierreEntregaReporte = 1;
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporteObtenida = fechaDeCierreDAO.obtenerFechaDeCierreEntregaDeReportePorId(idFechaDeCierreEntregaReporte);
        assertTrue(fechaDeCierre1.equals(fechaDeCierreEntregaReporteObtenida));
    }

    @Test
    public void testAgregarFechaDeCierreEntregaDeReporte() throws SQLException {
        assertTrue(fechaDeCierreDAO.agregarFechaDeCierreEntregaDeReporte(fechaDeCierreNueva));
    }

    @Test
    public void testEliminarFechaDeCierreEntregaDeReportePorId() throws SQLException {
        int idFechaDeCierreEntregaReporte = 0;
        assertFalse(fechaDeCierreDAO.eliminarFechaDeCierreEntregaDeReportePorId(idFechaDeCierreEntregaReporte));
    }
    
    @Test
    public void testModificarFechaDeCierreEntregaDeReporte() throws SQLException {
        assertTrue(fechaDeCierreDAO.modificarFechaDeCierreEntregaDeReporte(fechaDeCierre1));
    }
}
