package com.java.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.chatbot.model.Nodes;

@Repository
public interface NodesRepossitory extends JpaRepository<Nodes, Long>{
	public Nodes findById(Long id);
	public Nodes findByNode(String node);
}
