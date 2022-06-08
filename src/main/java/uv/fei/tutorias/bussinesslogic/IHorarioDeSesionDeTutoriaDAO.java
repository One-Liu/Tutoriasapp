package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.HorarioDeSesionDeTutoria;

public interface IHorarioDeSesionDeTutoriaDAO {
    public boolean eliminarHorarioDeSesionDeTutoria(int searchId);
    public boolean agregarHorarioDeSesionDeTutoria(HorarioDeSesionDeTutoria horarioDeSesionDeTutoria);
}
