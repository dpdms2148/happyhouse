
package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.ParameterDto;
import com.ssafy.vue.model.QnaDto;

@Mapper
public interface QnaMapper {
	
	public int writeQna(QnaDto qnaDto) throws SQLException;
	public List<QnaDto> listQna(ParameterDto ParameterDto) throws SQLException;
	public int getTotalCount(ParameterDto ParameterDto) throws SQLException;
	public QnaDto getQna(int qnano) throws SQLException;
	public void updateHit(int qnano) throws SQLException;
	public int modifyQna(QnaDto qnaDto) throws SQLException;
	public int deleteQna(int qnano) throws SQLException;
	
	public int writeQnaAns(QnaDto qnaDto) throws SQLException;
	public int modifyQnaAns(QnaDto qnaDto) throws SQLException;
	
}