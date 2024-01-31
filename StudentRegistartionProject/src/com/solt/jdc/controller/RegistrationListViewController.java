package com.solt.jdc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Registration;
import com.solt.jdc.service.RegistrationService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RegistrationListViewController implements Initializable {

	@FXML
	private TextField schStuName;

	@FXML
	private TextField schClassName;

	@FXML
	private TableView<Registration> tableView;

	@FXML
	private TableColumn<Registration, Integer> colId;

	@FXML
	private TableColumn<Registration, String> colStuName;

	@FXML
	private TableColumn<Registration, String> colClassName;

	@FXML
	private TableColumn<Registration, Integer> colFees;

	@FXML
	private TableColumn<Registration, Integer> colPaid;

	@FXML
	private TableColumn<Registration, Integer> colRemain;

	@FXML
	private TableColumn<Registration, String> colStartDate;

	@FXML
	private TableColumn<Registration, String> colMember;

	private RegistrationService srv;
	private List<Registration> list;

	private void reload() {
		list = srv.findAll();
		tableView.getItems().clear();
		tableView.getItems().addAll(list);
	}

	private void search() {
		if (!schStuName.getText().isEmpty() && 
				schClassName.getText() != null) {
			list = srv.find(schStuName.getText());
			tableView.getItems().clear();
			tableView.getItems().addAll(list);
		}else {
			reload();
		}

	}
	private void searchByClassName() {
		if(!schClassName.getText().isEmpty() && 
				schClassName.getText() != null) {
			list = srv.findByClassName(schClassName.getText());
			tableView.getItems().clear();
			tableView.getItems().addAll(list);
		}else {
			reload();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colStuName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
		colClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
		colFees.setCellValueFactory(new PropertyValueFactory<>("fees"));
		colPaid.setCellValueFactory(new PropertyValueFactory<>("paid"));
		colRemain.setCellValueFactory(new PropertyValueFactory<>("remain"));
		colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDateStr"));
		colMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));

		srv = new RegistrationService();
		list = new ArrayList<>();
		
		schStuName.textProperty()
				.addListener((a,b,c) -> search());
		schClassName.textProperty()
				.addListener((a,b,c) -> searchByClassName());
		reload();
	}

}
