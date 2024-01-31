package com.solt.jdc.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainFrameViewController {

    @FXML
    private Label title;

    @FXML
    private StackPane stackPane;

    public void addClass() {
    	loadView("AddClassView.fxml", "Add Class");
    }

    public void addCourse() {
    	loadView("CourseView.fxml", "Add Course");
    }

    public void addMember() {
    	loadView("AddMemberView.fxml", "Add Member");
    }

    public void classList() {
    	loadView("ClassListView.fxml", "Class List");
    }

    public void courseList() {
    	loadView("CourseListView.fxml", "Course List");
    }

    public void exit() {
    	Platform.exit();
    }

    public void memberList() {
    	loadView("MemberListView.fxml", "Member List");
    }

    public void newReg() {
    	loadView("RegistrationView.fxml", "New Registration");
    }

    public void regList() {
    	loadView("RegistrationListView.fxml", "Registration List");
    }

    public void studentList() {
    	loadView("StudentListView.fxml", "Student List");
    }
    
    public void loadView(String view,String titleStr){
    	try {
			Parent viewName = FXMLLoader
					.load(getClass().getResource(view));
			stackPane.getChildren().clear();
			stackPane.getChildren().add(viewName);
			
			title.setText(titleStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void showView() throws IOException {
    	FXMLLoader load = new FXMLLoader(MainFrameViewController
    			.class.getResource("MainFrameView.fxml"));
    	Parent view = load.load();
    	MainFrameViewController controller = load.getController();
    	Stage stage = new Stage();
    	controller.newReg();
        	stage.initModality(Modality.APPLICATION_MODAL);
    	stage.setScene(new Scene(view));
    	stage.show();
    }

}
