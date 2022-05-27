package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uv.fei.tutorias.domain.TutorAcademico;

import java.io.IOException;

public class GUIMenuPrincipalDeTutorAcademico {
    @FXML
    private AnchorPane panel;

    public void actLLenarReporteDeTutoriasAcademicas(ActionEvent actionEvent) throws IOException {
        TutorAcademico tutorAcademico = new TutorAcademico();
        tutorAcademico = (TutorAcademico) Utilidad.recuperarValoresDeLaVentana(panel,tutorAcademico);
        Utilidad.pasarValoresEntreVentanas(tutorAcademico,"src\\main\\resources\\uv.fei.tutorias.main\\GUILlenarReporteDeTutoria.fxml","LLenar reporte de tutoria",actionEvent);
    }

}
