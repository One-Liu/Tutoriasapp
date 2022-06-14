package uv.fei.tutorias.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uv.fei.tutorias.bussinesslogic.CoordinadorDAO;
import uv.fei.tutorias.bussinesslogic.JefeDeCarreraDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import uv.fei.tutorias.domain.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;

public class LoginControlador {
    @FXML
    private TextField txtcorreoInstitucional;
    @FXML
    private TextField txtContrasena;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnRegistrar;


    public void onIngresarBtn(ActionEvent actionEvent) throws IOException {
        if (!camposVacios()){
            Usuario usuario = new Usuario(txtContrasena.getText(),txtcorreoInstitucional.getText());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try {
                usuario = usuarioDAO.buscarUsuarioPorCorreoYContrasena(usuario);
            }catch (SQLException ex){
                UtilidadVentana.mensajePerdidaDeConexion();
            }
            int idUsuario = usuario.getIdUsuario();
            if (idUsuario == 0){
                UtilidadVentana.mostrarAlertaSinConfirmacion("No existes","No se han encontrado usuarios con los valores que ingresaste", Alert.AlertType.WARNING);
            }else {
                try {
                    if (usuarioDAO.estaIdUsuarioEnTutorAcademico(usuario.getIdUsuario())){
                        TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
                        TutorAcademico tutorAcademico =  tutorAcademicoDAO.buscarTutorAcademicoPorElIdDeUsuario(idUsuario);
                        DatosGlobalesDeSesion.getDatosGlobalesDeSesion().setTutorAcademico(tutorAcademico);
                    }else if (usuarioDAO.estaIdUsuarionEnCoordinador(usuario.getIdUsuario())){
                        CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
//                        Coordinador coordinador = coordinadorDAO.obtenerCoordinadorPorIdUsuario(usuario.getIdUsuario());

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public boolean camposVacios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean bandera = true;
        if (txtcorreoInstitucional.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error correo institucional vacio");
            alert.showAndWait();
        }else if (txtContrasena.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error contraseña vacia");
            alert.showAndWait();
        }else {
            bandera = false;
        }
        return bandera;
    }

}
