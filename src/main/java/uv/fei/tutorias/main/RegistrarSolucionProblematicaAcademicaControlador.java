package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.utilidades.UtilidadVentana;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class RegistrarSolucionProblematicaAcademicaControlador implements Initializable{
    @FXML
    private TextArea taDescripcioProbleamticaAcademica;
    @FXML
    private Label lblProblematicaAcadémica;
    @FXML
    private Label lblExperienciaEducativa;
    @FXML
    private Label lblProfesor;
    @FXML
    private Label lblFechaReporte;
    @FXML
    private TextArea taSolucion;

    @Setter
    ProblematicaAcademica problematicaAcademica;
    @Setter
    SesionDeTutoriaAcademica sesionDeTutoriaAcademica;
    @Setter
    ExperienciaEducativa experienciaEducativa;
    @Setter
    Profesor profesor;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void guardarBtn(ActionEvent actionEvent) {
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        ProblematicaAcademicaDAO problematicaAcademicaDAO = new ProblematicaAcademicaDAO();
        int idSolucion;
        if (!taSolucion.getText().isEmpty()){
            try {
                idSolucion = solucionAProblematicaAcademicaDAO.agregarSolucionProblematicaAcademica(taSolucion.getText());
                problematicaAcademica.setIdSolucionProblematicaAcademica(idSolucion);
                problematicaAcademicaDAO.modificarSolucionAProblematicaAcademica(problematicaAcademica);

            } catch (SQLException e) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
            UtilidadVentana.mostrarAlertaConfirmacion("Cambios guardados",
            "Los cambios se han guardado exitosamente", Alert.AlertType.CONFIRMATION);
            UtilidadVentana.cerrarVentana(actionEvent);
        }else {
            UtilidadVentana.mostrarAlertaSinConfirmacion("Datos vacios",
                    "Asegurece de llenar los campos", Alert.AlertType.WARNING);
        }

    }

    public void cancelarBtn(ActionEvent actionEvent) {
        UtilidadVentana.cerrarVentana(actionEvent);
    }


    public void recuperarDatos(){
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();

        lblProblematicaAcadémica.setText(problematicaAcademica.getTitulo());
        taDescripcioProbleamticaAcademica.setText(problematicaAcademica.getDescripcion());
        taDescripcioProbleamticaAcademica.setDisable(true);
        lblFechaReporte.setText(sesionDeTutoriaAcademica.getFechaConFormato());
        lblExperienciaEducativa.setText(experienciaEducativa.getNombre());
        lblProfesor.setText(profesor.getNombreCompleto());


    }


}
