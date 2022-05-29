package uv.fei.tutorias.bussinesslogic;


import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.HorarioDeSesionDeTutoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HorarioDeSesionDeTutoriaDAO implements IHorarioDeSesionDeTutoriaDAO{
    private final Logger LOG = Logger.getLogger(PersonaDAO.class);

    @Override
    public boolean eliminarHorarioDeSesionDeTutoria(int searchId) {
        boolean bandera = false;
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "DELETE FROM horario_de_sesion_de_tutoria WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ningun profesor con el id " + searchId);
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean agregarHorarioDeSesionDeTutoria(HorarioDeSesionDeTutoria horarioDeSesionDeTutoria) {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO horario_de_sesion_de_tutoria (hora,idEstudiante,idSesionDeTutoriaAcademica) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,horarioDeSesionDeTutoria.getHora());
            statement.setInt(2, horarioDeSesionDeTutoria.getEstudiante().getId());
            statement.setInt(3, horarioDeSesionDeTutoria.getSesionDeTutoriaAcademica().getId());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El Horario de sesion de tutoria academica no se ha agregado");
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

}
