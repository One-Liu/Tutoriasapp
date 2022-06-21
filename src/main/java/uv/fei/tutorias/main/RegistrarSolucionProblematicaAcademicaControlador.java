package uv.fei.tutorias.main;

import uv.fei.tutorias.utilidades.UtilidadVentana;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import uv.fei.tutorias.bussinesslogic.ExperienciaEducativaDAO;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.bussinesslogic.SolucionAProblematicaAcademicaDAO;
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
    public Label lblProblematicaAcadémica;
    @FXML
    public Label lblExperienciaEducativa;
    @FXML
    public Label lblProfesor;
    @FXML
    public Label lblFechaReporte;
    @FXML
    public TextArea taSolucion;

    public RegistrarSolucionProblematicaAcademicaControlador(String seleccion){
        try {
            asignarTextosLabel(seleccion);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void guardarBtn(MouseEvent mouseEvent) {

        SolucionAProblematicaAcademicaDAO solucionProblematica = new SolucionAProblematicaAcademicaDAO();
        String solucionProblematicaTexto = taSolucion.getText();
        boolean respuesta = false;
        try {
            respuesta = solucionProblematica.agregarSolucionProblematicaAcademica(solucionProblematicaTexto);
        } catch (SQLException e) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
        if (respuesta == false){
            mostrarAlerta("Error de conexión","Error: la problematica academica no se ha agregado",Alert.AlertType.ERROR);
        }else{
            mostrarAlerta("Solución guardada","La solución ha problemática académica\nfue guardada exitosamente", Alert.AlertType.INFORMATION);
        }
    }

    public void cancelarBtn(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void asignarTextosLabel(String descripcion) throws SQLException {

        ProblematicaAcademicaDAO problematica = new ProblematicaAcademicaDAO();
        ExperienciaEducativaDAO experiencia = new ExperienciaEducativaDAO();
        ProfesorDAO profesordao = new ProfesorDAO();
        ArrayList<ProblematicaAcademica> listaProblematica = (ArrayList<ProblematicaAcademica>) problematica.obtenerProblematicaAcademicaPorDescripcion(descripcion);
        int idExperienciaEducativa =listaProblematica.get(0).getIdExperienciaEducativa();
        ExperienciaEducativa datosExperiencia = experiencia.obtenerExperienciaEducativaPorId(idExperienciaEducativa);
        Profesor nombreProfesor = profesordao.findProfesorById(datosExperiencia.getIdProfesor());

        lblExperienciaEducativa.setText(datosExperiencia.getNombre());
        lblProfesor.setText(nombreProfesor.getNombre());
        lblProblematicaAcadémica.setText(listaProblematica.get(0).getDescripcion());
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
