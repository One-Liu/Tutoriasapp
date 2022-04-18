package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProblematicaAcademicaDAO implements IProblematicaAcademicaDAO{
    private final Logger log = Logger.getLogger(ProblematicaAcademicaDAO.class);

    @Override
    public List<ExperienciaEducativa> findProblematicasAcademicasByName(String serchName) {

        return null;
    }

    @Override
    public ProblematicaAcademica findProblematicaAcademicaById(int searchId) {
        return null;
    }

    @Override
    public boolean addProblematicaAcademica(ProblematicaAcademica problematicaAcademica) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "INSERT INTO problematicaacademica(descripcion, ExperienciaEducativa_idExperienciaEducativa) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, problematicaAcademica.getDescripcion());
            statement.setInt(2,problematicaAcademica.getIdExperienciaEducativa());
            int excecuteUpadate = statement.executeUpdate();
            if (excecuteUpadate == 0){
                throw new SQLException("Error problematica academica no se ha agregado");
            }
        } catch (SQLException e) {
            log.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean deleteProblematicaAcademicaById(int searchId) {
        return false;
    }
}
