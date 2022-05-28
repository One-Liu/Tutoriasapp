package uv.fei.tutorias.bussinesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.Persona;
import uv.fei.tutorias.domain.Estudiante;

// author @liu
public class EstudianteDAO implements IEstudianteDAO {

    private final Logger LOGGER = Logger.getLogger(EstudianteDAO.class);

    @Override
    public ArrayList<Estudiante> obtenerEstudiantes() throws SQLException {
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        String consulta = 
        "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
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
        Estudiante estudiante = new Estudiante();
        String consulta = 
        "SELECT E.id, E.matricula, E.idTutorAcademico, E.idProgramaEducativo, P.nombre, P.apellidoPaterno, P.apellidoMaterno " +
        "FROM estudiante E LEFT JOIN persona P ON P.id = E.idPersona " +
        "WHERE E.id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setInt(1, idEstudiante);
            ResultSet resultado = sentencia.executeQuery();
            if(!resultado.next()) {
                throw new SQLException("No se ha encontrado al estudiante con el id " + idEstudiante);
            } else {
                estudiante = getEstudiante(resultado);
            }
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return estudiante;
    }

    private Estudiante getEstudiante(ResultSet resultado) throws SQLException {
        int idEstudiante;
        String matricula;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        int idProgramaEducativo;
        int idTutorAcademico;
        
        idEstudiante = resultado.getInt("id");
        matricula = resultado.getString("matricula");
        nombre = resultado.getString("nombre");
        apellidoPaterno = resultado.getString("apellidoPaterno");
        apellidoMaterno = resultado.getString("apellidoMaterno");
        idTutorAcademico = resultado.getInt("idTutorAcademico");
        idProgramaEducativo = resultado.getInt("idProgramaEducativo");
        
        Persona personaEstudiante = new Persona(nombre,apellidoPaterno,apellidoMaterno);
        Estudiante estudiante = new Estudiante(idEstudiante,matricula,personaEstudiante,idTutorAcademico,idProgramaEducativo);
        return estudiante;
    }

    @Override
    public boolean agregarEstudiante(Estudiante estudiante) throws SQLException {
        boolean validacion = false;
        PersonaDAO personaDao = new PersonaDAO();
        Persona personaEstudiante = new Persona(estudiante.getNombre(), estudiante.getApellidoPaterno(), estudiante.getApellidoMaterno());
        String consulta = "INSERT INTO estudiante (matricula, idTutorAcademico, idProgramaEducativo, idPersona) VALUES (?,?,?,?)";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            sentencia.setInt(2, estudiante.getIdTutorAcademico());
            sentencia.setInt(3, estudiante.getIdProgramaEducativo());
            sentencia.setInt(4, personaDao.addPersonaReturnId(personaEstudiante));
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: El estudiante no se ha agregado");
            } else {
                validacion = true;
            }
        } finally {
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
                throw new SQLException("ERROR: No se ha eliminado al estudiante con el id " + idEstudiante);
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
        boolean validacion = false;
        String consulta = 
                "UPDATE estudiante " + 
                "SET matricula = ?, " +
                "SET idProgramaEducativo = ?, " +
                "SET idTutorAcademico = ? " +
                "WHERE id = ?";
        ConexionBD baseDeDatos = new ConexionBD();
        try(Connection conexion = baseDeDatos.abrirConexion()) {
            PreparedStatement sentencia = conexion.prepareStatement(consulta);
            sentencia.setString(1, estudiante.getMatricula());
            sentencia.setInt(2, estudiante.getIdProgramaEducativo());
            sentencia.setInt(3, estudiante.getIdTutorAcademico());
            sentencia.setInt(4, estudiante.getId());
            int columnasAfectadas = sentencia.executeUpdate();
            if(columnasAfectadas == 0) {
                throw new SQLException("ERROR: No se ha modificado el estudiante con el id " + estudiante.getId());
            }
            validacion = true;
        } finally {
            baseDeDatos.cerrarConexion();
        }
        return validacion;
    }
}