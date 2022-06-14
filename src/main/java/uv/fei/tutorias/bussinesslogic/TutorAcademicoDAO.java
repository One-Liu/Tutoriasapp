package uv.fei.tutorias.bussinesslogic;

import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.TutorAcademico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// author @liu
public class TutorAcademicoDAO implements ITutorAcademicoDAO {
    private final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(TutorAcademicoDAO.class);

    @Override
    public List<TutorAcademico> obtenerTutoresAcademicos() throws SQLException {
        List<TutorAcademico> tutores = new ArrayList<>();
        String consulta =
                "SELECT TA.id AS idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM tutor_academico TA INNER JOIN persona P ON P.id = TA.idPersona";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
<<<<<<< HEAD
            while(resultado.next()) {
                tutores.add(getTutorAcademico(resultado));
=======
            if (!resultado.next()) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
                do {
                    tutores.add(getTutorAcademico(resultado));
                } while (resultado.next());
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return tutores;
    }

    @Override
    public TutorAcademico obtenerTutorAcademicoPorId(int idTutorAcademico) throws SQLException {
        TutorAcademico tutorAcademico = new TutorAcademico();
        String consulta = 
                "SELECT TA.id AS idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM tutor_academico TA INNER JOIN persona P ON P.id = TA.idPersona " +
                "WHERE TA.id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            ResultSet resultado = sentencia.executeQuery();
<<<<<<< HEAD
            if(resultado.next()) {
=======
            if (!resultado.next()) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
                tutorAcademico = getTutorAcademico(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
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
        int idProgramaEducativo;
        
        idTutorAcademico = resultado.getInt("idTutorAcademico");
        nombre = resultado.getString("nombre");
        apellidoPaterno = resultado.getString("apellidoPaterno");
        apellidoMaterno = resultado.getString("apellidoMaterno");
        idProgramaEducativo = resultado.getInt("idProgramaEducativo");

        Persona personaTutorAcademico = new Persona(nombre,apellidoPaterno,apellidoMaterno,idProgramaEducativo);
        TutorAcademico tutorAcademico = new TutorAcademico(idTutorAcademico,personaTutorAcademico);
        return tutorAcademico;
    }

    @Override
    public boolean agregarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException {
        boolean resultado = false;
        String consulta = "INSERT INTO tutor_academico(idPersona,idUsuario) VALUES(?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, tutorAcademico.getIdPersona());
            sentencia.setInt(2, tutorAcademico.getIdUsuario());
            int columnasAfectadas = sentencia.executeUpdate();
<<<<<<< HEAD
            if(columnasAfectadas != 0) {
                resultado = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
=======
            if(columnasAfectadas == 0) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } 
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }

    @Override
    public boolean eliminarTutorAcademicoPorId(int idTutorAcademico) throws SQLException {
        boolean resultado = false;
        String consulta = "DELETE FROM tutor_academico WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            int columnasAfectadas = sentencia.executeUpdate();
<<<<<<< HEAD
            if(columnasAfectadas != 0) {
                resultado = true;
=======
            if(columnasAfectadas == 0) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }
    
    @Override
    public boolean modificarTutorAcademico(TutorAcademico tutorAcademico) throws SQLException {
<<<<<<< HEAD
        boolean resultado = false;
        String consulta = 
=======
        String consulta =
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
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
<<<<<<< HEAD
            if(columnasAfectadas != 0) {
                resultado = true;
=======
            if(columnasAfectadas == 0) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;
    }
    @Override
    public TutorAcademico buscarTutorAcademicoPorElIdDeUsuario(int idUsuario) throws SQLException {
        TutorAcademico tutorAcademico;
        String query =
                "SELECT TA.id AS idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM tutor_academico TA INNER JOIN persona P ON P.id = TA.idPersona " +
                "WHERE idUsuario = ?";
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUsuario);
            ResultSet resultSet = statement.executeQuery();
<<<<<<< HEAD
            if(resultSet.next()) {
=======
            if (!resultSet.next()) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            }else {
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
                tutorAcademico = getTutorAcademico(resultSet);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return tutorAcademico;

    }
    
    @Override
    public ObservableList<TutorAcademico> obtenerTutoresAcademicosDistintosA(int idTutorAcademico) throws SQLException {
        ObservableList<TutorAcademico> tutores = FXCollections.observableArrayList();
        String consulta =
                "SELECT TA.id AS idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM tutor_academico TA INNER JOIN persona P ON P.id = TA.idPersona " +
                "WHERE TA.id != ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try (Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            ResultSet resultado = sentencia.executeQuery();
<<<<<<< HEAD
            while(resultado.next()) {
                tutores.add(getTutorAcademico(resultado));
=======
            if (!resultado.next()) {
                LOGGER.warn(TutorAcademicoDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexion a la base de datos");
            } else {
                do {
                    tutores.add(getTutorAcademico(resultado));
                } while (resultado.next());
>>>>>>> 7c46bd4502853b234313391492c8cb61d1ee4f5a
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return tutores;
    }
}