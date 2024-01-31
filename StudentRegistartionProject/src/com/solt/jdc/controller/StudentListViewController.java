package com.solt.jdc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Student;
import com.solt.jdc.service.StudentService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentListViewController implements Initializable{

	
    @FXML
    private TextField schName;

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> colId;

    @FXML
    private TableColumn<Student, String> colName;

    @FXML
    private TableColumn<Student, String> colPhone;

    @FXML
    private TableColumn<Student, String> colEmail;

    @FXML
    private TableColumn<Student, String> colAddress;

    private StudentService srv;
    
    private List<Student> list;
    
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
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		srv = new StudentService();
		list = new ArrayList<Student>();
		
		schName.textProperty().addListener((a,b,c) -> search());
		reload();
	}

}
