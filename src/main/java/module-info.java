module org.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
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
    requires java.desktop;

    // Abre el paquete principal a javafx.fxml
    opens org.example.bibliotecafx.vistas to javafx.fxml;
    opens org.example.bibliotecafx.controladores to javafx.fxml; // Si tienes controladores también

    opens org.example.bibliotecafx.entities  to javafx.base, org.hibernate.orm.core, javafx.fxml;
    // Exporta el paquete principal
    exports org.example.bibliotecafx.vistas;
    exports org.example.bibliotecafx.controladores;
}
