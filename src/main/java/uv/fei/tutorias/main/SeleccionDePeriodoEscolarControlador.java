package uv.fei.tutorias.main;

import java.io.IOException;
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
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;

public class SeleccionDePeriodoEscolarControlador implements Initializable {
    @FXML
    private ComboBox<PeriodoEscolar> cbPeriodosEscolares;
    
    private final PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
    
    private ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
    
    private void cargarDatos() {
        try {
            this.periodosEscolares.addAll(periodoEscolarDAO.obtenerPeriodosEscolares());
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    private void cargarCamposGUI() {
        RegistrarFechasDeCierreParaLaEntregaDeReporteControlador.cargarCamposGui(this.cbPeriodosEscolares, periodosEscolares);
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
        PeriodoEscolar periodoEscolarSeleccionado = this.cbPeriodosEscolares.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIModificacionDeFechasDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeFechasDeSesionDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setPeriodoEscolar(periodoEscolarSeleccionado);
            controladorGUI.cargarDatos();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Modificación de fechas de sesión de tutoría");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

}
