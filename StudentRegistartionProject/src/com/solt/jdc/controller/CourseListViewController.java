package com.solt.jdc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Course;
import com.solt.jdc.service.CourseService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseListViewController implements Initializable{

    @FXML
    private TableView<Course> tableView;

    @FXML
    private TableColumn<Course, Integer> colId;

    @FXML
    private TableColumn<Course, String> colName;

    @FXML
    private TableColumn<Course, Integer> colFees;

    @FXML
    private TableColumn<Course, String> colDuration;

    @FXML
    private TableColumn<Course, String> colRemark;
    
    private CourseService srv;
    
    private List<Course> list;
    
    private void reload() {
    	list = srv.findAll();
    	tableView.getItems().clear();
    	tableView.getItems().addAll(list);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colFees.setCellValueFactory(new PropertyValueFactory<>("fees"));
		colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
		colRemark.setCellValueFactory(new PropertyValueFactory<>("remark"));
		srv = new CourseService();
		list = new ArrayList<Course>();
		
		reload();
	}

}
