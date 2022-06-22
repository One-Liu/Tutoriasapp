package uv.fei.tutorias.main;

import uv.fei.tutorias.utilidades.UtilidadVentana;
import uv.fei.tutorias.utilidades.DatosGlobalesDeSesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.CoordinadorDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import uv.fei.tutorias.domain.*;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import uv.fei.tutorias.bussinesslogic.JefeDeCarreraDAO;

public class LoginControlador {

    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private PasswordField pfContrasena;

    private Usuario usuario = new Usuario();
    private ObservableList<String> tiposDeUsuario = FXCollections.observableArrayList();

    public boolean validarCamposLlenos() {
        boolean camposLlenos = true;
        if(tfCorreoInstitucional.getText().trim().isEmpty()
            || pfContrasena.getText().trim().isEmpty()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Campos vacíos",
                "No puede haber campos vacíos",
                Alert.AlertType.WARNING);
            camposLlenos = false;
        }
        return camposLlenos;
    }

    private void iniciarSesion(ActionEvent evento) throws SQLException, IOException {
        if(this.tiposDeUsuario.size() > 1) {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("GUISeleccionDeTipoDeUsuario.fxml"));
            Parent raiz = cargadorFXML.load();
            SeleccionDeTipoDeUsuarioControlador controladorGUI = cargadorFXML.getController();
            controladorGUI.setUsuario(this.usuario);
            controladorGUI.setTiposDeUsuario(tiposDeUsuario);
            controladorGUI.cargarCamposGUI();
            Scene escena = new Scene(raiz);
            Stage escenario = new Stage();
            escenario.setResizable(false);
            escenario.setScene(escena);
            escenario.setTitle("Seleccion de tipo de usuario");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.show();
            UtilidadVentana.cerrarVentana(evento);

        } else {
            switch(this.tiposDeUsuario.get(0)) {
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
        }
    }
    
    private void validarTipoDeUsuario(ActionEvent evento) throws SQLException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if(usuarioDAO.estaIdUsuarioEnTutorAcademico(this.usuario.getIdUsuario())) {
            this.tiposDeUsuario.add("Tutor académico");
        } 
        if(usuarioDAO.estaIdUsuarionEnCoordinador(this.usuario.getIdUsuario())) {
            this.tiposDeUsuario.add("Coordinador");
        } 
        if(usuarioDAO.estaIdUsuarioEnJefeDeCarrera(this.usuario.getIdUsuario())) {
            this.tiposDeUsuario.add("Jefe de carrera");
        }
        
        iniciarSesion(evento);
    }

    @FXML
    private void clicIngresar(ActionEvent evento) {
        if(validarCamposLlenos()) {
            this.usuario = new Usuario(this.pfContrasena.getText(), this.tfCorreoInstitucional.getText());
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            try {
                this.usuario = usuarioDAO.buscarUsuarioPorCorreoYContrasena(this.usuario);
                if(this.usuario.getIdUsuario() == 0) {
                    UtilidadVentana.mostrarAlertaSinConfirmacion(
                        "Usuario inválido",
                        "No se ha encontrado un usuario con los datos ingresados",
                        Alert.AlertType.WARNING);
                } else {
                    validarTipoDeUsuario(evento);
                }
            } catch(SQLException ex) {
                UtilidadVentana.mensajePerdidaDeConexion();
            } catch(IOException excepcionIO) {
                UtilidadVentana.mensajeErrorAlCargarLaInformacionDeLaVentana();
            }
        }
    }
}
