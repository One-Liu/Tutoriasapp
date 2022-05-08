package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.EstudianteProblematicaAcademica;
import uv.fei.tutorias.domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteProblematicaAcademicaDAO implements IEstudianteProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(EstudianteProblematicaAcademicaDAO.class);

    //este metodo se hizo con la finalidad de saber todos los estudiantes que tienen una misma problematica academica
    @Override
    public List<EstudianteProblematicaAcademica> findEstudiantesProblematicaAcademicaById(int searchIdproblematicaAcademica) {
        ArrayList<EstudianteProblematicaAcademica> estudiantesproblematicaAcademica = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "Select * from estudiante_problematicaacademica where ProblematicaAcademica_idProblematicaAcademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchIdproblematicaAcademica + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException("No se han encontrado estudiantes con la problematica academica numero: "  + searchIdproblematicaAcademica);
            } else {
                int idProblematicaAcademica = 0;
                int idEstudiante = 0;
                int idEstudianteProblematicaAcademica = 0;
                do {
                    idProblematicaAcademica = resultSet.getInt("ProblematicaAcademica_idProblematicaAcademica");
                    idEstudiante = resultSet.getInt("Estudiante_idEstudiante");
                    idEstudianteProblematicaAcademica = resultSet.getInt("idEstudiante_ProblematicaAcademica");

                    EstudianteProblematicaAcademica estudianteProblematicaAcademica = new EstudianteProblematicaAcademica();
                    estudianteProblematicaAcademica.setIdEstudiante(idEstudiante);
                    estudianteProblematicaAcademica.setIdEstudiante(idProblematicaAcademica);
                    estudianteProblematicaAcademica.setIdEstudiante_ProblematicaAcademica(idEstudianteProblematicaAcademica);

                    estudiantesproblematicaAcademica.add(estudianteProblematicaAcademica);
                } while (resultSet.next());
            }
        } catch (SQLException ex) {
            LOG.warn(EstudianteProblematicaAcademicaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return estudiantesproblematicaAcademica;

    }

    @Override
    public boolean addEstudianteProblematicaAcademica(EstudianteProblematicaAcademica estudiante_problematicaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO estudiante_problematicaacademica(Estudiante_idEstudiante, ProblematicaAcademica_idProblematicaAcademica) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, estudiante_problematicaAcademica.getIdEstudiante());
            statement.setInt(2, estudiante_problematicaAcademica.getIdProblematicaAcademica());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("ERROR: No se ha asignado una problematica academica a un estudiante");
            }else {
                bandera = true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean deleteEstudinateProblematicaAcademicaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "DELETE FROM estudiante_problematicaacademica WHERE (idEstudiante_ProblematicaAcademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ninguna relacion con estudiante-problematicaAcademica");
            }
            bandera = true;
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }
}
