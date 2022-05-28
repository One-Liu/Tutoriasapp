package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.JefeDeCarrera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JefeDeCarreraDAO implements IJefeDeCarreraDAO{
    private final Logger LOG = Logger.getLogger(JefeDeCarreraDAO.class);
    @Override
    public boolean addJefeDeCarrera(JefeDeCarrera jefeDeCarrera) {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO jefe_de_carrera(idPersona,idUsuario) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,jefeDeCarrera.getIdPersona());
            statement.setInt(2, jefeDeCarrera.getUsuario().getId());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El profesor no se ha agregado");
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(JefeDeCarrera.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }



    @Override
    public boolean deleteJefeDeCarreraById(int searchId) {
        return false;
    }

    @Override
    public JefeDeCarrera findJefeDeCarreraById(int searchId) {
        return null;
    }
}
