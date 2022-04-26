package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.Estudiante_ProblematicaAcademica;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstudianteProblematicaAcademicaDAO implements IEstudianteProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(EstudianteProblematicaAcademicaDAO.class);

    @Override
    public Estudiante_ProblematicaAcademica findEstudianteProblematicaAcademicaById(int searchId) {
       return null;
    }

    @Override
    public boolean addEstudianteProblematicaAcademica(Estudiante_ProblematicaAcademica estudiante_problematicaAcademica) {
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
            }
            bandera = true;
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public boolean deleteEstudinateProblematicaAcademica(int searchId) {
        return false;
    }
}
