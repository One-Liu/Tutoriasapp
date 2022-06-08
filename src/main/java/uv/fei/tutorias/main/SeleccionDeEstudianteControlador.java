package uv.fei.tutorias.main;

import uv.fei.tutorias.domain.Estudiante;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    
    private void inicializarCBEstudiantes() {
        this.cbEstudiantes.setItems(estudiantes);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarEstudiantes();
            inicializarCBEstudiantes();
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }    
    
    @FXML
    private void clicCancelar() {
        
    }
    
    @FXML
    private void clicSeleccionar() {
        Estudiante estudianteSeleccionado = this.cbEstudiantes.getSelectionModel().getSelectedItem();
        if(estudianteSeleccionado == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de estudiante", "Seleccione un estudiante válido", Alert.AlertType.WARNING);
        } else {
            
        }
    }
}
