package com.java.chatbot.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.java.chatbot.model.Chat;
import com.java.chatbot.model.Nodes;
import com.java.chatbot.model.Relations;
import com.java.chatbot.repository.NodesRepossitory;
import com.java.chatbot.repository.RelationsRepossitory;

/**
 * @author ${Suresh M Kumar}
 * @date Jun 18, 2018
 */
@Service
public class ChatService {
	
	@Value("${chatbot.name}")
	private String chatBotName;
/*	@Value("${chatbot.welcome.message}")
	private String welcomeTemplate;
	@Value("${chatbot.continuation.message}")
	private String contMessageTemplate;*/
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@Autowired
	private RelationsRepossitory relationsRepossitory;
	
	@Autowired
	private NodesRepossitory nodesRepossitory;
	
	private static final String NAME_PLACEHOLDER="<<name>>"; 
	private static final String RELATIONS_PLACEHOLDER="<<relations>>"; 
	private static final String NODE_PLACEHOLDER="<<node>>"; 
	
	public synchronized String getchatId() {
		return String.valueOf(new Date().getTime());
	}
	
	public void joinUser(Chat chat) {
		ArrayList<Relations> relationsList = null;
		try {
			relationsList = relationsRepossitory.findByParentNodeId(1);
			chat.setContent(buildWelcomeMessgae(chat, relationsList));
		} catch (NullPointerException e) {
			chat.setContent(
					"Looks like Admin is not ready for any suggessions!! Please enter your query, our team will get back to you. Thanks.");
		}
		chat.setType(Chat.MessageType.CHAT);
		chat.setSender(chatBotName);
		template.convertAndSend("/topic/private/" + chat.getChatId(), chat);
	}
	public void sendMessage(Chat chat) {
		if(chat.getContent() == null || chat.getContent().trim().length()==0) {
			chat.setChatId("looking like you are not entering valid question, Please type your question my friend.");
		} else {
			Nodes node = nodesRepossitory.findByNode(chat.getContent());
			if (node == null) {
				chat.setContent(
						"Looks like you are asking which is out of my context. Please select any product which i mentioned above. Thanks. ");
			} else {
				ArrayList<Relations> relationsList = relationsRepossitory.findByParentNodeId(node.getId());
				if(!relationsList.isEmpty()) {
					chat.setContent(buildContinuationMessgae(chat, relationsList));
				}
			}
		}
		chat.setSender(chatBotName);
		template.convertAndSend("/topic/private/"+chat.getChatId(), chat);
	}
	
	public String buildWelcomeMessgae(Chat chat, ArrayList<Relations> relationsList) {
		String welcomeMessage = relationsList.get(0).getVocabulary().getMessage();
		welcomeMessage = welcomeMessage.replace(NAME_PLACEHOLDER, chat.getSender());
		welcomeMessage = welcomeMessage.replace(RELATIONS_PLACEHOLDER, formatRelations(chat, relationsList));
		return welcomeMessage;
	}
	
	public String buildContinuationMessgae(Chat chat, ArrayList<Relations> relationsList) {
		String contMessage = relationsList.get(0).getVocabulary().getMessage();
		contMessage = contMessage.replace(NAME_PLACEHOLDER, chat.getSender());
		contMessage = contMessage.replace(RELATIONS_PLACEHOLDER, formatRelations(chat, relationsList));
		contMessage = contMessage.replace(NODE_PLACEHOLDER, chat.getContent());
		return contMessage;
	}
	
	public String formatRelations(Chat chat, ArrayList<Relations> relationsList) {
		String nodesNames="";
		ArrayList<String> keywords = new ArrayList<String>();
		for(Relations relations:relationsList) {
			nodesNames += relations.getnodes().getNode()+","; 
			keywords.add(relations.getnodes().getNode());
		}
		chat.setKeywords(keywords);
		return nodesNames.substring(0, (nodesNames.length()-1));
		//return "Movies or Events";
	}

}
