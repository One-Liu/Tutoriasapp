package uv.fei.tutorias;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAOTest {
    
    private Date fechaInicioNueva;
    private Date fechaTerminoNueva;
    private PeriodoEscolar periodoEscolarNuevo;
    private Date fechaInicio1;
    private Date fechaTermino1;
    private PeriodoEscolar periodoEscolar1;
    private Date fechaInicio2;
    private Date fechaTermino2;
    private PeriodoEscolar periodoEscolar2;
    private Date fechaInicio3;
    private Date fechaTermino3;
    private PeriodoEscolar periodoEscolar3;
    private Date fechaInicio4;
    private Date fechaTermino4;
    private PeriodoEscolar periodoEscolar4;
    private PeriodoEscolarDAO periodoEscolarDAO;
    
    @Before
    public void inicializar() {
        fechaInicioNueva = (Date) java.sql.Date.valueOf(LocalDate.of(2016, 8, 19));
        fechaTerminoNueva = (Date) java.sql.Date.valueOf(LocalDate.of(2016, 8, 19));
        System.out.println(fechaInicioNueva);
        periodoEscolarNuevo = new PeriodoEscolar(fechaInicioNueva,fechaTerminoNueva);
        fechaInicio1 = new Date();
        fechaTermino1 = new Date();
        periodoEscolar1 = new PeriodoEscolar(1,fechaInicio1,fechaTermino1);
        fechaInicio2 = new Date();
        fechaTermino2 = new Date();
        periodoEscolar2 = new PeriodoEscolar(2,fechaInicio2,fechaTermino2);
        fechaInicio3 = new Date();
        fechaTermino3 = new Date();
        periodoEscolar3 = new PeriodoEscolar(3,fechaInicio3,fechaTermino3);
        fechaInicio4 = new Date();
        fechaTermino4 = new Date();
        periodoEscolar4 = new PeriodoEscolar(4,fechaInicio4,fechaTermino4);
        periodoEscolarDAO = new PeriodoEscolarDAO();
    }

    @Test
    public void testObtenerPeriodosEscolares() throws SQLException {
        ObservableList<PeriodoEscolar> periodosEscolaresEsperados = FXCollections.observableArrayList();
        periodosEscolaresEsperados.add(periodoEscolar1);
        periodosEscolaresEsperados.add(periodoEscolar2);
        periodosEscolaresEsperados.add(periodoEscolar3);
        periodosEscolaresEsperados.add(periodoEscolar4);
        ObservableList<PeriodoEscolar> periodosEscolaresObtenidos = FXCollections.observableArrayList();
        periodosEscolaresObtenidos.addAll(periodoEscolarDAO.obtenerPeriodosEscolares());
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
        assertNotEquals(periodoEscolarDAO.agregarPeriodoEscolar(periodoEscolarNuevo),-1);
    }
}
