/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uv.fei.tutorias.main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import uv.fei.tutorias.bussinesslogic.ProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ProblematicaAcademica;

/**
 * FXML Controller class
 *
 * @author alfre
 */
public class ProblematicasSinSolucionControlador implements Initializable {

    public ComboBox ProblematicaAcademicaComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CargarProblematicas();

    }

    public void RegistrarSolucionBtn(MouseEvent mouseEvent) {
        String seleccion = ProblematicaAcademicaComboBox.getSelectionModel().getSelectedItem().toString();

        new RegistrarSolucionProblematicaAcademicaControlador(seleccion);
    }

    public void CancelarBtn(MouseEvent mouseEvent) {
        System.exit(1);
    }

    public void CargarProblematicas(){

        ObservableList<ProblematicaAcademica> listaObservableProblematicas = FXCollections.observableArrayList();
        ProblematicaAcademicaDAO problematica =new ProblematicaAcademicaDAO();
        ArrayList<ProblematicaAcademica> listaProblematicas = (ArrayList<ProblematicaAcademica>) problematica.mostrarTodasLasProblematicasAcademicas();
        listaObservableProblematicas.addAll(listaProblematicas);
        ProblematicaAcademicaComboBox.setItems(listaObservableProblematicas);
    }
}
