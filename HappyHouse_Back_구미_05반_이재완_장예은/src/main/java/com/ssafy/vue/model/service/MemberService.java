package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto loginUser(MemberDto memberDto) throws Exception;
	public MemberDto getUserinfo(String userid) throws Exception;
	
	public boolean joinMember(MemberDto memberDto) throws Exception;
	public boolean updateMember(MemberDto memberDto) throws Exception;
	public boolean deleteMember(String userid) throws Exception;

	int idCheck(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
}
