package com.java.chatbot.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ${Suresh M Kumar}
 * @date Jun 19, 2018
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Relations {

	@Id
	private int id;
	private int parentNodeId;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "child_node_id")
	private Nodes nodes;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "vocabulary_id")
	private Vocabulary vocabulary;
	private int hadNextRelations;
	
	public Relations() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public Nodes getnodes() {
        return nodes;
    }
    public void setNodes(Nodes nodes) {
        this.nodes = nodes;
    }
	public int getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(int parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public Vocabulary getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(Vocabulary vocabulary) {
		this.vocabulary = vocabulary;
	}

	public int getHadNextRelations() {
		return hadNextRelations;
	}

	public void setHadNextRelations(int hadNextRelations) {
		this.hadNextRelations = hadNextRelations;
	}

}
