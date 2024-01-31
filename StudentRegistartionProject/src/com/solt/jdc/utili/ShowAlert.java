package com.solt.jdc.utili;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ShowAlert {
	public static void showAlert(String message,AlertType type) {
		Alert alert = new Alert(type, message);
		alert.showAndWait();
	}
}
