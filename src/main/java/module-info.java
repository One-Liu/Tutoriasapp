module uv.fei.tutorias.tutoriasapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.testng;


    opens main to javafx.fxml;
    exports main;
}