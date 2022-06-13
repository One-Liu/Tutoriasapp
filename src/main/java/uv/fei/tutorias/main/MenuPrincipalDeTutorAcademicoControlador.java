package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uv.fei.tutorias.domain.TutorAcademico;

import java.io.IOException;

public class MenuPrincipalDeTutorAcademicoControlador {
    @FXML
    private AnchorPane panel;

    public void clicLLenarReporteDeTutoriasAcademicas(ActionEvent actionEvent) {
        TutorAcademico tutorAcademico = new TutorAcademico();
        tutorAcademico = (TutorAcademico) UtilidadVentana.recuperarValoresDeLaVentana(panel,tutorAcademico);
        System.out.println(tutorAcademico);
//        Utilidad.pasarValoresEntreVentanas(tutorAcademico,"src\\main\\resources\\uv.fei.tutorias.main\\GUILlenarReporteDeTutoria.fxml","LLenar reporte de tutoria",actionEvent);

    }

    public void clicConsultarProblematicaAcademica(ActionEvent actionEvent) {
    }
}
