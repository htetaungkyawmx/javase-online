package com.solt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Member;
import com.solt.jdc.service.MemberService;
import com.solt.jdc.utili.ShowAlert;
import com.solt.jdc.utili.StudentRegException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddMemberViewController implements Initializable{
	
    @FXML
	private TextField loginId;
	
    @FXML
    private TextField nametf;

    @FXML
    private TextField phonetf;

    @FXML
    private TextField emailtf;

    @FXML
    private TextField addresstf;

    @FXML
    private PasswordField passwordpa;

    private MemberService srv;

	private String text;
   
    
	@SuppressWarnings("unused")
	public void add() {
    	try {
    		if(loginId.getText().isEmpty() || loginId.getText()==null) {
				throw new StudentRegException("LoginId is Empty!");
			}
			if(nametf.getText().isEmpty() || nametf.getText()==null) {
				throw new StudentRegException("Name is Empty!");
			}
			if(phonetf.getText().isEmpty() || phonetf.getText()==null) {
				throw new StudentRegException("Phone is Empty!");
			}
			int num=0;
			try {
				num = Integer.valueOf(phonetf.getText());
			} catch (NumberFormatException e) {
				throw new StudentRegException
						("Please enter only number in Phone!");
			}
			if(emailtf.getText().isEmpty() || emailtf.getText()==null) {
				throw new StudentRegException("Email is Empty!");
			}
			if(addresstf.getText().isEmpty() || addresstf.getText()==null) {
				throw new StudentRegException("Address is Empty!");
			}
			if(passwordpa.getText().isEmpty() || passwordpa.getText()==null) {
				throw new StudentRegException("Password is Empty!");
			}
		
    	 Member member = new Member();
    	    member.setLoginId(loginId.getText());
    	    member.setName(nametf.getText());
    	    member.setPhone(phonetf.getText());
    	    member.setEmail(emailtf.getText());
    	    member.setAddress(addresstf.getText());
    	    member.setPassword(passwordpa.getText());
    	    
    	    
    	   
		Member num1= srv.findByLoginId(text);
    	   
    	   int num2 = srv.add(member);
    	    if(num2 != 0) {
    			ShowAlert.showAlert("Add Complete",AlertType.CONFIRMATION);
    			clear();
    		}else {
    			ShowAlert.showAlert("Add Error!", AlertType.ERROR);
    		}
		} catch (Exception e) {
			ShowAlert.showAlert
				(e.getMessage(), AlertType.WARNING);
		}
    
    }

   
    public void clear() {
    	loginId.clear();
    	nametf.clear();
    	phonetf.clear();
    	emailtf.clear();
    	addresstf.clear();
    	passwordpa.clear();
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		srv= new MemberService();
		
	}

}
