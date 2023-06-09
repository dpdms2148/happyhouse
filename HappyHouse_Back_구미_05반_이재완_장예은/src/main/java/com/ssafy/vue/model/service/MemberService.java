package com.ssafy.vue.model.service;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {

	public MemberDto loginUser(MemberDto memberDto) throws Exception;
	public MemberDto getUserinfo(String userid) throws Exception;
	
	public boolean registUser(MemberDto memberDto) throws Exception;
	public boolean updateUser(MemberDto memberDto) throws Exception;
	public boolean deleteUser(String userid) throws Exception;

	int idCheck(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
}
