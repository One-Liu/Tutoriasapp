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
        ComunicacionEntreVentanas comunicacionEntreVentanas = new ComunicacionEntreVentanas();
        TutorAcademico tutorAcademico;
        comunicacionEntreVentanas.setActionEvent(actionEvent);
        tutorAcademico = (TutorAcademico) comunicacionEntreVentanas.recuperarValoresDeLaVentana();
        System.out.println(tutorAcademico);
        System.out.println(comunicacionEntreVentanas.getObjetoRecuperado());
        comunicacionEntreVentanas.setNombreDeLaVentana("Llenar reporte de tutoria");
//        comunicacionEntreVentanas.setObjeto(tutorAcademico);
        comunicacionEntreVentanas.setNombreFxml("GUILlenarReporteDeTutoria.fxml");
        comunicacionEntreVentanas.setActionEvent(actionEvent);
        comunicacionEntreVentanas.pasarValoresEntreVentanas();
    }


    public void actConsultarProblematicaAcademica(ActionEvent actionEvent) {
    }
}
