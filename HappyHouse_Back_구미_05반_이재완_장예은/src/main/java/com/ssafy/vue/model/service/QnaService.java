package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.ParameterDto;
import com.ssafy.vue.model.QnaDto;

public interface QnaService {
	public boolean writeQna(QnaDto qnaDto) throws Exception;
	public List<QnaDto> listQna(ParameterDto qnaParameterDto) throws Exception;
	public PageNavigation makePageNavigation(ParameterDto qnaParameterDto) throws Exception;
	
	public QnaDto getQna(int qnano) throws Exception;
	public void updateHit(int qnano) throws Exception;
	public boolean modifyQna(QnaDto qnaDto) throws Exception;
	public boolean deleteQna(int qnano) throws Exception;
}
