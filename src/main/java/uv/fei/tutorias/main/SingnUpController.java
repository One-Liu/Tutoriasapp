package uv.fei.tutorias.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.fei.tutorias.bussinesslogic.ProfesorDAO;
import uv.fei.tutorias.bussinesslogic.TutorAcademicoDAO;
import uv.fei.tutorias.domain.Persona;

import java.net.URL;
import java.util.ResourceBundle;

public class SingnUpController implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidoMaterno;
    @FXML
    private TextField apellidoPaterno;
    @FXML
    private TextField correoInstitucional;
    @FXML
    private TextField correoPersonal;
    @FXML
    private ComboBox<String> tipo;


    @FXML
    protected void onOkButton() {
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
                        apellidoPaterno.getText(), apellidoMaterno.getText(),
                        correoInstitucional.getText() ,correoPersonal.getText());
                switch (value) {
                    case "Estudiante":
                        System.out.println("Se selecciono al estudiante");;
                        break;
                    case "Coordinador":
                        System.out.println("Se selecciona al Coordinador");
                        break;
                    case "Tutor academico":
                        TutorAcademicoDAO tutorAcademicoDAO= new TutorAcademicoDAO();
                        tutorAcademicoDAO.addTutorAcademico(persona);
                        break;
                    case "Profesor":
                        ProfesorDAO profesorDAO = new ProfesorDAO();
                        profesorDAO.addProfesor(persona);
                        break;

                }
            }
        }


    }

    public boolean camposVacios(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean bandera = true;
        if (nombre.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error nombre vacio");
            alert.showAndWait();
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
        }else if (correoPersonal.getText().trim().isEmpty()){
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error correo personal");
            alert.showAndWait();
        }else {
            bandera = false;
        }
        return bandera;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipo.getItems().setAll("Estudiante", "Coordinador", "Tutor academico", "Profesor");


    }
}