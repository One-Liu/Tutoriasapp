package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public class EstudianteDAO implements IEstudianteDAO {

    private final Logger LOGGER = Logger.getLogger(EstudianteDAO.class);

    @Override
    public ObservableList<Estudiante> obtenerEstudiantes() throws SQLException {
        ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
        String consulta =
        "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                LOGGER.warn(TutorAcademicoDAO.class.getName());
                throw new SQLException("No se han encontrado estudiantes");
            } else {
                do {
                    estudiantes.add(getEstudiante(resultado));
                }while(resultado.next());
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiantes;
    }

    @Override
    public Estudiante obtenerEstudiantePorId(int idEstudiante) throws SQLException {
        Estudiante estudiante;
        String consulta =
        "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno, E.enRiesgo " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
        "WHERE E.id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                SQLException exception = new SQLException();
                LOGGER.warn(EstudianteDAO.class.getName(),exception);
                throw exception;
            } else {
                estudiante = getEstudiante(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiante;
    }

    private Estudiante getEstudiante(ResultSet resultSet)throws SQLException {
        int idEstudiante = 0;
        String matricula = "";
        String nombre = "";
        String apellidoPaterno = "";
        String apellidoMaterno = "";
        boolean enRiesgo = false;
        int idProgramaEducativo = 0;
        int idTutorAcademico = 0;

        idEstudiante = resultSet.getInt("id");
        matricula = resultSet.getString("matricula");
        nombre = resultSet.getString("nombre");
        apellidoPaterno = resultSet.getString("apellidoPaterno");
        apellidoMaterno = resultSet.getString("apellidoMaterno");
        enRiesgo = resultSet.getBoolean("enRiesgo");
        idTutorAcademico = resultSet.getInt("idTutorAcademico");
        idProgramaEducativo = resultSet.getInt("idProgramaEducativo");
        Persona personaEstudiante = new Persona(nombre,apellidoPaterno,apellidoMaterno);
        Estudiante estudiante = new Estudiante(idEstudiante,matricula,personaEstudiante,idTutorAcademico,idProgramaEducativo);
        estudiante.setEnRiesgo(enRiesgo);
        return estudiante;
    }
    @Override
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException {
        boolean validacion;
        PersonaDAO personaDao = new PersonaDAO();
        Persona personaEstudiante = new Persona(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno());
        String consulta = "INSERT INTO estudiante (matricula, idTutorAcademico, idProgramaEducativo, idPersona) VALUES (?,?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            sentencia.setInt(2, estudiante.getIdTutorAcademico());
            sentencia.setInt(3, estudiante.getIdProgramaEducativo());
            sentencia.setInt(4, personaDao.agregarPersona(personaEstudiante));
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                SQLException exception = new SQLException();
                LOGGER.warn(EstudianteDAO.class.getName(),exception);
                throw exception;
            } else {
                validacion = true;
            }
        }finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }

    @Override
    public boolean eliminarEstudiantePorId(int idEstudiante) throws SQLException {
        boolean validacion = false;
        String consulta = "DELETE FROM estudiante WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                SQLException exception = new SQLException();
                LOGGER.warn(EstudianteDAO.class.getName(),exception);
                throw exception;
            } else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    
    @Override
    public boolean modificarEstudiante(Estudiante estudiante) throws SQLException {
        boolean validacion;
        String consulta =
                "UPDATE estudiante " +
                "SET matricula = ?, " +
                "SET idProgramaEducativo = ?, " +
                "SET idTutorAcademico = ?, " +
                "SET enRiesgo = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            sentencia.setInt(2, estudiante.getIdProgramaEducativo());
            sentencia.setInt(3, estudiante.getIdTutorAcademico());
            sentencia.setBoolean(4,estudiante.getEnRiesgo());
            sentencia.setInt(5, estudiante.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                SQLException exception = new SQLException();
                LOGGER.warn(EstudianteDAO.class.getName(),exception);
                throw exception;
            }else {
                validacion = true;
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
    @Override
    public ArrayList<Estudiante> recuperarTodosEstudiantesPorIDTutor(int idTutor){
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        String query = "SELECT matricula, nombre, apellidoPaterno, apellidoMaterno, estudiante.idPersona " +
                "FROM estudiante INNER JOIN persona ON estudiante.idPersona = persona.id";
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                do {
                    Estudiante estudiante = new Estudiante();
                    estudiante.setMatricula(resultSet.getString("matricula"));
                    estudiante.setNombre(resultSet.getString("nombre"));
                    estudiante.setApellidoPaterno(resultSet.getString("apellidoPaterno"));
                    estudiante.setApellidoMaterno(resultSet.getString("apellidoMaterno"));
                    listaEstudiantes.add(estudiante);
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            LOGGER.warn(EstudianteDAO.class.getName(), ex);
        }finally{
            dataBaseConnection.cerrarConexion();
        }
        return listaEstudiantes;
    }
}