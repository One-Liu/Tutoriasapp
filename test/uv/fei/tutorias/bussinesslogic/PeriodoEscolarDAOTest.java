package uv.fei.tutorias.bussinesslogic;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uv.fei.tutorias.domain.PeriodoEscolar;

// author @liu
public class PeriodoEscolarDAOTest {
    @Test
    public void testFindPeriodosEscolaresByFechaInicio() {
        System.out.println("findPeriodosEscolaresByFechaInicio");
        String date = "2022";
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        List<PeriodoEscolar> periodosEscolaresEsperados = new ArrayList<>();
        PeriodoEscolar periodoEscolar1 = new PeriodoEscolar("2022-02-01","2022-07-01");
        periodosEscolaresEsperados.add(periodoEscolar1);
        PeriodoEscolar periodoEscolar2 = new PeriodoEscolar("2022-08-01","2023-01-01");
        periodosEscolaresEsperados.add(periodoEscolar2);
        List<PeriodoEscolar> periodosEscolaresObtenidos = periodoEscolarDao.findPeriodosEscolaresByFechaInicio(date);
        boolean listasIguales = true;
        for (int i=0; i<periodosEscolaresObtenidos.size(); i++) {
            if (periodosEscolaresObtenidos.get(i).equals(periodosEscolaresEsperados.get(i)) == false) {
                listasIguales = false;
            }
        }
        assertTrue(listasIguales);
    }

    @Test
    public void testFindPeriodoEscolarById() {
        System.out.println("findPeriodoEscolarById");
        int idPeriodoEscolar = 1;
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        PeriodoEscolar periodoEscolarEsperado = new PeriodoEscolar(1,"2022-02-01","2022-07-01");
        PeriodoEscolar periodoEscolarObtenido = periodoEscolarDao.findPeriodoEscolarById(idPeriodoEscolar);
        assertTrue(periodoEscolarEsperado.equals(periodoEscolarObtenido));
    }

    @Test
    public void testAddPeriodoEscolar() {
        System.out.println("addPeriodoEscolar");
        PeriodoEscolar periodoEscolar = new PeriodoEscolar("2022-08-01","2023-01-01");
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        boolean result = periodoEscolarDao.addPeriodoEscolar(periodoEscolar);
        assertTrue(result);
    }

    @Test
    public void testDeletePeriodoEscolarById() {
        System.out.println("deletePeriodoEscolarById");
        int idPeriodoEscolar = 2;
        PeriodoEscolarDAO periodoEscolarDao = new PeriodoEscolarDAO();
        boolean result = periodoEscolarDao.deletePeriodoEscolarById(idPeriodoEscolar);
        assertTrue(result);
    }
}
