package com.solt.jdc.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.solt.jdc.entity.ClassEntity;
import com.solt.jdc.utili.DatabaseManager;

public class ClassService {

	public int add(ClassEntity ce) {
		String sql = "insert into class (name,start_date,day,"
				+ "start_time,end_time,duration,fees,remark,"
				+ "Course_id) values (?,?,?,?,?,?,?,?,?)";
		int num = 0;
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, ce.getName());	
				stmt.setDate(2, Date.valueOf(ce.getStartDate()));
				stmt.setString(3, ce.getDays());
				stmt.setString(4, ce.getStartTime());
				stmt.setString(5, ce.getEndTime());
				stmt.setString(6, ce.getDuration());
				stmt.setInt(7, ce.getFees());
				stmt.setString(8, ce.getRemark());
				stmt.setInt(9, ce.getCourseId());
				
				num = stmt.executeUpdate();
				return num;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public List<ClassEntity> findAll() {
		String sql = "select c.id, c.name, c.start_date, c.day, c.start_time, "
				+ "c.end_time, c.duration, c.fees, c.remark, c.course_id, "
				+ "d.name from Class c inner join Course d on "
				+ "c.course_id = d.id";
		
			List<ClassEntity> list = new ArrayList<>();
			try(Connection con = DatabaseManager.getConnection();
					PreparedStatement stmt = con.prepareStatement(sql)) {
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						ClassEntity ce = new ClassEntity();
						ce.setId(rs.getInt("c.id"));
						ce.setName(rs.getString("c.name"));
						ce.setStartDate(rs.getDate("c.start_date")
											.toLocalDate());
						ce.setDays(rs.getString("c.day"));
						ce.setStartTime(rs.getString("c.start_time"));
						ce.setEndTime(rs.getString("c.end_time"));
						ce.setDuration(rs.getString("c.duration"));
						ce.setFees(rs.getInt("c.fees"));
						ce.setRemark(rs.getString("c.remark"));
						ce.setCourseId(rs.getInt("c.course_id"));
						ce.setCourseName(rs.getString("d.name"));
						list.add(ce);
					}
					return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	public List<ClassEntity> findByName(String text) {
		String sql = "select c.id, c.name, c.start_date, c.day, c.start_time, "
				+ "c.end_time, c.duration, c.fees, c.remark, c.course_id, "
				+ "d.name from Class c inner join Course d where "
				+ "c.course_id = d.id and c.name like ?";
		List<ClassEntity> list = new ArrayList<ClassEntity>();
		
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, text.concat("%"));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					ClassEntity ce = new ClassEntity();
					ce.setId(rs.getInt("c.id"));
					ce.setName(rs.getString("c.name"));
					ce.setStartDate(rs.getDate("c.start_date")
										.toLocalDate());
					ce.setDays(rs.getString("c.day"));
					ce.setStartTime(rs.getString("c.start_time"));
					ce.setEndTime(rs.getString("c.end_time"));
					ce.setDuration(rs.getString("c.duration"));
					ce.setFees(rs.getInt("c.fees"));
					ce.setRemark(rs.getString("c.remark"));
					ce.setCourseId(rs.getInt("c.course_id"));
					ce.setCourseName(rs.getString("d.name"));
					list.add(ce);
				}
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
