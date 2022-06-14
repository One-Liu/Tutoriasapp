package uv.fei.tutorias.main;

import java.io.IOException;
import uv.fei.tutorias.domain.Estudiante;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;

public class SeleccionDeEstudianteControlador implements Initializable {
    @FXML
    private ComboBox<Estudiante> cbEstudiantes;
    
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    
    private void cargarDatos() {
        try {
            EstudianteDAO estudianteDAO = new EstudianteDAO();
            this.estudiantes.addAll(estudianteDAO.obtenerEstudiantesConTutorAsignado());
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
    }
    
    private void cargarCamposGUI() {
        this.cbEstudiantes.setItems(estudiantes);
        this.cbEstudiantes.getSelectionModel().selectFirst();
        this.cbEstudiantes.setConverter(new StringConverter<Estudiante>() {
            @Override
            public String toString(Estudiante estudiante) {
                return "(" + estudiante.getMatricula() + ") " + estudiante.getNombreCompleto();
            }

            @Override
            public Estudiante fromString(String string) {
                throw new UnsupportedOperationException("Método no soportado");
            }
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cargarCamposGUI();
    }    
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
    
    @FXML
    private void clicSeleccionar(ActionEvent evento) {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        System.out.println(estudianteSeleccionado);
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIModificacionDeAsignacionDeTutorAcademico.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeAsignacionDeTutorAcademicoControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setEstudiante(estudianteSeleccionado);
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Modificación de asignación de tutor");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }
}
