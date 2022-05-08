package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.DataBaseConnection;
import uv.fei.tutorias.domain.ReporteDeTutoriaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteDeTutoriaAcademicaDAO implements IReporteDeTutoriaAcademicaDAO{
    private final Logger LOG = Logger.getLogger(ReporteDeTutoriaAcademicaDAO.class);


    @Override
    public boolean addReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) {
        boolean bandera = false;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {
            String query = "INSERT INTO reportedetutoriaacademica(descripcionGeneral, SesionDeTutoriaAcademica_idSesionDeTutoriaAcademica, TutorAcademico_idTutorAcademico) VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reporteDeTutoriaAcademica.getDescripcionGeneral());
            statement.setInt(2, reporteDeTutoriaAcademica.getIdSesionDeTutoriaAcademica());
            statement.setInt(3, reporteDeTutoriaAcademica.getIdTutorAcademico());

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El reporte de tutoria academica no se ha agregado");
            }
            bandera = true;
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        }finally {
            dataBaseConnection.cerrarConexion();
        }

        return bandera;
    }

    @Override
    public boolean deleteReporteDeTutoriasAcademicasById(int searchId) {
        boolean bandera = false;
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        try (Connection connection = dataBaseConnection.getConnection()) {

            String query = "DELETE FROM reportedetutoriaacademica WHERE (idReporteDeTutoriaAcademica = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ninguna tabla");
            }
            bandera =true;
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public ReporteDeTutoriaAcademica findReporteDeTutoriaById(int searchId) {
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();

        try (Connection connection = dataBaseConnection.getConnection()){
            String query = "SELECT * from reportedetutoriaacademica where idReporteDeTutoriaAcademica like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next() == false){
                throw new SQLException("Reporte de tutoria academica not found");
            }else {
                int idReporteDeTutoriaAcademica = 0;
                String descripcion = "";
                int idsesionDeTutoriaAcademica = 0;
                int idTutorAcademico = 0;
                do {
                    idReporteDeTutoriaAcademica = resultSet.getInt("idReporteDeTutoriaAcademica");
                    descripcion = resultSet.getString("descripcionGeneral");
                    idsesionDeTutoriaAcademica = resultSet.getInt("SesionDeTutoriaAcademica_idSesionDeTutoriaAcademica");
                    idTutorAcademico = resultSet.getInt("TutorAcademico_idTutorAcademico");

                    reporteDeTutoriaAcademica.setIdReporteDeTutoriaAcademica(idReporteDeTutoriaAcademica);
                    reporteDeTutoriaAcademica.setIdReporteDeTutoriaAcademica(idsesionDeTutoriaAcademica);
                    reporteDeTutoriaAcademica.setDescripcionGeneral(descripcion);
                    reporteDeTutoriaAcademica.setIdTutorAcademico(idTutorAcademico);
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }
        return reporteDeTutoriaAcademica;
    }
}
