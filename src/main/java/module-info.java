module uv.fei.tutorias.tutoriasapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens uv.fei.tutorias.tutoriasapp to javafx.fxml;
    exports uv.fei.tutorias.tutoriasapp;
}