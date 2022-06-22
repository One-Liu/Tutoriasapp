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
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.CoordinadorDAO;
import uv.fei.tutorias.bussinesslogic.JefeDeCarreraDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Coordinador;
import uv.fei.tutorias.domain.JefeDeCarrera;
import uv.fei.tutorias.domain.TutorAcademico;
import uv.fei.tutorias.domain.Usuario;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import uv.fei.tutorias.utilidades.UtilidadVentana;

public class SeleccionDeTipoDeUsuarioControlador implements Initializable {

    @FXML
    private ComboBox<String> cbTipoUsuarios;

    @Setter
    private Usuario usuario = new Usuario();
    @Setter
    private ObservableList<String> tiposDeUsuario = FXCollections.observableArrayList();
    
    public void cargarCamposGUI() {
        this.cbTipoUsuarios.setItems(tiposDeUsuario);
        this.cbTipoUsuarios.getSelectionModel().selectFirst();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void clicIngresar(ActionEvent evento) {
        try {
            switch(cbTipoUsuarios.getSelectionModel().getSelectedItem()) {
                case "Tutor académico" -> {
                    TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
                    TutorAcademico tutorAcademico = tutorAcademicoDAO.buscarTutorAcademicoPorElIdDeUsuario(this.usuario.getIdUsuario());
                    DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setTutorAcademico(tutorAcademico);

                    FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIMenuPrincipalDeTutorAcademico.fxml"));
                    Parent raiz = cargadorFXML.load();
                    Scene escena = new Scene(raiz);
                    Stage escenario = new Stage();
                    escenario.setResizable(false);
                    escenario.setScene(escena);
                    escenario.setTitle("Menú principal");
                    escenario.initModality(Modality.APPLICATION_MODAL);
                    escenario.show();
                    UtilidadVentana.cerrarVentana(evento);
                }
                case "Coordinador" -> {
                    CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
                    Coordinador coordinador = coordinadorDAO.obtenerCoordinadorPorIdUsuario(this.usuario.getIdUsuario());
                    DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setCoordinador(coordinador);

                    FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIMenuPrincipalCoordinadorDeTutorias.fxml"));
                    Parent raiz = cargadorFXML.load();
                    Scene escena = new Scene(raiz);
                    Stage escenario = new Stage();
                    escenario.setResizable(false);
                    escenario.setScene(escena);
                    escenario.setTitle("Menú principal");
                    escenario.initModality(Modality.APPLICATION_MODAL);
                    escenario.show();
                    UtilidadVentana.cerrarVentana(evento);
                }
                case "Jefe de carrera" -> {
                    JefeDeCarreraDAO jefeDeCarreraDAO = new JefeDeCarreraDAO();
                    JefeDeCarrera jefeDeCarrera = jefeDeCarreraDAO.obtenerJefeDeCarreraPorIdUsuario(this.usuario.getIdUsuario());
                    DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setJefeDeCarrera(jefeDeCarrera);

                    FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUIMenuPrincipalJefeDeCarrera.fxml"));
                    Parent raiz = cargadorFXML.load();
                    Scene escena = new Scene(raiz);
                    Stage escenario = new Stage();
                    escenario.setResizable(false);
                    escenario.setScene(escena);
                    escenario.setTitle("Menú principal");
                    escenario.initModality(Modality.APPLICATION_MODAL);
                    escenario.show();
                    UtilidadVentana.cerrarVentana(evento);
                }
            }
        } catch(IOException excepcionIO) {
            UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        }
    }
}
