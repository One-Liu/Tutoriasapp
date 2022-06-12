package uv.fei.tutorias.main;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarSolucionProblematicaAcademicaControlador implements Initializable{

    public Label problematicaAcadémica;
    public Label experienciaEducativa;
    public Label profesor;
    public TextArea insertarSolucionTextArea;

    public RegistrarSolucionProblematicaAcademicaControlador(String seleccion){
        asignarTextosLabel(seleccion);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    public void guardarBtn(MouseEvent mouseEvent) {

        SolucionAProblematicaAcademicaDAO solucionProblematica = new SolucionAProblematicaAcademicaDAO();
        String solucionProblematicaTexto = insertarSolucionTextArea.getText();
        boolean respuesta = solucionProblematica.addSolucionProblematicaAcademica(solucionProblematicaTexto);
        if (respuesta == false){
            mostrarAlerta("Error de conexión","Error: la problematica academica no se ha agregado",Alert.AlertType.ERROR);
        }else{
            mostrarAlerta("Solución guardada","La solución ha problemática académica\nfue guardada exitosamente", Alert.AlertType.INFORMATION);
        }
    }

    public void cancelarBtn(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void asignarTextosLabel(String descripcion){

        ProblematicaAcademicaDAO problematica = new ProblematicaAcademicaDAO();
        ExperienciaEducativaDAO experiencia = new ExperienciaEducativaDAO();
        ProfesorDAO profesordao = new ProfesorDAO();
        ArrayList<ProblematicaAcademica> listaProblematica = (ArrayList<ProblematicaAcademica>) problematica.obtenerProblematicaAcademicaPorDescripcion(descripcion);
        int idExperienciaEducativa =listaProblematica.get(0).getIdExperienciaEducativa();
        ExperienciaEducativa datosExperiencia = experiencia.obtenerExperienciaEducativaPorId(idExperienciaEducativa);
        Profesor nombreProfesor = profesordao.findProfesorById(datosExperiencia.getIdProfesor());

        experienciaEducativa.setText(datosExperiencia.getNombre());
        profesor.setText(nombreProfesor.getNombre());
        problematicaAcadémica.setText(listaProblematica.get(0).getDescripcion());
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
