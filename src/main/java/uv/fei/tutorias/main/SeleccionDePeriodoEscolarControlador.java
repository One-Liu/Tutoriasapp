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
import uv.fei.tutorias.bussinesslogic.PeriodoEscolarDAO;
import uv.fei.tutorias.domain.PeriodoEscolar;

public class SeleccionDePeriodoEscolarControlador implements Initializable {
    @FXML
    private ComboBox<PeriodoEscolar> cbPeriodosEscolares;
    
    private final PeriodoEscolarDAO periodoEscolarDAO = new PeriodoEscolarDAO();
    
    private ObservableList<PeriodoEscolar> periodosEscolares = FXCollections.observableArrayList();
    
    private void cargarPeriodosEscolares() throws SQLException {
        this.periodosEscolares = periodoEscolarDAO.obtenerPeriodosEscolares();
    }
    
    private void cargarCamposGUI() {
        try {
            cargarPeriodosEscolares();
            this.cbPeriodosEscolares.setItems(periodosEscolares);
        } catch(SQLException ex) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarCamposGUI();
    }    
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }

    @FXML
    private void clicSeleccionar(ActionEvent event) {
        PeriodoEscolar periodoEscolarSeleccionado = this.cbPeriodosEscolares.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/src/main/resources/uv/fei/tutorias/main/GUIModificacionDeFechasDeSesionDeTutoria.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeFechasDeSesionDeTutoriaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setPeriodoEscolar(periodoEscolarSeleccionado);
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
