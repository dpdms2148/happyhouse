package com.ssafy.vue.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnaDto : 게시글정보", description = "qna의 상세 정보를 나타낸다.")
public class QnaDto {
	@ApiModelProperty(value = "글번호")
	private int qnano;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	@ApiModelProperty(value = "글제목")
	private String subject;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	@ApiModelProperty(value = "답변내용")
	private String answer;
	@ApiModelProperty(value = "답변일")
	private String anstime;

	public int getQnano() {
		return qnano;
	}

	public void setQnano(int qnano) {
		this.qnano = qnano;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnstime() {
		return anstime;
	}

	public void setAnstime(String anstime) {
		this.anstime = anstime;
	}

	@Override
	public String toString() {
		return "QnaDto [qnano=" + qnano + ", userid=" + userid + ", subject=" + subject + ", content=" + content
				+ ", hit=" + hit + ", regtime=" + regtime + ", answer=" + answer + ", anstime=" + anstime + "]";
	}

}