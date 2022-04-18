package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ExperienciaEducativa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO{
    private final Logger log = Logger.getLogger(ExperienciaEducativa.class);

    @Override
    public List<ExperienciaEducativa> findExperienciasEducativasByName(String serchName) {
        ArrayList<ExperienciaEducativa> experienciasEducativas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT * From experienciaeducativa WHERE nombreEE SOUNDS LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + serchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("Experiencia educativa not found");
            }else {
                do {
                    experienciasEducativas.add(getExperienciaEducativa(resultSet));
                }while (resultSet.next());
            }

        } catch (SQLException e) {
            log.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciasEducativas;
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
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "DELETE FROM experienciaeducativa where (idExperienciaEducativa = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error: No se ha eliminado ninguna experiencia educativa");
            }else {
                log.info("Experiencia educativa eliminada con id" + searchId);
            }
        } catch (SQLException e) {
            log.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    private ExperienciaEducativa getExperienciaEducativa(ResultSet resultSet){
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        int idExperienciaEducativa = 0;
        String nombreEE = "";
        int profesorIdProfesor = 0;
        try {
            idExperienciaEducativa = resultSet.getInt("idExperienciaEducativa");
            nombreEE = resultSet.getString("nombreEE");
            profesorIdProfesor = resultSet.getInt("Profesor_idProfesor");

            experienciaEducativa.setIdExperienciaEducativa(idExperienciaEducativa);
            experienciaEducativa.setNombre(nombreEE);
            experienciaEducativa.setIdProfesor(profesorIdProfesor);

        } catch (SQLException e) {
            log.warn(PersonaDAO.class.getName(), e);

        }
        return experienciaEducativa;

    }
}
