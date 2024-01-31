package com.solt.jdc.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Registration {
	private int id;
	private int fees;
	private int paid;
	private int studentId;
	private int classId;
	private String memberId;
	
	private String memberName;
	private String studentName;
	private String className;
	private LocalDate startDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getRemain() {
		return getFees() - getPaid();
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public String getStartDateStr() {
		return startDate.format(DateTimeFormatter
					.ofPattern("dd-MM-yyyy"));
	}
	
	
}
