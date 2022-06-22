package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import uv.fei.tutorias.domain.JefeDeCarrera;

public interface IJefeDeCarreraDAO {
    public JefeDeCarrera obtenerJefeDeCarreraPorIdUsuario(int idUsuario) throws SQLException;
}
