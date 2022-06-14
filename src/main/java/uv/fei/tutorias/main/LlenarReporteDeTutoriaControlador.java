package uv.fei.tutorias.main;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LlenarReporteDeTutoriaControlador implements Initializable {
    @FXML
    private AnchorPane panel;
    @FXML
    private TableView<ListaDeAsistencia> listaDeAsistenciaTableView;
    @FXML
    private TableColumn<ListaDeAsistencia, String> colNombreEstudiantes;
    @FXML
    private TableColumn<ListaDeAsistencia, Boolean> colEnRiesgo;
    @FXML
    private TableColumn<ListaDeAsistencia, Boolean> colAsistio;
    @FXML
    private TextArea txtComentariosGenerales;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPeriodo;

    public void actEnviar(ActionEvent actionEvent) {
//        agregar reporte general
//        asignarEstudiantesEnRiesgo();
        TutorAcademico tutorAcademico = new TutorAcademico();
        tutorAcademico = (TutorAcademico) UtilidadVentana.recuperarValoresDeLaVentana(panel,tutorAcademico);
        ReporteDeTutoriaAcademicaDAO reporteDeTutoriaAcademicaDAO = new ReporteDeTutoriaAcademicaDAO();
        ReporteDeTutoriaAcademica reporteDeTutoriaAcademica = new ReporteDeTutoriaAcademica();
        reporteDeTutoriaAcademica.setDescripcionGeneral(txtComentariosGenerales.getText());
        reporteDeTutoriaAcademica.setIdTutorAcademico(tutorAcademico.getIdTutorAcademico());
        reporteDeTutoriaAcademica.setIdSesionDeTutoriaAcademica(6);
//        reporteDeTutoriaAcademicaDAO.agregarReporteDeTutoriaAcademica(reporteDeTutoriaAcademica);
    }
    public void asignarEstudiantesEnRiesgo(){
        List<Estudiante> estudiantes = new ArrayList<>();
        for (int i = 1; i < listaDeAsistenciaTableView.getColumns().size(); i++) {
            Estudiante estudiante = new Estudiante();
//            estudiante.setNombre(listaDeAsistenciaTableView.getItems().get(i).getEstudiante().getNombre());
            if (listaDeAsistenciaTableView.getSelectionModel().isSelected(3)){
                estudiante.setEnRiesgo(true);
            }
        }
        for (Estudiante e: estudiantes) {
            System.out.println(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Date dia = new Date();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        ListaDeAsistenciaDAO listaDeAsistenciaDAO = new ListaDeAsistenciaDAO();
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        PeriodoEscolar periodoEscolar;
        SesionDeTutoriaAcademica sesionDeTutoriaAcademica;

        //Recargamos los lebels de la sesion de tutoria
        //TODO el tutor academico deberia de selecciona la sesion de tutoria academica antes
//        try {
//            sesionDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionDeTutoriaAcademicaPorId(6);
//            periodoEscolar = periodoEscolarDAO.obtenerPeriodoEscolarPorId(sesionDeTutoriaAcademica.getIdPeriodoEscolar());
////            ObservableList<ListaDeAsistencia> listasDeAsistencias = listaDeAsistenciaDAO.obtenerListasDeAsistenciaPorIdTutorAcademico(6);
//            observaList(listasDeAsistencias);
//            lblPeriodo.setText(periodoEscolar.getFechaInicio() +" "+periodoEscolar.getFechaTermino());
//            lblFecha.setText(dia.toString());
//            for (ListaDeAsistencia l :
//                    listasDeAsistencias) {
////                l.setEstudiante(estudianteDAO.obtenerEstudiantePorId(l.getIdEstudiante()));
//            }
//        } catch (SQLException e) {
//            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
//            e.printStackTrace();
//        }



    }

    private void observaList(ObservableList<ListaDeAsistencia> listaDeAsistencias) {
//        this.colNombreEstudiantes.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getEstudiante().getNombre()));
//        this.colAsistio.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getAsistio()));
//        this.colAsistio.setCellFactory( tc -> new CheckBoxTableCell<>());
//        this.colEnRiesgo.setCellValueFactory(cellDataFeatures -> new ReadOnlyObjectWrapper(cellDataFeatures.getValue().getEstudiante().getEnRiesgo()));
//        this.colEnRiesgo.setCellFactory( tc -> new CheckBoxTableCell<>());
//        this.listaDeAsistenciaTableView.setItems(listaDeAsistencias);
//        this.listaDeAsistenciaTableView.setEditable(true);
    }

}
