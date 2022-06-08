package uv.fei.tutorias.main;

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
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class SeleccionDeSesionDeTutoriaControlador implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSeleccionar;
    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoria;
    
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
        this.sesionesDeTutoriaAcademica = sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademica();
    }
    
    private void inicializarCBFechasDeSesionDeTutoria() {
        this.cbFechasDeSesionDeTutoria.setItems(sesionesDeTutoriaAcademica);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarSesionesDeTutoriaAcademica();
            inicializarCBFechasDeSesionDeTutoria();
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicSeleccionar(ActionEvent event) {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoria.getSelectionModel().getSelectedItem();
        if(sesionDeTutoriaAcademicaSeleccionada == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de fecha de sesion", "Seleccione una fecha de sesión válida", Alert.AlertType.WARNING);
        } else {
            
        }
    }

}
