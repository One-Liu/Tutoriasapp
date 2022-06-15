package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Usuario;

import java.sql.*;

public class UsuarioDAO implements IUsuarioDAO{
    private final Logger LOG = Logger.getLogger(UsuarioDAO.class);


  @Override
    public int agregarUsuario(Usuario usuario) throws SQLException{
        ConexionBD dataBaseConnection = new ConexionBD();
        int id;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO usuario(contrasena, correoInstitucional) VALUES(MD5(?),?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usuario.getContrasena());
            statement.setString(2, usuario.getCorreoInstitucional());

            int executeUpdate = statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (executeUpdate == 0) {
                SQLException ex = new SQLException();
                LOG.warn(UsuarioDAO.class.getName(), ex);
                throw ex;
            }else {
                resultSet.next();
                id=resultSet.getInt(1);
            }
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return id;
    }

    @Override
    public Usuario buscarUsuarioPorCorreoYContrasena(Usuario usuario)throws SQLException{
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select id,Contrasena, correoInstitucional from usuario where Contrasena = md5 (?) and correoInstitucional = (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario.getContrasena());
            statement.setString(2, usuario.getCorreoInstitucional());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                SQLException ex = new SQLException();
                LOG.warn(getClass().getName(), ex);
                throw ex;
            } else {
                int id;
                id = resultSet.getInt("id");
                usuario.setIdUsuario(id);
            }
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return usuario;

    }
    @Override
    public boolean estaIdUsuarioEnTutorAcademico(int searchId)throws SQLException{
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean resultado;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select idUsuario from tutor_academico where idUsuario=(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultado = true;
            }else {
                SQLException ex = new SQLException();
                LOG.warn(getClass().getName(), ex);
                throw ex;
            }
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean estaIdUsarionEnJefeDeCarrera(int searchId)throws SQLException{
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean resultado;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "select idUsuario from jefe_de_carrera where idUsuario=(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultado = true;
            }else {
                SQLException ex = new SQLException("No existe ningun usuario");
                LOG.warn(getClass().getName(), ex);
                throw ex;
            }
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return resultado;
    }
    @Override
    public boolean estaIdUsuarionEnCoordinador(int searchId)throws SQLException{
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
        }catch (SQLException ex){
            LOG.warn(getClass().getName(), ex);
            throw ex;
        }  finally {
            dataBaseConnection.cerrarConexion();
        }
        return resultado;

    }
}
