package com.vanistudios.typo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanistudios.typo.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	List<Message> findByroomId(Long roomId);
	
	List<Message> findAllByRoomIdOrderByUpdatedAtDesc(Long roomId);
	
}
