package uv.fei.tutorias.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingnUpController implements Initializable {

    @FXML
    private PasswordField pastxtcontrasena;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidoMaterno;
    @FXML
    private TextField apellidoPaterno;
    @FXML
    private TextField correoInstitucional;
    @FXML
    private ComboBox<String> tipo;


    @FXML
    protected void onOkButton() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String value = (String) tipo.getValue();
        if (value == null){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error seleccione un tipo");
            alert.showAndWait();
        } else {
            if (!camposVacios()){
                Persona persona = new Persona( nombre.getText(),
                        apellidoPaterno.getText(), apellidoMaterno.getText());
                Usuario usuario = new Usuario(pastxtcontrasena.getText(),correoInstitucional.getText());
                PersonaDAO personaDAO = new PersonaDAO();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                switch (value) {
                    case "Jefe de carrera":
                        JefeDeCarreraDAO jefeDeCarreraDAO = new JefeDeCarreraDAO();
                        JefeDeCarrera jefeDeCarrera = new JefeDeCarrera(persona);
                        jefeDeCarrera.setIdPersona(personaDAO.agregarPersona(persona));
                        jefeDeCarrera.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
                        jefeDeCarreraDAO.agregarJefeDeCarrera(jefeDeCarrera);
                        break;
                    case "Coordinador":
                        CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
                        Coordinador coordinador = new Coordinador(persona);
                        coordinador.setIdPersona(personaDAO.agregarPersona(persona));
                        coordinador.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
                        coordinadorDAO.agregarCoordinador(coordinador);
                        break;

                    case "Tutor academico":
                        TutorAcademicoDAO tutorAcademicoDAO= new TutorAcademicoDAO();
                        TutorAcademico tutorAcademico = new TutorAcademico(persona);
                        tutorAcademico.setIdPersona(personaDAO.agregarPersona(persona));
                        tutorAcademico.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
                        tutorAcademicoDAO.agregarTutorAcademico(tutorAcademico);
                        break;

                }
            }
        }


    }

    public boolean camposVacios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean bandera = true;
        if (nombre.getText().trim().isEmpty()){
            UtilidadVentana.mostrarAlertaSinConfirmacion("Error","El campo nombre esta vacio", Alert.AlertType.ERROR);
        }else if (apellidoPaterno.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error apellido paterno vacio");
            alert.showAndWait();
        }else if (apellidoMaterno.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error apellido materno");
            alert.showAndWait();
        }else if (correoInstitucional.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error correo institucional vacio");
            alert.showAndWait();
        }else {
            bandera = false;
        }
        return bandera;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipo.getItems().setAll("Jefe de carrera",  "Coordinador", "Tutor academico");
    }
}