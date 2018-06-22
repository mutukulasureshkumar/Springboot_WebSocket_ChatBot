package com.java.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.chatbot.model.Vocabulary;

@Repository
public interface VocabularyRepossitory extends JpaRepository<Vocabulary, Long>{
	public Vocabulary findById(int id);
}
