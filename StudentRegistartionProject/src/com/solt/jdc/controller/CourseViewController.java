package com.solt.jdc.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Course;
import com.solt.jdc.service.CourseService;
import com.solt.jdc.utili.ShowAlert;
import com.solt.jdc.utili.StudentRegException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CourseViewController implements Initializable{

    @FXML
    private TextField nametf;

    @FXML
    private TextField durationtf;

    @FXML
    private TextField feestf;

    @FXML
    private TextArea remarkta;
    
    private CourseService srv;

    public void add() {
    	try {
			if(nametf.getText().isEmpty() 
					|| nametf.getText() == null) {
				throw new StudentRegException("Name is Empty!");
			}
    		if(durationtf.getText().isEmpty() || 
    				durationtf.getText() == null) {
    			throw new StudentRegException("Duration is Empty!");
    		}
    		if(feestf.getText().isEmpty() ||
    				feestf.getText() == null) {
    			throw new StudentRegException("Fees is Empty!");
    		}
    		int feesInt = 0;
    		try {
				feesInt = Integer.valueOf(feestf.getText());
			} catch (NumberFormatException e) {
				throw new StudentRegException
					("Please enter number only in fees!");
			}
    		
    		Course course = new Course();
    		course.setName(nametf.getText());
    		course.setDuration(durationtf.getText());
    		course.setFees(feesInt);
    		course.setRemark(remarkta.getText());
    		
    		int num = srv.add(course);
    		if(num != 0) {
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
    	nametf.clear();
    	durationtf.clear();
    	feestf.clear();
    	remarkta.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		srv = new CourseService();
		
	}

	

}
