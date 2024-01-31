package com.solt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.jdc.entity.ClassEntity;
import com.solt.jdc.entity.Registration;
import com.solt.jdc.entity.Student;
import com.solt.jdc.service.ClassService;
import com.solt.jdc.service.RegistrationService;
import com.solt.jdc.service.StudentService;
import com.solt.jdc.utili.Security;
import com.solt.jdc.utili.ShowAlert;
import com.solt.jdc.utili.StudentRegException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RegistrationViewController implements Initializable{

    @FXML
    private ComboBox<ClassEntity> classCombo;

    @FXML
    private Label startDate;

    @FXML
    private Label days;

    @FXML
    private Label times;

    @FXML
    private Label fees;

    @FXML
    private Label duration;

    @FXML
    private TextField sName;

    @FXML
    private TextField sPhone;

    @FXML
    private TextField sEmail;

    @FXML
    private TextField sAddress;

    @FXML
    private TextField paid;
    
    private ClassService classSrv;
    private StudentService stuSrv;
    private RegistrationService srv;
    
    public void add() {
    	try {
			if(classCombo.getValue() == null) {
				throw new StudentRegException
						("Class is not Selected!");
			}
			if(sName.getText().isEmpty() || sName.getText() == null) {
				throw new StudentRegException
						("Student Name is Empty!");
			}
			if(sPhone.getText().isEmpty() || sPhone.getText() == null) {
				throw new StudentRegException
						("Student Phone is Empty!");
			}
			if(paid.getText().isEmpty() || paid.getText() == null) {
				throw new StudentRegException("Paid is Empty!");
			}
			int num = 0;
			try {
				num = Integer.valueOf(paid.getText());
			} catch (NumberFormatException e) {
				throw new StudentRegException
						("Please only enter number in paid");
			}
			Student student = new Student();
			student.setName(sName.getText());
			student.setPhone(sPhone.getText());
			student.setEmail(sEmail.getText());
			student.setAddress(sAddress.getText());
			int stuId = stuSrv.add(student);
			
			Registration reg = new Registration();
			reg.setStudentId(stuId);
			reg.setPaid(num);
			reg.setFees(classCombo.getValue().getFees());
			reg.setClassId(classCombo.getValue().getId());
			reg.setMemberId(Security.getMember().getLoginId());
			int result = srv.add(reg);
			if(result != 0) {
				ShowAlert.showAlert("Add Complete",
								AlertType.CONFIRMATION);
				clear();
			}else {
				ShowAlert.showAlert("Add UnComplete!",
									AlertType.ERROR);
			}
    		
		} catch (StudentRegException e) {
			ShowAlert.showAlert(e.getMessage()
					, AlertType.WARNING);
		}
    }

    public void clear() {
    	classCombo.setValue(null);
    	classCombo.setPromptText("Select Class");
    	startDate.setText("");
    	days.setText("");
    	times.setText("");
    	fees.setText("");
    	duration.setText("");
    	sName.clear();
    	sPhone.clear();
    	sEmail.clear();
    	sAddress.clear();
    	paid.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		classSrv = new ClassService();
		stuSrv = new StudentService();
		srv = new RegistrationService();
		
		classCombo.getItems().addAll(classSrv.findAll());
		classCombo.setOnAction(event -> {
			if(classCombo.getValue() != null) {
				showMethod();
			}
		});
	}

	private void showMethod() {
		ClassEntity ce = classCombo.getValue();
		startDate.setText(ce.getStartDateStr());
		days.setText(ce.getDays());
		times.setText(ce.getStartTime() + " - "+ce.getEndTime());
		fees.setText(String.valueOf(ce.getFees()));
		duration.setText(ce.getDuration());
	}

}
