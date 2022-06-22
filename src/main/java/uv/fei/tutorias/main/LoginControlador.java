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
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;

public class LoginControlador {
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private PasswordField pfContrasena;
    
    private Usuario usuario = new Usuario();
    
    public boolean camposVacios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean bandera = true;
        if (tfCorreoInstitucional.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error correo institucional vacio");
            alert.showAndWait();
        }else if (pfContrasena.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error contraseña vacia");
            alert.showAndWait();
        }else {
            bandera = false;
        }
        return bandera;
    }
    
    private void validarTipoDeUsuario(ActionEvent evento) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {
            if (usuarioDAO.estaIdUsuarioEnTutorAcademico(this.usuario.getIdUsuario())) {
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

            } else if (usuarioDAO.estaIdUsuarionEnCoordinador(this.usuario.getIdUsuario())) {
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
        } catch (SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
        } catch (IOException excepcionIO) {
            excepcionIO.printStackTrace();
        }
    }
    
    @FXML
    private void clicIngresar(ActionEvent evento) {
        if(!camposVacios()) {
            this.usuario = new Usuario(this.pfContrasena.getText(),this.tfCorreoInstitucional.getText());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            try {
                this.usuario = usuarioDAO.buscarUsuarioPorCorreoYContrasena(this.usuario);
            } catch(SQLException ex) {
                UtilidadVentana.mensajePerdidaDeConexion();
            } finally {
                if (this.usuario.getIdUsuario() == 0) {
                    UtilidadVentana.mostrarAlertaSinConfirmacion("Usuario inválido", "No se ha encontrado un usuario con los datos ingresados", Alert.AlertType.WARNING);
                } else {
                    validarTipoDeUsuario(evento);
                }
            }
        }
    }
}
