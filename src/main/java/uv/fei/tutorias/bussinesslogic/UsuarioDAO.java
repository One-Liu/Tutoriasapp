package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Usuario;

import java.sql.*;

public class UsuarioDAO implements IUsarioDAO{
    private final Logger LOG = Logger.getLogger(PersonaDAO.class);


    @Override
    public boolean addUsuario(Usuario usuario) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO usuario(contrasena, correoInstitucional) VALUES(MD5(?),?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario.getContrasena());
            statement.setString(2, usuario.getCorreoInstitucional());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El usuario no se ha agregado");
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
    public int addUsuarioReturnId(Usuario usuario){
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        int id = -1;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO usuario(contrasena, correoInstitucional) VALUES(MD5(?),?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuario.getContrasena());
            statement.setString(2, usuario.getCorreoInstitucional());

            int executeUpdate = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El usuario no se ha agregado");
            }else {
                resultSet.next();
                id=resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return id;
    }
}
