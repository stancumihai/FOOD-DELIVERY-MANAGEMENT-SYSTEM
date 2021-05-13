module org.stancu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itextpdf;
    requires org.jetbrains.annotations;
    requires java.desktop;

    opens org.stancu to javafx.fxml;
    exports org.stancu;
    exports org.stancu.model;
    exports org.stancu.service;
    exports org.stancu.service.user;
    exports org.stancu.dao;
    exports org.stancu.util;
    exports org.stancu.model.userModel;
    exports org.stancu.dao.user;
    exports org.stancu.presentation.controller.user;
    opens org.stancu.presentation.controller.user to javafx.fxml;
    exports org.stancu.presentation.controller.login;
    opens org.stancu.presentation.controller.login to javafx.fxml;
}