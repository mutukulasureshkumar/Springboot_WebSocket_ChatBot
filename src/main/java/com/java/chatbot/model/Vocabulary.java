package com.java.chatbot.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ${Suresh M Kumar}
 * @date Jun 19, 2018
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Vocabulary {

	@Id
	private int id;
	private String message;

	public Vocabulary() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
