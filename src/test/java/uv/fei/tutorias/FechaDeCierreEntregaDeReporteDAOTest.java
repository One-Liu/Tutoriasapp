package uv.fei.tutorias;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.FechaDeCierreEntregaDeReporteDAO;
import uv.fei.tutorias.domain.FechaDeCierreEntregaDeReporte;

// author @liu
public class FechaDeCierreEntregaDeReporteDAOTest {
    private FechaDeCierreEntregaDeReporte fechaDeCierreNueva;
    private FechaDeCierreEntregaDeReporte fechaDeCierre1;
    private FechaDeCierreEntregaDeReporteDAO fechaDeCierreDAO;
    
    @Before
    public void init() {
        fechaDeCierreNueva = new FechaDeCierreEntregaDeReporte("");
        fechaDeCierre1 = new FechaDeCierreEntregaDeReporte("28-04-2022");
        fechaDeCierreDAO = new FechaDeCierreEntregaDeReporteDAO();
    }
    
    @Test
    public void testFindFechaDeCierreEntregaDeReporteByFecha() {
        String searchDate = "2022";
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporteEsperadas = new ArrayList<>();
        fechasDeCierreEntregaReporteEsperadas.add(fechaDeCierre1);
        ArrayList<FechaDeCierreEntregaDeReporte> fechasDeCierreEntregaReporteObtenidas = fechaDeCierreDAO.findFechasDeCierreEntregaDeReporteByFecha(searchDate);
        assertTrue(fechasDeCierreEntregaReporteEsperadas.equals(fechasDeCierreEntregaReporteObtenidas));
    }
    
    @Test
    public void testFindFechaDeCierreEntregaDeReporteById() {
        int idFechaDeCierreEntregaReporte = 1;
        FechaDeCierreEntregaDeReporte fechaDeCierreEntregaReporteObtenida = fechaDeCierreDAO.findFechaDeCierreEntregaDeReporteById(idFechaDeCierreEntregaReporte);
        assertTrue(fechaDeCierre1.equals(fechaDeCierreEntregaReporteObtenida));
    }

    @Test
    public void testAddFechaDeCierreEntregaDeReporte() {
        assertTrue(fechaDeCierreDAO.addFechaDeCierreEntregaDeReporte(fechaDeCierreNueva));
    }

    @Test
    public void testDeleteFechaDeCierreEntregaDeReporteById() {
        int idFechaDeCierreEntregaReporte = 0;
        assertFalse(fechaDeCierreDAO.deleteFechaDeCierreEntregaDeReporteById(idFechaDeCierreEntregaReporte));
    }
}
