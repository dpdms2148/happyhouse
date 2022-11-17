package com.ssafy.vue.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto loginUser(MemberDto memberDto) throws Exception {
		if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).loginUser(memberDto);
	}

	@Override
	public MemberDto getUserinfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getUserinfo(userid);
	}

	@Override
	public boolean registUser(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).registUser(memberDto) == 1;
	}

	@Override
	@Transactional
	public boolean updateUser(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).updateUser(memberDto) == 1;		
	}

	@Override
	@Transactional
	public boolean deleteUser(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteUser(userid) == 1;
	}

	@Override
	public int idCheck(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).idCheck(userid);
	}
	
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}

}
