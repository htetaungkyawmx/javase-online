package com.solt.jdc.controller;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

import com.solt.jdc.entity.ClassEntity;
import com.solt.jdc.entity.Course;
import com.solt.jdc.service.ClassService;
import com.solt.jdc.service.CourseService;
import com.solt.jdc.utili.ShowAlert;
import com.solt.jdc.utili.StudentRegException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

public class AddClassViewController implements Initializable{

    @FXML
    private TextField startTimes;

    @FXML
    private TextField endTimes;

    @FXML
    private TextArea remarkta;

    @FXML
    private ComboBox<Course> courseId;

    @FXML
    private DatePicker startDate;

    @FXML
    private HBox hbox;

    @FXML
    private TextField nametf;

    @FXML
    private TextField durationtf;

    @FXML
    private TextField feestf;
    
    private CourseService courseSrv;

    private ClassService srv;
    
    public void add() {
    	try {
    		if(nametf.getText().isEmpty() 
    				|| nametf.getText() == null) {
        		throw new StudentRegException("Name is Empty!");
        	}
    		if(courseId.getValue() == null) {
    			throw new StudentRegException
    						("Course is not Selected!");
    		}
    		if(startDate.getValue() == null) {
    			throw new StudentRegException
    					("Start Date is not Selected!");
    		}
    		if(startTimes.getText().isEmpty()|| 
    				startTimes.getText() == null) {
    			throw new StudentRegException
    					("Start Time is Empty!");
    		}
    		if(endTimes.getText().isEmpty() ||
    				endTimes.getText() == null) {
    			throw new StudentRegException("End Time is Empty!");
    		}
    		if(hbox.getChildren().stream()
    				.filter(a -> a instanceof CheckBox)
    				.map(a -> (CheckBox)a)
    				.filter(a -> a.isSelected()).count() <= 0) {
    			throw new StudentRegException("Days is not Selected!");
    		}
    		if(durationtf.getText().isEmpty() || 
    				durationtf.getText() == null) {
    			throw new StudentRegException("Duration is Empty!");
    		}
    		if(feestf.getText().isEmpty() || 
    				feestf.getText() == null) {
    			throw new StudentRegException("Fees is Empty!");
    		}
    		int feetsInt = 0;
    		try {
				feetsInt = Integer.valueOf(feestf.getText());
			} catch (NumberFormatException e) {
				throw new StudentRegException
					("Please enter number only in fees");
			}
    		
    		
    		ClassEntity ce = new ClassEntity();
    		ce.setName(nametf.getText());
    		ce.setCourseId(courseId.getValue().getId());
    		ce.setStartDate(startDate.getValue());
    		
    		StringBuffer sb = new StringBuffer();
    		hbox.getChildren().stream()
    			.filter(a -> a instanceof CheckBox)
    			.map(a -> (CheckBox) a).filter(a -> a.isSelected()).
    			forEach(a -> sb.append(a.getText()).append(", "));
    		String dayStr = sb.substring(0,sb.length()-2);
    		ce.setDays(dayStr);
    		
    		ce.setStartTime(startTimes.getText());
    		ce.setEndTime(endTimes.getText());
    		ce.setDuration(durationtf.getText());
    		ce.setFees(feetsInt);
    		ce.setRemark(remarkta.getText());
    		
    		int num = srv.add(ce);
    		if(num == 0) {
    			ShowAlert.showAlert("Add UnComplete!", AlertType.ERROR);
    		}else {
    			ShowAlert.showAlert
    					("Add Complete", AlertType.CONFIRMATION);
    			clear();
    		}
    		
		} catch (StudentRegException e) {
			ShowAlert.showAlert(e.getMessage(), AlertType.WARNING);
		}
    }

    public void clear() {
    	nametf.clear();
    	courseId.setValue(null);
    	courseId.setPromptText("Select Course");;
    	startDate.setValue(null);
    	
    	hbox.getChildren().stream()
    			.filter(a -> a instanceof CheckBox)
    			.map(a -> (CheckBox)a)
    			.forEach(a -> a.setSelected(false));
    	
    	startTimes.clear();
    	endTimes.clear();
    	durationtf.clear();
    	feestf.clear();
    	remarkta.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		courseSrv = new CourseService();
		srv = new ClassService();
		remarkta.setWrapText(true);
		courseId.getItems().addAll(courseSrv.findAll());
		for(DayOfWeek days : DayOfWeek.values()) {
			CheckBox cbox = new CheckBox
					(days.getDisplayName
							(TextStyle.SHORT, Locale.getDefault()));
			hbox.getChildren().add(cbox);
		}
	}

}
