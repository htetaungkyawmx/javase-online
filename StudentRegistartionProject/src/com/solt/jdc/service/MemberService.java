package com.solt.jdc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.solt.jdc.entity.Member;
import com.solt.jdc.utili.DatabaseManager;

public class MemberService {

	public Member findByLoginId(String text) {
		String sql = "Select * from member where loginId = ?";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, text);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Member member = new Member();
					member.setLoginId(rs.getString("loginId"));
					member.setName(rs.getString("name"));
					member.setPhone(rs.getString("phone"));
					member.setEmail(rs.getString("email"));
					member.setAddress(rs.getString("address"));
					member.setPassword(rs.getString("password"));
					return member;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Member> findAll() {
		List<Member> list = new ArrayList<Member>();
		String sql = "select * from member";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs= stmt.executeQuery();
			while(rs.next()) {
				Member mem = new Member();
				mem.setLoginId(rs.getString("loginId"));
				mem.setName(rs.getString("name"));
				mem.setPhone(rs.getString("phone"));
				mem.setEmail((rs.getString("email")));
				mem.setAddress((rs.getString("address")));
				list.add(mem);
				System.out.println(list);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Member> findByName(String text) {
		List<Member> list  = new ArrayList<Member>();
		String sql = "select * from member where name like ?";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
				stmt.setString(1, text.concat("%"));
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Member mem = new Member();
					mem.setLoginId(rs.getString("loginId"));
					mem.setName(rs.getString("name"));
					mem.setPhone(rs.getString("phone"));
					mem.setEmail((rs.getString("email")));
					mem.setAddress((rs.getString("address")));
					list.add(mem);
					
				}
			
				return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	public int add(Member member) {
		int num = 0;
		String sql = "insert into member (loginId,name,phone,email,address,password)"
				+ " values (?,?,?,?,?,?)";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, member.getLoginId());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getPhone());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getAddress());
			stmt.setString(6, member.getPassword());
			
			num = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}

}
