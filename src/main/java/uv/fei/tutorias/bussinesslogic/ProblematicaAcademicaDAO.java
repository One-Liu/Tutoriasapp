package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblematicaAcademicaDAO implements IProblematicaAcademicaDAO{
    private final Logger log = Logger.getLogger(ProblematicaAcademicaDAO.class);

    @Override
    public List<ProblematicaAcademica> findProblematicaAcademicaByDescription(String serchName) {
        ArrayList<ProblematicaAcademica> experienciasEducativas = new ArrayList<>();
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT  * from problematicaacademica where descripcion like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + serchName + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("Problematica academica not found");
            }else {
                int idproblematicaAcademica = 0;
                String descripcion = "";
                int experienciaEducativaIdExperienciaEducativa = 0;
                do {
                    idproblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    descripcion = resultSet.getString("descripcion");
                    experienciaEducativaIdExperienciaEducativa = resultSet.getInt("ExperienciaEducativa_idExperienciaEducativa");

                    ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica(idproblematicaAcademica,descripcion,experienciaEducativaIdExperienciaEducativa);
                    experienciasEducativas.add(problematicaAcademica);
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
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "DELETE FROM problematicaacademica WHERE (idProblematicaAcademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error la problematica academica no se ha eliminado");
            }else {
                log.info("Problematica academica eliminada con el id " + searchId);
            }
        } catch (SQLException e) {
            log.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return true;
    }
}
