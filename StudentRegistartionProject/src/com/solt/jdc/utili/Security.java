package com.solt.jdc.utili;

import com.solt.jdc.entity.Member;

public class Security {
	private static Member member;

	public static Member getMember() {
		return member;
	}

	public static void setMember(Member member) {
		Security.member = member;
	}
	
}
