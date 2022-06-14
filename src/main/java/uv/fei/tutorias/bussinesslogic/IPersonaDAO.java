package uv.fei.tutorias.bussinesslogic;

import java.sql.SQLException;
import uv.fei.tutorias.domain.Persona;
import java.util.List;

public interface IPersonaDAO {
    public Persona obtenerPersonaPorId(int searchId) throws SQLException;
    public int agregarPersona(Persona persona) throws SQLException;
    public boolean eliminarPersonaPorId(int searchId)throws SQLException;
}
