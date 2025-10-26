module br.unicentro.criadorpers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens br.unicentro.criadorpers to javafx.fxml;
    exports br.unicentro.criadorpers;
}