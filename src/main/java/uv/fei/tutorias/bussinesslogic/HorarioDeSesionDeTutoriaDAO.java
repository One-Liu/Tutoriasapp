package uv.fei.tutorias.bussinesslogic;


import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.HorarioDeSesionDeTutoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HorarioDeSesionDeTutoriaDAO implements IHorarioDeSesionDeTutoriaDAO{
    private final Logger LOG = Logger.getLogger(HorarioDeSesionDeTutoriaDAO.class);

    @Override
    public boolean eliminarHorarioDeSesionDeTutoria(int searchId) throws SQLException {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "DELETE FROM horario_de_sesion_de_tutoria WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean agregarHorarioDeSesionDeTutoria(HorarioDeSesionDeTutoria horarioDeSesionDeTutoria) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO horario_de_sesion_de_tutoria (hora,idEstudiante,idSesionDeTutoriaAcademica) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,horarioDeSesionDeTutoria.getHora());
            statement.setInt(2, horarioDeSesionDeTutoria.getEstudiante().getIdEstudiante());
            statement.setInt(3, horarioDeSesionDeTutoria.getSesionDeTutoriaAcademica().getId());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
            throw ex;
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

}
