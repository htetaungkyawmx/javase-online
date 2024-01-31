package com.solt.jdc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.solt.jdc.entity.Registration;
import com.solt.jdc.utili.DatabaseManager;

public class RegistrationService {

	public int add(Registration reg) {
		int num = 0;
		String sql = "insert into registration (fees,paid,"
				+ "Student_id,Member_loginId,Class_id) values "
				+ "(?,?,?,?,?)";
		
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(sql)){
				stmt.setInt(1, reg.getFees());
				stmt.setInt(2, reg.getPaid());
				stmt.setInt(3, reg.getStudentId());
				stmt.setString(4,reg.getMemberId());
				stmt.setInt(5, reg.getClassId());
				
				num = stmt.executeUpdate();
				
				return num;
				
		} catch (Exception e) {
			
			
			
			e.printStackTrace();
		}
		return num;
	}

	public List<Registration> findAll() {
		List<Registration> list = new ArrayList<>();
		
		String sql = "select r.id as id, r.fees as fees, r.paid as paid,"
				+ " r.Student_id as Student_id, r.Member_loginId "
				+ "as Member_loginId, r.Class_id as Class_id, s.id "
				+ "as stu_id, s.name as stu_name, c.id as class_id, "
				+ "c.name as class_name, c.start_date as "
				+ "start_date, m.loginId as login_id,"
				+ " m.name as member_name from registration r "
				+ "inner join class c on r.Class_id = c.id "
				+ "inner join student s on r.Student_id = s.id "
				+ "inner join member m on r.Member_loginId = m.loginId";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt =
						con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Registration reg = new Registration();
				reg.setId(rs.getInt("id"));
				reg.setFees(rs.getInt("fees"));
				reg.setPaid(rs.getInt("paid"));
				reg.setStudentId(rs.getInt("Student_id"));
				reg.setMemberId(rs.getString("Member_loginId"));
				reg.setClassId(rs.getInt("Class_id"));
				reg.setClassName(rs.getString("class_name"));
				reg.setStartDate(rs.getDate("start_date").toLocalDate());
				reg.setMemberName(rs.getString("member_name"));
				reg.setStudentName(rs.getString("stu_name"));
				list.add(reg);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Registration> find(String stuName) {
		List<Registration> list = new ArrayList<>();
		String sql = "select r.id as id, r.fees as fees, r.paid as paid,"
				+ " r.Student_id as Student_id, r.Member_loginId "
				+ "as Member_loginId, r.Class_id as Class_id, s.id "
				+ "as stu_id, s.name as stu_name, c.id as class_id, "
				+ "c.name as class_name, c.start_date as "
				+ "start_date, m.loginId as login_id,"
				+ " m.name as member_name from registration r "
				+ "inner join class c on r.Class_id = c.id "
				+ "inner join member m on r.Member_loginId = m.loginId "
				+ "inner join student s on r.Student_id = s.id "
				+ "where s.name like ?";
			try(Connection con = DatabaseManager.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)) {
					stmt.setString(1, stuName.concat("%"));
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						Registration reg = new Registration();
						reg.setId(rs.getInt("id"));
						reg.setFees(rs.getInt("fees"));
						reg.setPaid(rs.getInt("paid"));
						reg.setStudentId(rs.getInt("Student_id"));
						reg.setMemberId(rs.getString("Member_loginId"));
						reg.setClassId(rs.getInt("Class_id"));
						reg.setClassName(rs.getString("class_name"));
						reg.setStartDate(rs.getDate("start_date").toLocalDate());
						reg.setMemberName(rs.getString("member_name"));
						reg.setStudentName(rs.getString("stu_name"));
						list.add(reg);
					}
					return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return null;
		
	}
	public List<Registration> findByClassName(String className) {
		List<Registration> list = new ArrayList<>();
		String sql = "select r.id as id, r.fees as fees, r.paid as paid,"
				+ " r.Student_id as Student_id, r.Member_loginId "
				+ "as Member_loginId, r.Class_id as Class_id, s.id "
				+ "as stu_id, s.name as stu_name, c.id as class_id, "
				+ "c.name as class_name, c.start_date as "
				+ "start_date, m.loginId as login_id,"
				+ " m.name as member_name from registration r "
				+ "inner join member m on r.Member_loginId = m.loginId "
				+ "inner join student s on r.Student_id = s.id "
				+ "inner join class c on r.Class_id = c.id "
				+ "where c.name like ?";
			try(Connection con = DatabaseManager.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)) {
					stmt.setString(1, className.concat("%"));
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						Registration reg = new Registration();
						reg.setId(rs.getInt("id"));
						reg.setFees(rs.getInt("fees"));
						reg.setPaid(rs.getInt("paid"));
						reg.setStudentId(rs.getInt("Student_id"));
						reg.setMemberId(rs.getString("Member_loginId"));
						reg.setClassId(rs.getInt("Class_id"));
						reg.setClassName(rs.getString("class_name"));
						reg.setStartDate(rs.getDate("start_date").toLocalDate());
						reg.setMemberName(rs.getString("member_name"));
						reg.setStudentName(rs.getString("stu_name"));
						list.add(reg);
					}
					return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return null;
		
	}

}
