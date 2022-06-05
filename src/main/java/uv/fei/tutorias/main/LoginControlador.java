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
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.bussinesslogic.UsuarioDAO;
import uv.fei.tutorias.domain.TutorAcademico;
import uv.fei.tutorias.domain.Usuario;

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

    public void onRegistrarseBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        URL url = Paths.get("src\\main\\resources\\uv.fei.tutorias.main\\GUISignUp.fxml").toUri().toURL();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Scene scene = new Scene(fxmlLoader.load(url ), 600, 400);
        stage.setTitle("Sign-up");
        stage.setScene(scene);
        stage.show();
        //cerramos la ventana de login
        Stage myStage = (Stage) this.btnRegistrar.getScene().getWindow();
        myStage.close();
    }

    public void onIngresarBtn(ActionEvent actionEvent) throws IOException {
        if (!camposVacios()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Usuario usuario = new Usuario(txtContrasena.getText(),txtcorreoInstitucional.getText());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuario = usuarioDAO.findUsuarioReturnId(usuario);
            int idUsuario = usuario.getIdUsuario();
            if (idUsuario == 0){
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("no se ha encontrado al usuario");
                alert.showAndWait();
            }else if (usuarioDAO.estaIdUsuarioEnTutorAcademico(usuario.getIdUsuario())){
                TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
                try {
                    TutorAcademico tutorAcademico =  tutorAcademicoDAO.buscarTutorAcademicoPorElIdDeUsuario(idUsuario);
//                    Utilidad.pasarValoresEntreVentanas(tutorAcademico,"src\\main\\resources\\uv.fei.tutorias.main\\GUIMenuPrincipalDeTutorAcademico.fxml", "Menu principal",actionEvent);
                }catch (SQLException e){
                    Utilidad.mensajeErrorAlCargarLaInformacionDeLaVentana();
                }

            }else if (usuarioDAO.estaIdUsarionEnJefeDeCarrera(usuario.getIdUsuario())){
                alert.setHeaderText(null);
                alert.setTitle("Menu principal de jefe de carrera");
                alert.setContentText("Este seria el menu principal de jefe de carreraa");
                alert.showAndWait();
            }else if (usuarioDAO.estaIdUsuarionEnCoordinador(usuario.getIdUsuario())){
                alert.setHeaderText(null);
                alert.setTitle("Menu principal de Coordinador");
                alert.setContentText("Este seria el menu principal de coordinador");
                alert.showAndWait();
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
