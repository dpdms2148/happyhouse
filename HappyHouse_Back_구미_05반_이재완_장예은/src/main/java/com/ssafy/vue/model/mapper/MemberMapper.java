package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {
	
	public MemberDto loginUser(MemberDto memberDto) throws SQLException;
	public MemberDto getUserinfo(String userid) throws SQLException;
	
	public int joinMember(MemberDto memberDto) throws SQLException;
	public int updateMember(MemberDto memberDto) throws SQLException;
	public int deleteMember(String userid) throws SQLException;

	int idCheck(String userid) throws Exception;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
