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
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;

public class SeleccionDeSesionDeTutoriaControlador implements Initializable {
    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoria;
    
    private final SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    
    @Setter
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    public void cargarCamposGUI() {
        this.cbFechasDeSesionDeTutoria.setItems(sesionesDeTutoriaAcademica);
        this.cbFechasDeSesionDeTutoria.getSelectionModel().selectFirst();
        this.cbFechasDeSesionDeTutoria.setConverter(new StringConverter<SesionDeTutoriaAcademica>() {
            @Override
            public String toString(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
                return sesionDeTutoriaAcademica == null ? null : sesionDeTutoriaAcademica.getFechaConFormato();
            }

            @Override
            public SesionDeTutoriaAcademica fromString(String string) {
                throw new UnsupportedOperationException("Operación no soportada");
            }
        });
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
    private void clicSeleccionar(ActionEvent evento) {
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoria.getSelectionModel().getSelectedItem();
        
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIModificacionDeFechaDeEntregaDeReporte.fxml"));
            Parent raiz = cargadorFXML.load();
            ModificacionDeFechaDeEntregaDeReporteControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaSeleccionada);
            controladorGUI.cargarDatos();
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Modificación de fecha de entrega de reporte");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
            UtilidadVentana.cerrarVentana(evento);
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

}
