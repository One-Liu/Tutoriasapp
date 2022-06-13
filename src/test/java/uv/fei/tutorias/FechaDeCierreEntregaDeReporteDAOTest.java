package uv.fei.tutorias;

import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// author @liu
public class FechaDeCierreEntregaDeReporteDAOTest {
    
    private Date fechaNueva;
    private FechaDeCierreEntregaDeReporte fechaDeCierreNueva;
    private Date fecha1;
    private FechaDeCierreEntregaDeReporte fechaDeCierre1;
    private FechaDeCierreEntregaDeReporteDAO fechaDeCierreDAO;
    
    @Before
    public void inicializar() {
        fechaNueva = new Date();
        fechaDeCierreNueva = new FechaDeCierreEntregaDeReporte("");
        fecha1 = new Date("");
        fechaDeCierre1 = new FechaDeCierreEntregaDeReporte(1,fecha1);
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
