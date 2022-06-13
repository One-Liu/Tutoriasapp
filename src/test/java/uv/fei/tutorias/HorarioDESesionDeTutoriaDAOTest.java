package uv.fei.tutorias;

import org.junit.Before;
import org.junit.Test;
import uv.fei.tutorias.bussinesslogic.HorarioDeSesionDeTutoriaDAO;
import uv.fei.tutorias.domain.Estudiante;
import uv.fei.tutorias.domain.HorarioDeSesionDeTutoria;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

import static org.junit.Assert.assertTrue;

public class HorarioDESesionDeTutoriaDAOTest {
    private HorarioDeSesionDeTutoria horarioDeSesionDeTutoria;
    private HorarioDeSesionDeTutoriaDAO horarioDeSesionDeTutoriaDAO;
    private Estudiante estudiante;
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
    @Before
    public void inicializador(){
        horarioDeSesionDeTutoriaDAO = new HorarioDeSesionDeTutoriaDAO();
        estudiante = new Estudiante();
        sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
        estudiante.setIdEstudiante(1);
        sesionDeTutoriaAcademica.setId(3);

        horarioDeSesionDeTutoria = new HorarioDeSesionDeTutoria("12:45:00",estudiante,sesionDeTutoriaAcademica);

    }
    @Test
    public void agregarHorarioDeSesionDeTutoria(){
        assertTrue(horarioDeSesionDeTutoriaDAO.agregarHorarioDeSesionDeTutoria(horarioDeSesionDeTutoria));
    }
    @Test
    public void eliminarHorarioDeSesionDeTutoria(){
        assertTrue(horarioDeSesionDeTutoriaDAO.eliminarHorarioDeSesionDeTutoria(8));
    }
}
