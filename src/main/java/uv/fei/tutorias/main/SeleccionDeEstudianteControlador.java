package uv.fei.tutorias.main;

import uv.fei.tutorias.domain.Estudiante;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    private void cerrarGUI() {
        Stage escenarioPrincipal = (Stage) this.btnCancelar.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
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
        cerrarGUI();
    }
    
    @FXML
    private void clicSeleccionar(ActionEvent event) {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        if(estudianteSeleccionado == null) {
            UtilidadVentana.mostrarAlertaSinConfirmacion("Seleccion de estudiante", "Seleccione un estudiante válido", Alert.AlertType.WARNING);
        } else {
            
        }
    }
}
