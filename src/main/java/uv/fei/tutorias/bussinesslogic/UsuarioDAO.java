package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Usuario;

import java.sql.*;

public class UsuarioDAO implements IUsarioDAO{
    private final Logger LOG = Logger.getLogger(PersonaDAO.class);


    @Override
    public boolean addUsuario(Usuario usuario) {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
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
        ConexionBD dataBaseConnection = new ConexionBD();
        int id = -1;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
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
    public Usuario findUsuarioReturnId(Usuario usuario){
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select id,Contrasena, correoInstitucional from usuario where Contrasena =  (?) and correoInstitucional = (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario.getContrasena());
            statement.setString(2, usuario.getCorreoInstitucional());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException("No se ha encontrado ningun usuario");
            } else {
                int id;
                id = resultSet.getInt("id");
                usuario.setIdUsuario(id);
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return usuario;

    }
    public boolean estaIdUsuarioEnTutorAcademico(int searchId){
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean resultado = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select idUsuario from tutor_academico where idUsuario=(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultado = true;
            }
        } catch (SQLException e) {
            LOG.warn(UsuarioDAO.class.getName(), e);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return resultado;
    }

    public boolean estaIdUsarionEnJefeDeCarrera(int searchId){
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean resultado = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select idUsuario from jefe_de_carrera where idUsuario=(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultado = true;
            }
        } catch (SQLException e) {
            LOG.warn(UsuarioDAO.class.getName(), e);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return resultado;
    }
    public boolean estaIdUsuarionEnCoordinador(int searchId){
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean resultado = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select idUsuario from coordinador where idUsuario=(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultado = true;
            }
        } catch (SQLException e) {
            LOG.warn(UsuarioDAO.class.getName(), e);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return resultado;

    }
}
