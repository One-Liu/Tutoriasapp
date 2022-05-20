package uv.fei.tutorias;

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
    public void init() {
        periodoEscolarNuevo = new PeriodoEscolar("","");
        periodoEscolar1 = new PeriodoEscolar("2022-02-01","2022-07-01");
        periodoEscolar2 = new PeriodoEscolar("2022-08-01","2023-01-01");
        periodoEscolarDAO = new PeriodoEscolarDAO();
    }

    @Test
    public void testFindPeriodosEscolaresByFechaInicio() {
        String date = "2022";
        ArrayList<PeriodoEscolar> periodosEscolaresEsperados = new ArrayList<>();
        periodosEscolaresEsperados.add(periodoEscolar1);
        periodosEscolaresEsperados.add(periodoEscolar2);
        ArrayList<PeriodoEscolar> periodosEscolaresObtenidos = periodoEscolarDAO.findPeriodosEscolaresByFechaInicio(date);
        assertTrue(periodosEscolaresEsperados.equals(periodosEscolaresObtenidos));
    }

    @Test
    public void testFindPeriodoEscolarById() {
        int idPeriodoEscolar = 1;
        PeriodoEscolar periodoEscolarObtenido = periodoEscolarDAO.findPeriodoEscolarById(idPeriodoEscolar);
        assertTrue(periodoEscolar1.equals(periodoEscolarObtenido));
    }

    @Test
    public void testAddPeriodoEscolar() {
        assertTrue(periodoEscolarDAO.addPeriodoEscolar(periodoEscolarNuevo));
    }

    @Test
    public void testDeletePeriodoEscolarById() {
        int idPeriodoEscolar = 2;
        assertTrue(periodoEscolarDAO.deletePeriodoEscolarById(idPeriodoEscolar));
    }
}
