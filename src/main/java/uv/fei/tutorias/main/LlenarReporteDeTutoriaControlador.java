package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LlenarReporteDeTutoriaControlador implements Initializable {
    @FXML
    private AnchorPane panel;
    @FXML
    private TableView<ListaDeAsistencia> listaDeAsistenciaTableView;
    @FXML
    private TableColumn<ListaDeAsistencia, String> colNombreEstudiantes;
    @FXML
    private TableColumn<ListaDeAsistencia, String> colEnRiesgo;
    @FXML
    private TableColumn<ListaDeAsistencia, Estudiante> colAsistio;
    @FXML
    private TextArea txtComentariosGenerales;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPeriodo;

    public void actEnviar(ActionEvent actionEvent) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        tutorAcademico = (TutorAcademico) Utilidad.recuperarValoresDeLaVentana(panel,tutorAcademico);
        System.out.println(tutorAcademico);
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        reporteDeTutoriaAcademica.setDescripcionGeneral(txtComentariosGenerales.getText());
        reporteDeTutoriaAcademica.setIdTutorAcademico(tutorAcademico.getId());
        reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(6);
        reporteDeTutoriaAcademicaDAO.addReporteDeTutoriaAcademica(reporteDeTutoriaAcademica);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        PeriodoEscolar periodoEscolar;
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica;

        //Recargamos los lebels de la sesion de tutoria
        //TODO el tutor academico deberia de selecciona la sesion de tutoria academica antes
        sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.findSesionDeTutoriaAcademicaById(6);
        periodoEscolar = periodoEscolarDAO.findPeriodoEscolarById(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
        lblFecha.setText(sesionDeTutoriaAcademica.getFecha());
        lblPeriodo.setText(periodoEscolar.getFechaInicio() + " " + periodoEscolar.getFechaTermino());

        //llenamos la columna de estudiantes con la lista de asistencia de la misma sesion de tutoria academica
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        ObservableList<ListaDeAsistencia> listasDeAsistencias = listaDeAsistenciaDAO.findListasDeAsistenciaByIdSesionDeTutoriaAcademica(6);
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        //a la lista asistencia la llenamos con el estudiante de estudianteDAO
        for (ListaDeAsistencia l :
                listasDeAsistencias) {
            l.setEstudiante(estudianteDAO.findEstudianteById(l.getIdEstudiante()));
        }
        observaList(listasDeAsistencias);




    }

    private void observaList(ObservableList<ListaDeAsistencia> listaDeAsistencias) {
        this.colNombreEstudiantes.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getEstudiante().getNombre()));
        this.colAsistio.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getAsistio()));
        this.colEnRiesgo.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getEstudiante().getEnRiesgo()));
        this.listaDeAsistenciaTableView.setItems(listaDeAsistencias);
    }

}
