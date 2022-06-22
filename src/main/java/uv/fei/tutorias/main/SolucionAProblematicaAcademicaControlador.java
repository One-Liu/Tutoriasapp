package uv.fei.tutorias.main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import lombok.Setter;
import uv.fei.tutorias.bussinesslogic.SolucionAProblematicaAcademicaDAO;
import uv.fei.tutorias.domain.ExperienciaEducativa;
import uv.fei.tutorias.domain.ProblematicaAcademica;
import uv.fei.tutorias.domain.Profesor;
import uv.fei.tutorias.domain.SesionDeTutoriaAcademica;
import uv.fei.tutorias.domain.SolucionAProblematicaAcademica;
import uv.fei.tutorias.utilidades.UtilidadVentana;

public class SolucionAProblematicaAcademicaControlador implements Initializable {

    @FXML
    private Label lblProblematicaAcademica;
    @FXML
    private Label lblFechaDeReporte;
    @FXML
    private Label lblExperienciaEducativa;
    @FXML
    private Label lblProfesor;
    @FXML
    private TextArea taProblematicaAcademica;
    @FXML
    private TextArea taSolucion;
    
    private SolucionAProblematicaAcademica solucion;
    
    @Setter
    private ProblematicaAcademica problematicaAcademica = new ProblematicaAcademica();
    @Setter
    private SesionDeTutoriaAcademica sesionDeTutoriaAcademica = new SesionDeTutoriaAcademica();
    @Setter
    private ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
    @Setter
    private Profesor profesor = new Profesor();
    
    public void cargarDatos() throws SQLException {
        SolucionAProblematicaAcademicaDAO solucionAProblematicaAcademicaDAO = new SolucionAProblematicaAcademicaDAO();
        this.solucion = solucionAProblematicaAcademicaDAO.buscarSolucionAProblematicaAcademicaById(problematicaAcademica.getIdSolucionProblematicaAcademica());
    }
    
    public void cargarCamposGUI() {
        this.lblProblematicaAcademica.setText(this.problematicaAcademica.getTitulo());
        this.lblFechaDeReporte.setText(this.sesionDeTutoriaAcademica.getFechaConFormato());
        this.lblExperienciaEducativa.setText(this.experienciaEducativa.getNombre());
        this.lblProfesor.setText(this.profesor.getNombreCompleto());
        this.taProblematicaAcademica.setText(this.problematicaAcademica.getDescripcion());
        this.taSolucion.setText(this.solucion.getDescripcion());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void clicDescargar(ActionEvent evento) {
        
    }
    
    @FXML
    private void clicImprimir(ActionEvent evento) {
        
    }
    
    @FXML
    private void clicCancelar(ActionEvent evento) {
        UtilidadVentana.cerrarVentana(evento);
    }
}
