package com.java.chatbot.repository;

//@Repository
public class ChatRedisRepository {/*

	private static final String KEY = "CHAT";
	@SuppressWarnings("unused")
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Chat> hashOperations;

    public ChatRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

	public void add(final Chat chat) {
		hashOperations.put(KEY, chat.getChatId(), chat);
	}

	public Chat findChat(final long chatId) {
		return (Chat) hashOperations.get(KEY, chatId);
	}
	
	public void update(final Chat chat) {
		add(chat);
	}
*/}
