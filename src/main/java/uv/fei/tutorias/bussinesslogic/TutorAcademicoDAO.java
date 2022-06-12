package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// author @liu
public class TutorAcademicoDAO implements ITutorAcademicoDAO {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PersonaDAO.class);

    @Override
    public ObservableList<TutorAcademico> obtenerTutoresAcademicos() throws SQLException {
        ObservableList<TutorAcademico> tutores = FXCollections.observableArrayList();
        String consulta =
        "SELECT TA.idTutorAcademico, P.* " +
        "FROM TutorAcademico TA INNER JOIN Persona P ON P.idPersona = TA.idPersona";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if (!resultado.next()) {
                SQLException exception = new SQLException();
                LOGGER.warn(TutorAcademicoDAO.class.getName(), exception);
                throw exception;
            } else {
                do {
                    tutores.add(getTutorAcademico(resultado));
                } while (resultado.next());
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return tutores;
    }

    @Override
    public TutorAcademico obtenerTutorAcademicoPorId(int idTutorAcademico) throws SQLException {
        TutorAcademico tutorAcademico;
        String consulta = 
        "SELECT TA.idTutorAcademico, P.* " +
        "FROM TutorAcademico TA LEFT JOIN Persona P ON P.idPersona = TA.idPersona " +
        "WHERE idTutorAcademico = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next() == false) {
                SQLException exception = new SQLException();
                LOGGER.warn(TutorAcademicoDAO.class.getName(), exception);
                throw exception;
            } else {
                tutorAcademico = getTutorAcademico(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return tutorAcademico;
    }

    private TutorAcademico getTutorAcademico(ResultSet resultado) throws SQLException {
        int idTutorAcademico;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;

        idTutorAcademico = resultado.getInt("id");
        nombre = resultado.getString("nombre");
        apellidoPaterno = resultado.getString("apellidoPaterno");
        apellidoMaterno = resultado.getString("apellidoMaterno");

        Persona personaCoordinador = new Persona(nombre,apellidoPaterno,apellidoMaterno);
        TutorAcademico tutorAcademico = new TutorAcademico(idTutorAcademico,personaCoordinador);
        return tutorAcademico;
    }
//TODO arreglar id de usuario persona tutorAcademico
    @Override
    public boolean agregarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException {
        boolean validacion = false;
        String consulta = "INSERT INTO tutor_academico(idPersona,idUsuario) VALUES(?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
//            sentencia.setInt(1, tutorAcademico.ge());
//            sentencia.setInt(2, tutorAcademico.getIdUsuario());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: El coordinador no se ha agregado");
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean eliminarTutorAcademicoPorId(int idTutorAcademico) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM tutor_academico WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                SQLException exception = new SQLException();
                LOGGER.warn(TutorAcademicoDAO.class.getName(), exception);
                throw exception;            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public boolean modificarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException {
        boolean validacion;
        String consulta = 
                "UPDATE tutor_academico " + 
                "SET idPersona = ?, " +
                "SET idUsuario = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, tutorAcademico.getIdPersona());
            sentencia.setInt(2, tutorAcademico.getIdUsuario());
            sentencia.setInt(3, tutorAcademico.getIdTutorAcademico());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                SQLException exception = new SQLException();
                LOGGER.warn(TutorAcademicoDAO.class.getName(), exception);
                throw exception;            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    public TutorAcademico buscarTutorAcademicoPorElIdDeUsuario(int idUsuario)throws SQLException{
        TutorAcademico tutorAcademico = new TutorAcademico();
        String query =
                "SELECT TA.id, P.* " +
                        "FROM tutor_academico TA LEFT JOIN persona P ON P.id = TA.idPersona " +
                        "WHERE idUsuario = ?";
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException("No se ha encontrado el tutor academico con el idusuario " + idUsuario);
            }else {
                tutorAcademico = getTutorAcademico(resultSet);
            }
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
            throw new SQLException("Error en la conexion con la base de datos " + idUsuario);
        }
        return tutorAcademico;

    }

    @Override
    public ArrayList<TutorAcademico> recuperarTodosTutoresAcademicos(){
        ArrayList<TutorAcademico> listaTutores = new ArrayList<>();
        String query = "SELECT tutor_academico.id AS idTutorAcademico, nombre, apellidoPaterno, apellidoMaterno" +
                " FROM tutor_academico INNER JOIN persona ON tutor_academico.idPersona = persona.id";
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                do {
                    TutorAcademico tutor = new TutorAcademico();
                    tutor.setIdTutorAcademico(resultSet.getInt("idTutorAcademico"));
                    tutor.setNombre(resultSet.getString("nombre"));
                    tutor.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                    tutor.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
                    listaTutores.add(tutor);
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
        }finally{
            dataBaseConnection.cerrarConexion();
        }
        return listaTutores;
    }
    
    @Override
    public ObservableList<TutorAcademico> obtenerTutoresAcademicosDistintosA(int idTutorAcademico) throws SQLException {
        ObservableList<TutorAcademico> tutores = FXCollections.observableArrayList();
        String consulta =
                "SELECT TA.idTutorAcademico, P.* " +
                "FROM TutorAcademico TA INNER JOIN Persona P ON P.idPersona = TA.idPersona" +
                "WHERE TA.idTutorAcademico != ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            ResultSet resultado = sentencia.executeQuery();
            if (!resultado.next()) {
                throw new SQLException("No se han encontrado tutores academicos");
            } else {
                do {
                    tutores.add(getTutorAcademico(resultado));
                } while (resultado.next());
            }
        } catch (SQLException ex) {
            LOGGER.warn(TutorAcademicoDAO.class.getName(), ex);
            throw new SQLException("No hay conexion a la base de datos");
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return tutores;
    }
}