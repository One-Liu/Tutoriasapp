package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.domain.HorarioDeSesionDeTutoria;

import java.sql.SQLException;

public interface IHorarioDeSesionDeTutoriaDAO {
    public boolean eliminarHorarioDeSesionDeTutoria(int searchId) throws SQLException;
    public boolean agregarHorarioDeSesionDeTutoria(HorarioDeSesionDeTutoria horarioDeSesionDeTutoria) throws SQLException;
}
