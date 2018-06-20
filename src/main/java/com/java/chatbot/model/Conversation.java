package com.java.chatbot.model;

/**
 * @author sureshMkumar
 * @date Jun 18, 2018
 *
 */
public class Conversation {
	private String question;
	private String replay;
	private String dateTime;
	private int goBackId;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getReplay() {
		return replay;
	}
	public void setReplay(String replay) {
		this.replay = replay;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String datetime) {
		this.dateTime = datetime;
	}
	public int getGoBackId() {
		return goBackId;
	}
	public void setGoBackId(int goBackId) {
		this.goBackId = goBackId;
	}
}
