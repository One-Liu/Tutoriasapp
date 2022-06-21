package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import uv.fei.tutorias.utilidades.TablaEstudiante_asistioEnRiesgo;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class LlenarReporteDeTutoriaControlador implements Initializable {
    @FXML
    private AnchorPane panel;
    @FXML
    private TableView<TablaEstudiante_asistioEnRiesgo> tblEstudiantes;
    @FXML
    private TableColumn<TablaEstudiante_asistioEnRiesgo, String> colNombreEstudiantes;
    @FXML
    private TableColumn<TablaEstudiante_asistioEnRiesgo, CheckBox> colEnRiesgo;
    @FXML
    private TableColumn<TablaEstudiante_asistioEnRiesgo, CheckBox> colAsistio;
    @FXML
    private TextArea txtComentariosGenerales;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPeriodo;

    @Setter
    SesionDeTutoriaAcademica sesionDeTutoriaAcademica;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            recuperarTutorados();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void cargarDatos() throws SQLException {
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        PeriodoEscolar periodoEscolar = periodoEscolarDAO.obtenerPeriodoEscolarPorId(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
        lblFecha.setText(sesionDeTutoriaAcademica.getFechaConFormato());
        lblPeriodo.setText(periodoEscolar.getFechas());
    }

    public void actEnviar(ActionEvent actionEvent) {
        try {
            if (!datosNulos()){
                registrarAsistentesyEnRiesgo();
                registrarComentariosGenerales();
                UtilidadVentana.mostrarAlertaConfirmacion("El reporte ha sido guardado"," Reporte de tutoria guardado exitosamente", Alert.AlertType.CONFIRMATION);
                UtilidadVentana.cerrarVentana(actionEvent);
            }else {
                UtilidadVentana.mostrarAlertaConfirmacion("Campos nulos","No debe de haber campos nulos", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void recuperarTutorados() throws SQLException {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        List<Estudiante> estudiantesConElMismoTutor = estudianteDAO.obtenerEstudiantesDeTutor(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico());
        ObservableList<TablaEstudiante_asistioEnRiesgo> listaObserbable = FXCollections.observableArrayList();
        for(Estudiante estudiante : estudiantesConElMismoTutor) {
            TablaEstudiante_asistioEnRiesgo objeto = new TablaEstudiante_asistioEnRiesgo();
            objeto.setEstudiante(estudiante);
            listaObserbable.add(objeto);
        }
        tblEstudiantes.setItems(listaObserbable);
        configurarTabla(listaObserbable);

    }
    private void configurarTabla(ObservableList<TablaEstudiante_asistioEnRiesgo> proyectos) {
        this.colEnRiesgo.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper<>(cellDataFeatures.getValue().getEnRiesgo()));
        this.colAsistio.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper<>(cellDataFeatures.getValue().getAsistio()));
        this.colNombreEstudiantes.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper<>(cellDataFeatures.getValue().getEstudiante().getNombre()));
        this.tblEstudiantes.setItems(proyectos);
    }

    private void registrarAsistentesyEnRiesgo() throws SQLException {
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        ListaDeAsistencia listaDeAsistencia = new ListaDeAsistencia();
        Estudiante estudiante;
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        ObservableList<TablaEstudiante_asistioEnRiesgo> estudiantes = tblEstudiantes.getItems();
        boolean resultadoLA = false;
        for (TablaEstudiante_asistioEnRiesgo ta :
                estudiantes) {
            if (ta.getAsistio().isSelected()) {
                listaDeAsistencia.setIdEstudiante(ta.estudiante.getIdEstudiante());
                listaDeAsistencia.setIdSesionDeTutoriaAcademica(sesionDeTutoriaAcademica.getId());
                listaDeAsistencia.setAsistio(true);
                resultadoLA = listaDeAsistenciaDAO.modificarAsistencia(listaDeAsistencia);
            }else {
                listaDeAsistencia.setIdEstudiante(ta.estudiante.getIdEstudiante());
                listaDeAsistencia.setIdSesionDeTutoriaAcademica(sesionDeTutoriaAcademica.getId());
                listaDeAsistencia.setAsistio(false);
               resultadoLA = listaDeAsistenciaDAO.modificarAsistencia(listaDeAsistencia);
            }
            if (ta.getEnRiesgo().isSelected()){
                estudiante = ta.estudiante;
                estudiante.setEnRiesgo(true);
                estudianteDAO.modificarEstadoDeEstudiante(estudiante);
            }else {
                estudiante = ta.estudiante;
                estudiante.setEnRiesgo(false);
                estudianteDAO.modificarEstadoDeEstudiante(estudiante);
            }
        }
        if (!resultadoLA){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Lista de asistencia vacia","Asegurece de registrar los horarios", Alert.AlertType.ERROR);
        }
    }
    private boolean datosNulos(){
        return txtComentariosGenerales.getText().isEmpty();
    }
    private void registrarComentariosGenerales() throws SQLException {
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();

        reporteDeTutoriaAcademica.setIdTutorAcademico(DatosGlobalesDeSesion.getDatosGlobalesDeSesion().getTutorAcademico().getIdTutorAcademico());
        reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(sesionDeTutoriaAcademica.getId());
        reporteDeTutoriaAcademica.setDescripcionGeneral(txtComentariosGenerales.getText());

        reporteDeTutoriaAcademicaDAO.agregarReporteDeTutoriaAcademica(reporteDeTutoriaAcademica);

    }
}