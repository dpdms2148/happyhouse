package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.ParameterDto;
import com.ssafy.vue.model.QnaDto;
import com.ssafy.vue.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeQna(QnaDto qnaDto) throws Exception {
		if(qnaDto.getSubject() == null || qnaDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnaMapper.class).writeQna(qnaDto) == 1;
	}

	@Override
	public List<QnaDto> listQna(ParameterDto ParameterDto) throws Exception {
		int start = ParameterDto.getPg() == 0 ? 0 : (ParameterDto.getPg() - 1) * ParameterDto.getSpp();
		ParameterDto.setStart(start);
		return sqlSession.getMapper(QnaMapper.class).listQna(ParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(ParameterDto ParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(ParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(QnaMapper.class).getTotalCount(ParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / ParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = ParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < ParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public QnaDto getQna(int qnano) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).getQna(qnano);
	}
	
	@Override
	public void updateHit(int qnano) throws Exception {
		sqlSession.getMapper(QnaMapper.class).updateHit(qnano);
	}

	@Override
	@Transactional
	public boolean modifyQna(QnaDto qnaDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).modifyQna(qnaDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQna(int qnano) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).deleteQna(qnano) == 1;
	}
	
	@Override
	public boolean writeQnaAns(QnaDto qnaDto) throws Exception {
		if(qnaDto.getAnswer() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnaMapper.class).writeQnaAns(qnaDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean modifyQnaAns(QnaDto qnaDto) throws Exception {
		return sqlSession.getMapper(QnaMapper.class).modifyQnaAns(qnaDto) == 1;
	}
}