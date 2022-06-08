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
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;

public class SeleccionDePeriodoEscolarControlador implements Initializable {
    
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSeleccionar;
    @FXML
    private ComboBox<PeriodoEscolar> cbPeriodosEscolares;
    
    private ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
    
    private void cargarPeriodosEscolares() throws SQLException {
        PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
        this.periodosEscolares = periodoEscolarDAO.obtenerPeriodosEscolares();
    }
    
    private void inicializarCBPeriodosEscolares() {
        this.cbPeriodosEscolares.setItems(periodosEscolares);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargarPeriodosEscolares();
            inicializarCBPeriodosEscolares();
        } catch(SQLException ex) {
            Utilidad.mensajePerdidaDeConexion();
        }
    }    
    
    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicSeleccionar(ActionEvent event) {
        PeriodoEscolar periodoEscolarSeleccionado = this.cbPeriodosEscolares.getSelectionModel().getSelectedItem();
        if(periodoEscolarSeleccionado == null) {
            Utilidad.mostrarAlertaSinConfirmacion("Seleccion de periodo escolar", "Seleccione un periodo escolar válido", Alert.AlertType.WARNING);
        } else {
            
        }
    }

}
