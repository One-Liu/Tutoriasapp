module test.java.uv.fei.tutorias {
    requires junit;
    requires uv.fei.tutorias;
    requires org.hamcrest;
    requires java.sql;
    requires javafx.base;


    exports uv.fei.tutorias to junit;
}