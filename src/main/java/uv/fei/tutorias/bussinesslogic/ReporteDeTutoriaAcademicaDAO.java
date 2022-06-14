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
    public boolean agregarReporteDeTutoriaAcademica(ReporteDeTutoriaAcademica reporteDeTutoriaAcademica) throws SQLException {
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
                SQLException ex = new SQLException();
                LOG.warn(ReporteDeTutoriaAcademicaDAO.class.getName(),ex);
                throw ex;
            }
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    @Override
    public boolean eliminarReporteDeTutoriasAcademicasPorId(int idReporteDeTutoriaAcademicaBusqueda) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        try (Connection connection = dataBaseConnection.abrirConexion()) {
            String query = "DELETE FROM reporte_de_tutoria_academica WHERE (id = ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idReporteDeTutoriaAcademicaBusqueda);
            int executeUpdate = statement.executeUpdate();
            if (executeUpdate == 0) {
                SQLException ex = new SQLException();
                LOG.warn(ReporteDeTutoriaAcademicaDAO.class.getName(), ex);
                throw ex;
            }
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return true;
    }

    @Override
    public ReporteDeTutoriaAcademica obtenerReporteDeTutoriaPorId(int idReporteDeTutoriaAcademicaBusqueda) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica;
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT * from reporte_de_tutoria_academica where id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,idReporteDeTutoriaAcademicaBusqueda);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                SQLException ex = new SQLException();
                LOG.warn(ReporteDeTutoriaAcademicaDAO.class.getName(), ex);
                throw ex;
            }else {
                reporteDeTutoriaAcademica = getReporteDeTutoriaAcademica(resultSet);
            }
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return reporteDeTutoriaAcademica;
    }
    
    @Override
    public ReporteDeTutoriaAcademica obtenerReporteDeTutoriaPorIdSesionTutoriaYIdTutor(ReporteDeTutoriaAcademica reporteDeTutoriaAcademicaBusqueda) throws SQLException {
        ConexionBD dataBaseConnection = new ConexionBD();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica;
        try (Connection connection = dataBaseConnection.abrirConexion()){
            String query = "SELECT * from reporte_de_tutoria_academica where idSesionDeTutoriaAcademica = ? AND idTutorAcademico = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, reporteDeTutoriaAcademicaBusqueda.getIdSesionDeTutoriaAcademica());
            statement.setInt(2, reporteDeTutoriaAcademicaBusqueda.getIdTutorAcademico());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                SQLException ex = new SQLException();
                LOG.warn(ReporteDeTutoriaAcademicaDAO.class.getName(),ex);
                throw ex;
            }else {
                   reporteDeTutoriaAcademica = getReporteDeTutoriaAcademica(resultSet);
            }
        } finally {
            dataBaseConnection.cerrarConexion();
        }
        return reporteDeTutoriaAcademica;
    }

    private ReporteDeTutoriaAcademica getReporteDeTutoriaAcademica(ResultSet resultSet) throws SQLException{
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        int idReporteDeTutoriaAcademica;
        String descripcion;
        int idsesionDeTutoriaAcademica;
        int idTutorAcademico;
        int idFechaCierreEntregaReporte;

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

        return reporteDeTutoriaAcademica;

    }
}
