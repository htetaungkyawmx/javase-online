package com.solt.jdc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.solt.jdc.entity.Student;
import com.solt.jdc.utili.DatabaseManager;

public class StudentService {

	public int add(Student student) {
		int id = 0;
		String sql = "insert into student (name,phone,email,"
				+ "address) values (?,?,?,?)";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt =
						con.prepareStatement
						(sql,Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getPhone());
			stmt.setString(3, student.getEmail());
			stmt.setString(4, student.getAddress());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public List<Student> findAll() {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from student";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Student stu = new Student();
					stu.setId(rs.getInt("id"));
					stu.setName(rs.getString("name"));
					stu.setPhone(rs.getString("phone"));
					stu.setEmail((rs.getString("email")));
					stu.setAddress((rs.getString("address")));
					list.add(stu);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Student> findByName(String text) {
		List<Student> list  = new ArrayList<Student>();
		String sql = "select * from student where name like ?";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, text.concat("%"));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Student stu = new Student();
					stu.setId(rs.getInt("id"));
					stu.setName(rs.getString("name"));
					stu.setPhone(rs.getString("phone"));
					stu.setEmail((rs.getString("email")));
					stu.setAddress((rs.getString("address")));
					list.add(stu);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
