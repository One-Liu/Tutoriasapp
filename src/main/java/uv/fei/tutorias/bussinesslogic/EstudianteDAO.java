package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public class EstudianteDAO implements IEstudianteDAO {

    private final Logger LOGGER = Logger.getLogger(EstudianteDAO.class);

    @Override
    public List<Estudiante> obtenerEstudiantes() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String consulta =
        "SELECT E.id, E.matricula, E.enRiesgo, E.idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                estudiantes.add(getEstudiante(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiantes;
    }

    @Override
    public Estudiante obtenerEstudiantePorId(int idEstudiante) throws SQLException {
        Estudiante estudiante = new Estudiante();
        String consulta =
        "SELECT E.id, E.matricula, E.enRiesgo, E.idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno, E.enRiesgo " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
        "WHERE E.id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next()) {
                estudiante = getEstudiante(resultado);
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiante;
    }

    private Estudiante getEstudiante(ResultSet resultSet) throws SQLException {
        int idEstudiante;
        String matricula;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        boolean enRiesgo;
        int idProgramaEducativo;
        int idTutorAcademico;

        idEstudiante = resultSet.getInt("id");
        matricula = resultSet.getString("matricula");
        nombre = resultSet.getString("nombre");
        apellidoPaterno = resultSet.getString("apellidoPaterno");
        apellidoMaterno = resultSet.getString("apellidoMaterno");
        enRiesgo = resultSet.getBoolean("enRiesgo");
        idTutorAcademico = resultSet.getInt("idTutorAcademico");
        idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        
        Persona personaEstudiante = new Persona(nombre,apellidoPaterno,apellidoMaterno,idProgramaEducativo);
        Estudiante estudiante = new Estudiante(idEstudiante,matricula,enRiesgo,idTutorAcademico,personaEstudiante);
        return estudiante;
    }

    @Override
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException {
        boolean resultado = false;
        PersonaDAO personaDao = new PersonaDAO();
        Persona personaEstudiante = new Persona(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno(), estudiante.getIdProgramaEducativo());
        String consulta = "INSERT INTO estudiante (matricula, idTutorAcademico, idPersona) VALUES (?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            sentencia.setInt(2, estudiante.getIdTutorAcademico());
            sentencia.setInt(3, personaDao.agregarPersona(personaEstudiante));
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
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
    public boolean eliminarEstudiantePorId(int idEstudiante) throws SQLException {
        boolean resultado = false;
        String consulta = "DELETE FROM estudiante WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
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
    public boolean modificarAsignacionDeTutor(Estudiante estudiante) throws SQLException {
        boolean resultado = false;
        String consulta =
                "UPDATE estudiante " +
                "SET idTutorAcademico = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, estudiante.getIdTutorAcademico());
            sentencia.setInt(2, estudiante.getIdEstudiante());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
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
    public List<Estudiante> obtenerEstudiantesDeTutor(int idTutorAcademico) throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String consulta =
                "SELECT E.id, E.matricula, E.enRiesgo, E.idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
                "WHERE E.idTutorAcademico = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idTutorAcademico);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                estudiantes.add(getEstudiante(resultado));                
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiantes;
    }
    
    @Override
    public List<Estudiante> obtenerEstudiantesSinTutorAsignado() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String consulta =
                "SELECT E.id, E.matricula, E.enRiesgo, E.idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
                "WHERE E.idTutorAcademico = 0";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                estudiantes.add(getEstudiante(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiantes;
    }
    
    @Override
    public List<Estudiante> obtenerEstudiantesConTutorAsignado() throws SQLException {
        List<Estudiante> estudiantes = new ArrayList<>();
        String consulta =
                "SELECT E.id, E.matricula, E.enRiesgo, E.idTutorAcademico, P.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
                "FROM estudiante E INNER JOIN persona P ON P.id = E.idPersona " +
                "WHERE E.idTutorAcademico != 0";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next()) {
                estudiantes.add(getEstudiante(resultado));
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiantes;
    }
    @Override
    public boolean modificarEstadoDeEstudiante(Estudiante estudiante) throws SQLException{
        boolean resultado = false;
        String consulta ="UPDATE estudiante t " +
                "SET t.enRiesgo = ? " +
                "WHERE t.id = ? ";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setBoolean(1, estudiante.getEnRiesgo());
            sentencia.setInt(2, estudiante.getIdEstudiante());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas != 0) {
                resultado = true;
            }
        } catch(SQLException excepcionSQL) {
            LOGGER.warn(getClass().getName(), excepcionSQL);
            throw excepcionSQL;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return resultado;

    }
}