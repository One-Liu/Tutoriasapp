package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.JefeDeCarrera;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JefeDeCarreraDAO implements IJefeDeCarreraDAO{
    private final Logger LOG = Logger.getLogger(JefeDeCarreraDAO.class);
    @Override
    public boolean addJefeDeCarrera(JefeDeCarrera jefeDeCarrera) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
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
    public boolean addPersonaJefeDeCarreraUsuario(JefeDeCarrera jefeDeCarrera) {
        boolean bandera = false;
        PersonaDAO personaDAO = new PersonaDAO();
        int resultado;
        resultado = personaDAO.addPersonaReturnId(jefeDeCarrera);
        if (resultado != -1){
            JefeDeCarreraDAO jefeDeCarreraDAO = new JefeDeCarreraDAO();
            jefeDeCarrera.setIdPersona(resultado);
            jefeDeCarreraDAO.addJefeDeCarrera(jefeDeCarrera);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.addUsuario(jefeDeCarrera.getUsuario());
            bandera = true;
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
