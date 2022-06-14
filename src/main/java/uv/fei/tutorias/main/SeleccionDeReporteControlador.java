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
import uv.fei.tutorias.bussinesslogic.SesionDeTutoriaAcademicaDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.TutorAcademico;

public class SeleccionDeReporteControlador implements Initializable {
    @FXML
    private ComboBox<TutorAcademico> cbTutoresAcademicos;
    @FXML
    private ComboBox<SesionDeTutoriaAcademica> cbFechasDeSesionDeTutoriaAcademica;
    
    private final TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
    private final SesionDeTutoriaAcademicaDAO sesionDeTutoriaAcademicaDAO = new SesionDeTutoriaAcademicaDAO();
    
    private ObservableList<TutorAcademico> tutoresAcademicos = FXCollections.observableArrayList();
    private ObservableList<SesionDeTutoriaAcademica> sesionesDeTutoriaAcademica = FXCollections.observableArrayList();
    
    private void cargarTutoresAcademicos() throws SQLException {
        this.tutoresAcademicos.addAll(tutorAcademicoDAO.obtenerTutoresAcademicos());
    }
    
    private void cargarSesionesDeTutoriaAcademica() throws SQLException {
        this.sesionesDeTutoriaAcademica.addAll(sesionDeTutoriaAcademicaDAO.obtenerSesionesDeTutoriaAcademica());
    }
    
    private void cargarCamposGUI() {
        try {
            cargarTutoresAcademicos();
            cargarSesionesDeTutoriaAcademica();
            this.cbTutoresAcademicos.setItems(tutoresAcademicos);
            this.cbTutoresAcademicos.getSelectionModel().selectFirst();
            this.cbTutoresAcademicos.setConverter(new StringConverter<TutorAcademico>() {
                @Override
                public String toString(TutorAcademico tutorAcademico) {
                    return tutorAcademico == null ? null : "(" + tutorAcademico.getIdTutorAcademico() + ") " + tutorAcademico.getNombreCompleto();
                }

                @Override
                public TutorAcademico fromString(String string) {
                    throw new UnsupportedOperationException("Método no soportado");
                }
            });
            this.cbFechasDeSesionDeTutoriaAcademica.setItems(sesionesDeTutoriaAcademica);
            this.cbFechasDeSesionDeTutoriaAcademica.getSelectionModel().selectFirst();
            this.cbFechasDeSesionDeTutoriaAcademica.setConverter(new StringConverter<SesionDeTutoriaAcademica>() {
                @Override
                public String toString(SesionDeTutoriaAcademica sesionDeTutoriaAcademica) {
                    return sesionDeTutoriaAcademica == null ? null : sesionDeTutoriaAcademica.getFechaConFormato();
                }

                @Override
                public SesionDeTutoriaAcademica fromString(String string) {
                    throw new UnsupportedOperationException("Método no soportado");
                }
            });
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
    private void clicSeleccionar(ActionEvent evento) {
        TutorAcademico tutorAcademicoSeleccionado = this.cbTutoresAcademicos.getSelectionModel().getSelectedItem();
        SesionDeTutoriaAcademica sesionDeTutoriaAcademicaSeleccionada = this.cbFechasDeSesionDeTutoriaAcademica.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(".fxml"));
            Parent raiz = cargadorFXML.load();
            ReporteDeTutoriaAcademicaControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setTutorAcademico(tutorAcademicoSeleccionado);
            controladorGUI.setSesionDeTutoriaAcademica(sesionDeTutoriaAcademicaSeleccionada);
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Reporte de tutoría académica");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch(IOException ioException) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        }
    }

}
