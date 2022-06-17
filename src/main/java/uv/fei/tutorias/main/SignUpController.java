package uv.fei.tutorias.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class SignUpController implements Initializable {
    
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private ComboBox<String> cbTipoDeUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipoDeUsuario.getItems().setAll("Tutor academico",  "Coordinador", "Jefe de carrera");
    }
    
//    protected void onOkButton() throws SQLException {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        String value = (String) tipo.getValue();
//        if (value == null){
//            alert.setHeaderText(null);
//            alert.setTitle("Error");
//            alert.setContentText("Error seleccione un tipo");
//            alert.showAndWait();
//        } else {
//            if (!camposVacios()){
//                Persona persona = new Persona( nombre.getText(),
//                        apellidoPaterno.getText(), apellidoMaterno.getText());
//                Usuario usuario = new Usuario(pastxtcontrasena.getText(),correoInstitucional.getText());
//                PersonaDAO personaDAO = new PersonaDAO();
//                UsuarioDAO usuarioDAO = new UsuarioDAO();
//                switch (value) {
//                    case "Jefe de carrera":
//                        JefeDeCarreraDAO jefeDeCarreraDAO = new JefeDeCarreraDAO();
//                        JefeDeCarrera jefeDeCarrera = new JefeDeCarrera(persona);
//                        jefeDeCarrera.setIdPersona(personaDAO.agregarPersona(persona));
//                        jefeDeCarrera.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
//                        jefeDeCarreraDAO.agregarJefeDeCarrera(jefeDeCarrera);
//                        break;
//                    case "Coordinador":
//                        CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
//                        Coordinador coordinador = new Coordinador(persona);
//                        coordinador.setIdPersona(personaDAO.agregarPersona(persona));
//                        coordinador.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
//                        coordinadorDAO.agregarCoordinador(coordinador);
//                        break;
//
//                    case "Tutor academico":
//                        TutorAcademicoDAO tutorAcademicoDAO= new TutorAcademicoDAO();
//                        TutorAcademico tutorAcademico = new TutorAcademico(persona);
//                        tutorAcademico.setIdPersona(personaDAO.agregarPersona(persona));
//                        tutorAcademico.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
//                        tutorAcademicoDAO.agregarTutorAcademico(tutorAcademico);
//                        break;
//
//                }
//            }
//        }
//
//
//    }

    public boolean camposLlenos(){
        boolean camposLlenos = true;
        if (this.tfNombre.getText().trim().isBlank()
            || this.tfApellidoPaterno.getText().trim().isBlank()
            || this.tfApellidoMaterno.getText().trim().isBlank()
            || this.tfCorreoInstitucional.getText().trim().isBlank()){
            camposLlenos = false;
        }
        return camposLlenos;
    }
    /*
    @FXML
    private void clicRegistrar() {
        if(camposLlenos()) {
            String nombre = this.tfNombre.getText();
            String apellidoPaterno = this.tfApellidoPaterno.getText();
            String apellidoMaterno = this.tfApellidoMaterno.getText();
            String correoInstitucional = this.tfCorreoInstitucional.getText();
            
            Persona persona = new Persona(nombre, apellidoPaterno, apellidoMaterno);
            Usuario usuario = new Usuario(pastxtcontrasena.getText(),correoInstitucional.getText());
            PersonaDAO personaDAO = new PersonaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            switch(cbTipoDeUsuario.getValue()) {
                case "Tutor académico":
                    TutorAcademicoDAO tutorAcademicoDAO= new TutorAcademicoDAO();
                    TutorAcademico tutorAcademico = new TutorAcademico(persona);
                    tutorAcademico.setIdPersona(personaDAO.agregarPersona(persona));
                    tutorAcademico.setIdUsuario(usuarioDAO.addUsuarioReturnId(usuario));
                    tutorAcademicoDAO.agregarTutorAcademico(tutorAcademico);
                    break;
                case "Coordinador":
                    break;
                case "Jefe de carrera":
                    break;
            }
        } else {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Campos vacíos",
                "No puede haber campos vacíos", 
                Alert.AlertType.ERROR);
        }
    }
    */
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}