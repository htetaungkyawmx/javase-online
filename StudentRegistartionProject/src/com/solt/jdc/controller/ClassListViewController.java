package com.solt.jdc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.jdc.entity.ClassEntity;
import com.solt.jdc.service.ClassService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClassListViewController implements Initializable{

    @FXML
    private TextField schName;

    @FXML
    private TableView<ClassEntity> tableView;

    @FXML
    private TableColumn<ClassEntity, Integer> colId;

    @FXML
    private TableColumn<ClassEntity, String> colClassName;

    @FXML
    private TableColumn<ClassEntity, String> colStartDate;

    @FXML
    private TableColumn<ClassEntity, String> colStartTime;

    @FXML
    private TableColumn<ClassEntity, String> colEndTime;

    @FXML
    private TableColumn<ClassEntity, String> colDays;

    @FXML
    private TableColumn<ClassEntity, String> colDuration;

    @FXML
    private TableColumn<ClassEntity, Integer> colFees;

    @FXML
    private TableColumn<ClassEntity, String> colRemark;

    @FXML
    private TableColumn<ClassEntity, String> colCourse;

    private ClassService srv;
    private List<ClassEntity> list;
    
    private void reload() {
    	list = srv.findAll();
    	tableView.getItems().clear();
    	tableView.getItems().addAll(list);
    }
    
    private void search() {
    	if(!schName.getText().isEmpty() 
    			&& schName.getText() != null) {
    		list = srv.findByName(schName.getText());
    		tableView.getItems().clear();
    		tableView.getItems().addAll(list);
    	}else {
    		reload();
    	}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colClassName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDateStr"));
		colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		colDays.setCellValueFactory(new PropertyValueFactory<>("days"));
		colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		colFees.setCellValueFactory(new PropertyValueFactory<>("fees"));
		colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));
		colCourse.setCellValueFactory(new PropertyValueFactory<>("courseName"));
		
		srv = new ClassService();
		list = new ArrayList<ClassEntity>();
		
		schName.textProperty().addListener((a,b,c) -> search());
		
		reload();
	}

}
