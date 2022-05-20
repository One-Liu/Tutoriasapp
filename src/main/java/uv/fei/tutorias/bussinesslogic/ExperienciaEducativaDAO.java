package uv.fei.tutorias.bussinesslogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private final Logger LOG = Logger.getLogger(ExperienciaEducativaDAO.class);

    @Override
    public ObservableList<ExperienciaEducativa> findExperienciasEducativasByName(String serchName) {
        ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT * From experiencia_educativa WHERE nombreEE like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + serchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se ha enconstrado ninguna experiencia educativa con el nombre " + serchName);
            }else {
                do {
                    experienciasEducativas.add(getExperienciaEducativa(resultSet));
                }while (resultSet.next());
            }

        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciasEducativas;
    }

    @Override
    public ExperienciaEducativa findExperienciaEducativaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT * FROM experienciaeducativa WHERE idExperienciaEducativa like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false){
                throw new SQLException("Experiencia educativa not found");
            }else {
                experienciaEducativa = getExperienciaEducativa(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciaEducativa;

    }
    public ObservableList<ExperienciaEducativa> findExperienciasEducativasWithoutProfesor(){
        ObservableList<ExperienciaEducativa> experienciasEducativas = FXCollections.observableArrayList();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT * From experiencia_educativa WHERE idProfesor = 0";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se ha enconstrado ninguna experiencia educativa sin profesor asignado ");
            }else {
                do {
                    experienciasEducativas.add(getExperienciaEducativa(resultSet));
                }while (resultSet.next());
            }

        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return experienciasEducativas;

    }
    @Override
    public boolean addExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO experiencia_educativa(nombreEE, nrc, idProfesor) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, experienciaEducativa.getNombre());
            statement.setString(2,experienciaEducativa.getNrc());
            statement.setInt(3, experienciaEducativa.getIdProfesor());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("ERROR: Experiencia educativa no se ha agregado");
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
    public boolean deleteExperienciaEducativa(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "DELETE FROM experiencia_e where (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error: No se ha eliminado ninguna experiencia educativa");
            }else {
                LOG.info("Experiencia educativa eliminada con id" + searchId);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    public boolean UpdateExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        boolean bandera = false;
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "UPDATE experiencia_educativa SET idProfesor = ? WHERE (id = ?) and (`idProfesor` = '0')";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, experienciaEducativa.getIdProfesor());
            statement.setInt(2,experienciaEducativa.getIdExperienciaEducativa());
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("ERROR: No se ha agregado al profesor");
            }
            bandera = true;
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    private ExperienciaEducativa getExperienciaEducativa(ResultSet resultSet){
        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        int idExperienciaEducativa = 0;
        String nombreEE = "";
        int profesorIdProfesor = 0;
        String nrc = "";
        try {
            idExperienciaEducativa = resultSet.getInt("id");
            nombreEE = resultSet.getString("nombreEE");
            profesorIdProfesor = resultSet.getInt("idProfesor");
            nrc = resultSet.getString("nrc");

            experienciaEducativa.setIdExperienciaEducativa(idExperienciaEducativa);
            experienciaEducativa.setNombre(nombreEE);
            experienciaEducativa.setIdProfesor(profesorIdProfesor);
            experienciaEducativa.setNrc(nrc);

        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);

        }
        return experienciaEducativa;

    }
}
