package uv.fei.tutorias.bussinesslogic;

import org.apache.log4j.Logger;
import uv.fei.tutorias.dataaccess.ConexionBD;
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
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "INSERT INTO reporte_de_tutoria_academica(descripcionGeneral, idSesionDeTutoriaAcademica, idTutorAcademico, idFechaCierreEntregaReporte) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reporteDeTutoriaAcademica.getDescripcionGeneral());
            statement.setInt(2, reporteDeTutoriaAcademica.getIdSesionDeTutoriaAcademica());
            statement.setInt(3, reporteDeTutoriaAcademica.getIdTutorAcademico());
            statement.setInt(4, reporteDeTutoriaAcademica.getIdFechaCierreEntregaReporte());

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: El reporte de tutoria academica no se ha agregado");
            }else {
                bandera = true;
            }
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
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {

            String query = "DELETE FROM reporte_de_tutoria_academica WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, searchId);

            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                throw new SQLException("ERROR: No se ha eliminado ninguna reporte de tutorias academicas con el id " + searchId);
            }else {
                bandera =true;
            }
        } catch (SQLException ex) {
            LOG.warn(PersonaDAO.class.getName(), ex);
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return bandera;
    }

    @Override
    public ReporteDeTutoriaAcademica findReporteDeTutoriaById(int searchId) {
        ConexionBD dataBaseConnection = new ConexionBD();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();

        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT * from reporte_de_tutoria_academica where id like ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,"%" + searchId + "%");
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                throw new SQLException("No se ha encontrado el reporte de tutoria con el id " + searchId);
            }else {
                int idReporteDeTutoriaAcademica = 0;
                String descripcion = "";
                int idsesionDeTutoriaAcademica = 0;
                int idTutorAcademico = 0;
                int idFechaCierreEntregaReporte = 0;
                do {
                    idReporteDeTutoriaAcademica = resultSet.getInt("id");
                    descripcion = resultSet.getString("descripcionGeneral");
                    idsesionDeTutoriaAcademica = resultSet.getInt("idSesionDeTutoriaAcademica");
                    idTutorAcademico = resultSet.getInt("idTutorAcademico");
                    idFechaCierreEntregaReporte = resultSet.getInt("idFechaCierreEntregaReporte");

                    reporteDeTutoriaAcademica.setId(idReporteDeTutoriaAcademica);
                    reporteDeTutoriaAcademica.setDescripcionGeneral(descripcion);
                    reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(idsesionDeTutoriaAcademica);
                    reporteDeTutoriaAcademica.setIdTutorAcademico(idTutorAcademico);
                    reporteDeTutoriaAcademica.setIdFechaCierreEntregaReporte(idFechaCierreEntregaReporte);
                }while (resultSet.next());
            }
        } catch (SQLException e) {
            LOG.warn(PersonaDAO.class.getName(), e);
        }
        return reporteDeTutoriaAcademica;
    }
}
