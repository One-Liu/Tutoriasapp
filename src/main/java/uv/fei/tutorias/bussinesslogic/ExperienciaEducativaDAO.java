package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO{
    private final Logger log = Logger.getLogger(ExperienciaEducativa.class);

    @Override
    public List<ExperienciaEducativa> findExperienciasEducativasByName(String serchName) {
        return null;
    }

    @Override
    public ExperienciaEducativa findExperienciaEducativa(int searchId) {
        return null;
    }

    @Override
    public boolean addExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO experienciaeducativa(nombreEE, Profesor_idProfesor) VALUES(?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, experienciaEducativa.getNombre());
            statement.setInt(2, experienciaEducativa.getIdProfesor());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("ERROR: Experiencia educativa no se ha agregado");
            }
        } catch (SQLException ex) {
            log.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean deleteExperienciaEducativa(int searchId) {
        return false;
    }
}
