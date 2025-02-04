module org.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;

    // Abre el paquete principal a javafx.fxml
    opens org.example.bibliotecafx.vistas to javafx.fxml;
    opens org.example.bibliotecafx.controladores to javafx.fxml; // Si tienes controladores también

    // Exporta el paquete principal
    exports org.example.bibliotecafx.vistas;
    exports org.example.bibliotecafx.controladores;
}
