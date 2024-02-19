package com.vanistudios.typo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanistudios.typo.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	List<Room> findByUserId(Long userId);
}
