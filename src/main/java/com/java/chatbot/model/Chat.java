package com.java.chatbot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ${Suresh M Kumar}
 * @date Jun 18, 2018
 */


public class Chat {
	
    private String chatId;
    private MessageType type;
    private String datetime;
    private String sender;
    private String content;
    private ArrayList<String> keywords = new ArrayList<String>();
    private transient List<Conversation> conversation;
    private boolean EndCoversation;
    
    public boolean isEndCoversation() {
		return EndCoversation;
	}

	public void setEndCoversation(boolean endCoversation) {
		EndCoversation = endCoversation;
	}

	public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public List<Conversation> getConversation() {
		return conversation;
	}

	public void setConversation(List<Conversation> conversation) {
		this.conversation = conversation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
}
