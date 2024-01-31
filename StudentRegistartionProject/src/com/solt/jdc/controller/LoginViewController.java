package com.solt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Member;
import com.solt.jdc.service.MemberService;
import com.solt.jdc.utili.Security;
import com.solt.jdc.utili.ShowAlert;
import com.solt.jdc.utili.StudentRegException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController implements Initializable{

    @FXML
    private TextField loginId;

    @FXML
    private PasswordField password;

    private MemberService srv;
    
    public void close() {
    	Platform.exit();
    }

    public void ok() {
    	try {
			if(loginId.getText().isEmpty() 
					|| loginId.getText() == null) {
				throw new StudentRegException("LoginId is Empty!");
			}
			if(password.getText().isEmpty() ||
					password.getText() == null) {
				throw new StudentRegException("Password is Empty!");
			}
			Member member = srv.findByLoginId(loginId.getText());
			if(member == null) {
				throw new StudentRegException("LoginId is Invalid!");
			}
			if(!password.getText().equals(member.getPassword())){
				throw new StudentRegException("Password is Invalid!");
			}
			MainFrameViewController.showView();
			Security.setMember(member);
			password.getScene().getWindow().hide();
			
		} catch (Exception e) {
			ShowAlert.showAlert(e.getMessage(), AlertType.WARNING);
		}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		srv = new MemberService();
	}

}
