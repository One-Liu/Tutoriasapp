package uv.fei.tutorias.utilidades;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import uv.fei.tutorias.domain.Estudiante;

public class TablaEstudiante_Horario {

    private Estudiante estudiante;
    private ObservableList<String> horas = FXCollections.observableArrayList(
        "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", 
        "15", "16", "17", "18", "19", "20", "21", "22", "23");
    private ObservableList<String> minutos = FXCollections.observableArrayList(
        "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", 
        "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", 
        "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", 
        "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");
    private Spinner<String> hora;
    private Spinner<String> minuto;

    public TablaEstudiante_Horario() {
        this.estudiante = new Estudiante();

        this.hora = new Spinner<>();
        SpinnerValueFactory<String> valoresDeSpinnerHora = new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.unmodifiableObservableList(horas));
        valoresDeSpinnerHora.setValue("00");
        this.hora.setValueFactory(valoresDeSpinnerHora);
        this.hora.setEditable(true);

        this.minuto = new Spinner<>();
        SpinnerValueFactory<String> valoresDeSpinnerMinuto = new SpinnerValueFactory.ListSpinnerValueFactory<>(FXCollections.unmodifiableObservableList(minutos));
        valoresDeSpinnerMinuto.setValue("00");
        this.minuto.setValueFactory(valoresDeSpinnerMinuto);
        this.minuto.setEditable(true);
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public String getNombre() {
        return this.estudiante.getNombreCompleto();
    }

    public Spinner<String> getHora() {
        return hora;
    }

    public Spinner<String> getMinuto() {
        return minuto;
    }

    @Override
    public String toString() {
        return "TablaEstudiante_Horario{" + "estudiante=" + estudiante.getNombreCompleto() + ", horas=" + horas + ", minutos=" + minutos + ", hora=" + hora + ", minuto=" + minuto + '}';
    }
    
    
}
