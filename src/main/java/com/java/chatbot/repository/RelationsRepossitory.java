package com.java.chatbot.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.chatbot.model.Relations;

@Repository
public interface RelationsRepossitory extends JpaRepository<Relations, Long>{
	public ArrayList<Relations> findByParentNodeId(int id);
}
