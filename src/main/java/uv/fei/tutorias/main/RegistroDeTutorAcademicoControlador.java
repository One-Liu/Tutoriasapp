package uv.fei.tutorias.main;

import uv.fei.tutorias.utilidades.UtilidadVentana;
import uv.fei.tutorias.utilidades.GeneradorDeContrasena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import uv.fei.tutorias.bussinesslogic.*;
import uv.fei.tutorias.domain.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.util.StringConverter;

public class RegistroDeTutorAcademicoControlador implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreoInstitucional;
    @FXML
    private ComboBox<ProgramaEducativo> cbProgramasEducativos;

    private ObservableList<ProgramaEducativo> programasEducativos = FXCollections.observableArrayList();

    public void cargarDatos() throws SQLException {
        ProgramaEducativoDAO programaEducativoDAO = new ProgramaEducativoDAO();
        this.programasEducativos.addAll(programaEducativoDAO.obtenerProgramasEducativos());
    }

    public void cargarCamposGUI() {
        if(this.programasEducativos.isEmpty()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Programas educativos", 
                "No hay programas educativos registrados", 
                Alert.AlertType.ERROR);
            UtilidadVentana.cerrarVentana(new ActionEvent());
        } else {
            cbProgramasEducativos.setItems(programasEducativos);
            cbProgramasEducativos.getSelectionModel().selectFirst();
            cbProgramasEducativos.setConverter(new StringConverter<ProgramaEducativo>() {
                @Override
                public String toString(ProgramaEducativo programaEducativo) {
                    return programaEducativo == null ? null : programaEducativo.getNombre();
                }

                @Override
                public ProgramaEducativo fromString(String string) {
                    throw new UnsupportedOperationException("Operación no soportada");
                }
            });
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private boolean validarCamposLlenos() {
        boolean camposLlenos = true;

        if(this.tfNombre.getText().trim().isBlank()
            || this.tfApellidoPaterno.getText().trim().isBlank()
            || this.tfApellidoMaterno.getText().trim().isBlank()
            || this.tfCorreoInstitucional.getText().trim().isBlank()) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Campos vacíos",
                "No puede haber campos vacíos",
                Alert.AlertType.WARNING);
            camposLlenos = false;
        }

        return camposLlenos;
    }

    private boolean validarCorreoInstitucional() {
        boolean correoInstitucionalValido = true;
        String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", "").trim();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if(!correoInstitucional.endsWith("@uv.mx")) {
            UtilidadVentana.mostrarAlertaSinConfirmacion(
                "Correo institucional", 
                "El correo ingresado no es un correo válido", 
                Alert.AlertType.WARNING);
            correoInstitucionalValido = false;
            
        } else {
            try {
                if(usuarioDAO.validarUsuarioRegistrado(correoInstitucional)) {
                    UtilidadVentana.mostrarAlertaSinConfirmacion(
                        "Correo institucional", 
                        "Ya se ha registrado un usuario con el correo institucional ingresado", 
                        Alert.AlertType.WARNING);
                    correoInstitucionalValido = false;
                }
            } catch(SQLException excepcionSQL) {
                UtilidadVentana.mensajePerdidaDeConexion();
                UtilidadVentana.cerrarVentana(new ActionEvent());
            }
        }
        
        return correoInstitucionalValido;
    }
    
    private void limpiarCamposGUI() {
        this.tfNombre.clear();
        this.tfApellidoPaterno.clear();
        this.tfApellidoMaterno.clear();
        this.tfCorreoInstitucional.clear();
        this.cbProgramasEducativos.getSelectionModel().selectFirst();
    }
    
    private boolean validarSiCorreoYaFueRegistrado() {
        boolean correoRegistradoAnteriormente = false;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", "").trim();
        
        try {
            if(usuarioDAO.validarUsuarioRegistrado(correoInstitucional)) {
                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Usuario ya registrado", 
                    "El usuario ya ha sido registrado", 
                    Alert.AlertType.ERROR);
                correoRegistradoAnteriormente = true;
            }
        } catch(SQLException excepcionSQL) {
            UtilidadVentana.mensajePerdidaDeConexion();
            UtilidadVentana.cerrarVentana(new ActionEvent());
        }
        
        return correoRegistradoAnteriormente;
    }
    
    @FXML
    private void clicRegistrar() {
        if(validarCamposLlenos() && validarCorreoInstitucional() && !validarSiCorreoYaFueRegistrado()) {
            String nombre = this.tfNombre.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String apellidoPaterno = this.tfApellidoPaterno.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String apellidoMaterno = this.tfApellidoMaterno.getText().toUpperCase().replaceAll("\\s+", " ").trim();
            String correoInstitucional = this.tfCorreoInstitucional.getText().replaceAll("\\s+", "").trim();
            ProgramaEducativo programaEducativo = this.cbProgramasEducativos.getSelectionModel().getSelectedItem();

            Persona persona = new Persona(nombre, apellidoPaterno, apellidoMaterno, programaEducativo.getId());
            Usuario usuario = new Usuario(GeneradorDeContrasena.getContrasena(), correoInstitucional);

            PersonaDAO personaDAO = new PersonaDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            try {
                TutorAcademicoDAO tutorAcademicoDAO = new TutorAcademicoDAO();
                TutorAcademico tutorAcademico = new TutorAcademico(persona);

                tutorAcademico.setIdPersona(personaDAO.agregarPersona(persona));
                tutorAcademico.setIdUsuario(usuarioDAO.agregarUsuario(usuario));
                tutorAcademicoDAO.agregarTutorAcademico(tutorAcademico);

                UtilidadVentana.mostrarAlertaSinConfirmacion(
                    "Tutor académico registrado",
                    "La contraseña del tutor académico es " + usuario.getContrasena() + ".\n"
                    + "Por favor, solicite al usuario cambiarla al ingresar al sistema.",
                    Alert.AlertType.INFORMATION);
                
                limpiarCamposGUI();
            } catch(SQLException excepcionSQL) {
                UtilidadVentana.mensajePerdidaDeConexion();
            }
        }
    }

    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
