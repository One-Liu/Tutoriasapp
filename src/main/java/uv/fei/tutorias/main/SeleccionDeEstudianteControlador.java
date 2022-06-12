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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.EstudianteDAO;

public class SeleccionDeEstudianteControlador implements Initializable {
    @FXML
    private ComboBox<Estudiante> cbEstudiantes;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSeleccionar;
    
    private ObservableList<Estudiante> estudiantes = FXCollections.observableArrayList();
    
    private void cargarEstudiantes() throws SQLException {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        this.estudiantes = estudianteDAO.obtenerEstudiantes();
    }
    
    private void cargarCamposGUI() {
        try {
            cargarEstudiantes();
            this.cbEstudiantes.setItems(estudiantes);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
        UtilidadVentana.cerrarVentana(event);
    }
    
    @FXML
    private void clicSeleccionar(ActionEvent event) {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUIModificacionDeAsignacionDeTutorAcademico.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeAsignacionDeTutorAcademicoControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setEstudiante(estudianteSeleccionado);
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
