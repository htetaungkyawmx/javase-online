package com.solt.jdc.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.solt.jdc.entity.Member;
import com.solt.jdc.service.MemberService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MemberListViewController implements Initializable {

    @FXML
	private TextField schName;
	
    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, String> colId;

    @FXML
    private TableColumn<Member, String> colName;

    @FXML
    private TableColumn<Member, String> colPhone;

    @FXML
    private TableColumn<Member, String> colEmail;

    @FXML
    private TableColumn<Member, String> colAddress;

    private MemberService srv;
    
    private List<Member>list;
    
    public void reload() {
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
		colId.setCellValueFactory(new PropertyValueFactory<>("loginId"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		srv = new MemberService();
		list = new ArrayList<Member>();
		
		schName.textProperty().addListener((a,b,c) -> search());
		reload();
	}

		
	}


