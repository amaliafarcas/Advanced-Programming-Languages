module GUI {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ass7toyinterpreter to javafx.fxml;
    exports com.example.ass7toyinterpreter;

    opens GUI to javafx.fxml;
    exports GUI;

    opens Exception to javafx.fxml;
    exports Exception;

    opens Model to javafx.fxml;
    exports Model;

    opens Model.Statement to javafx.fxml;
    exports Model.Statement;
}

/*module com.example.ass7toyinterpreter {
        requires javafx.controls;
        requires javafx.fxml;

        requires org.controlsfx.controls;
        requires org.kordamp.bootstrapfx.core;

        opens com.example.ass7toyinterpreter to javafx.fxml;
        exports com.example.ass7toyinterpreter;
        }*/
