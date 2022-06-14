package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.EstudiantesProblematicasAcademicas;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudiantesProblematicasAcademicasDAO implements IEstudianteProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(EstudiantesProblematicasAcademicasDAO.class);

    //este metodo se hizo con la finalidad de saber todos los estudiantes que tienen asignado una misma problematica academica
    @Override
    public List<EstudiantesProblematicasAcademicas> obtenerEstudianteProblematicaAcademicaPorId(int searchIdproblematicaAcademica) throws SQLException {
        ArrayList<EstudiantesProblematicasAcademicas> estudiantesproblematicaAcademica = new ArrayList<>();
        ConexionBD dataBaseConnection = new ConexionBD();
        String query = "select nombre, apellidoPaterno, apellidoMaterno, pa.descripcion from estudiantes_problematicasacademicas ep" +
                "inner join estudiante e on e.id = ep.idEstudiante" +
                "    inner join persona p on e.idPersona = p.id" +
                "    inner join problematica_academica pa on pa.id = ep.idProblematicaAcademica" +
                "    where ep.idProblematicaAcademica = (?)";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchIdproblematicaAcademica);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                estudiantesproblematicaAcademica.add(getProblematicaAcademica(resultSet));
            }
        } catch (SQLException ex) {
            LOG.warn(getClass().getName(), ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return estudiantesproblematicaAcademica;

    }

    @Override
    public boolean agregarEstudianteProblemtaicaAcademica(EstudiantesProblematicasAcademicas estudiantesProblematicasAcademicas) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO estudiantes_problematicasacademicas(idEstudiante,idProblematicaAcademica) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, estudiantesProblematicasAcademicas.getIdEstudiante());
            statement.setInt(2, estudiantesProblematicasAcademicas.getIdProblematicaAcademica());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0){
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean eliminarEstudianteProblematicaAcademicaPorId(int searchId) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean bandera = false;
        String query = "DELETE FROM estudiantes_problematicasacademicas WHERE (id = ?)";
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate != 0) {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
            throw ex;
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }
    private EstudiantesProblematicasAcademicas getProblematicaAcademica(ResultSet resultSet) throws SQLException {
        EstudiantesProblematicasAcademicas estudianteProblematicaAcademica = new EstudiantesProblematicasAcademicas();

        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        String descripcion;
        nombre = resultSet.getString("nombre");
        apellidoPaterno = resultSet.getString("apellidoPaterno");
        apellidoMaterno = resultSet.getString("apellidoMaterno");
        descripcion = resultSet.getString("descripcion");
        estudianteProblematicaAcademica.getEstudiante().setNombre(nombre);
        estudianteProblematicaAcademica.getEstudiante().setApellidoPaterno(apellidoPaterno);
        estudianteProblematicaAcademica.getEstudiante().setApellidoMaterno(apellidoMaterno);
        estudianteProblematicaAcademica.getProblematicaAcademica().setDescripcion(descripcion);
        return estudianteProblematicaAcademica;

    }
}
