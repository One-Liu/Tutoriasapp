package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProblematicaAcademicaDAO implements IProblematicaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(ProblematicaAcademicaDAO.class);

    @Override
    public ObservableList<ProblematicaAcademica> obtenerProblematicaAcademicaPorDescripcion(String descripcionBusqueda) throws SQLException {
        ObservableList<ProblematicaAcademica> problematicasAcademicas = FXCollections.observableArrayList();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  * from problematicaacademica where descripcion like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + descripcionBusqueda + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                LOG.warn(PersonaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            }else {
                int idproblematicaAcademica = 0;
                String titulo = "";
                String descripcion = "";
                int idExperienciaEducativa = 0;
                int idSolucionProblematicaAcademica = 0;
                int idSesionDeTutoriaAcademica = 0;
                int idProfesor = 0;
                do {
                    idproblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    titulo = resultSet.getString("titulo");
                    descripcion = resultSet.getString("descripcion");
                    idExperienciaEducativa = resultSet.getInt("idExperienciaEducativa");
                    idSolucionProblematicaAcademica = resultSet.getInt("idSolucionProblematicaAcademica");
                    idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
                    idProfesor = resultSet.getInt("idProfesor");
                    
                    ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica(idproblematicaAcademica,titulo,descripcion,idSolucionProblematicaAcademica,idSesionDeTutoriaAcademica,idExperienciaEducativa,idProfesor);
                    problematicasAcademicas.add(problematicaAcademica);
                }while (resultSet.next());
            }

        } finally {
            dataBaseConnection.cerrarConexion();
        }

        return problematicasAcademicas;
    }

    @Override
    public ObservableList<ProblematicaAcademica> obtenerProblematicasAcademicas() throws SQLException {
        ObservableList<ProblematicaAcademica> experienciasEducativas = FXCollections.observableArrayList();
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT  * from problematicaacademica";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                LOG.warn(PersonaDAO.class.getName(), new SQLException());
                throw new SQLException("No hay conexión con la base de datos");
            }else {
                int idproblematicaAcademica = 0;
                String titulo = "";
                String descripcion = "";
                int idExperienciaEducativa = 0;
                int idSolucionProblematicaAcademica = 0;
                int idSesionDeTutoriaAcademica = 0;
                int idProfesor = 0;
                do {
                    idproblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    titulo = resultSet.getString("titulo");
                    descripcion = resultSet.getString("descripcion");
                    idExperienciaEducativa = resultSet.getInt("idExperienciaEducativa");
                    idSolucionProblematicaAcademica = resultSet.getInt("idSolucionProblematicaAcademica");
                    idSesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
                    idProfesor = resultSet.getInt("idProfesor");
                    
                    ProblematicaAcademica problematicaAcademica= new ProblematicaAcademica(idproblematicaAcademica,titulo,descripcion,idSolucionProblematicaAcademica,idSesionDeTutoriaAcademica,idExperienciaEducativa,idProfesor);
                    experienciasEducativas.add(problematicaAcademica);
                }while (resultSet.next());
            }

        } finally {
            dataBaseConnection.cerrarConexion();
        }

        return experienciasEducativas;
    }


    @Override
    public ProblematicaAcademica obtenerProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) {
        ConexionBD dataBaseConnection = new ConexionBD();
        ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();

        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT * from problematicaacademica where idProblematicaAcademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + idProblematicaAcademicaBusqueda + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("Problematica academica not found");
            }else {
                int idProblematicaAcademica = 0;
                String descripcion = "";
                int idExperienciaEducativa = 0;
                do {
                    idProblematicaAcademica = resultSet.getInt("idProblematicaAcademica");
                    descripcion = resultSet.getString("descripcion");
                    idExperienciaEducativa = resultSet.getInt("ExperienciaEducativa_idExperienciaEducativa");

                    problematicaAcademica.setIdProblematicaAcademica(idProblematicaAcademica);
                    problematicaAcademica.setDescripcion(descripcion);
                    problematicaAcademica.setIdExperienciaEducativa(idExperienciaEducativa);
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }
        return problematicaAcademica;
    }

    @Override
    public boolean agregarProblematicaAcademica(ProblematicaAcademica problematicaAcademica) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        boolean result = false;
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "INSERT INTO problematica_academica(titulo, descripcion, idExperienciaEducativa) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, problematicaAcademica.getTitulo());
            statement.setString(2, problematicaAcademica.getDescripcion());
            statement.setInt(3,problematicaAcademica.getIdExperienciaEducativa());
            int excecuteUpdate = statement.executeUpdate();
            if (excecuteUpdate == 0){
                LOG.warn(ProblematicaAcademicaDAO.class.getName(), new SQLException());
                throw new SQLException("Error problematica academica no se ha agregado");
            }else{
                result = true;
            }
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return result;
    }

    @Override
    public boolean eliminarProblematicaAcademicaPorId(int idProblematicaAcademicaBusqueda) {
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "DELETE FROM problematicaacademica WHERE (idProblematicaAcademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idProblematicaAcademicaBusqueda);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0){
                throw new SQLException("Error la problematica academica no se ha eliminado");
            }else {
                LOG.info("Problematica academica eliminada con el id " + idProblematicaAcademicaBusqueda);
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return true;
    }
}
