package com.vanistudios.typo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanistudios.typo.dto.RoomDTO;
import com.vanistudios.typo.entity.Room;
import com.vanistudios.typo.repository.RoomRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	RoomRepository roomRepository;

	@PostMapping("/create")
	@Operation(summary = "create room by giving room details")
	public Room createRoom(@RequestBody Room room) {
		return roomRepository.save(room);
	}

	@GetMapping("/all")
	@Operation(summary = "get all rooms")
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	@GetMapping("/{id}")
	@Operation(summary = "get room by providing room id")
	public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId) throws Exception {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new Exception("Room not found for this id :: " + roomId));
		return ResponseEntity.ok().body(room);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long roomId, @RequestBody Room roomDetails)
			throws Exception {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new Exception("Room not found for this id :: " + roomId));

		room.setRoom_name(roomDetails.getRoom_name());
		room.setCreatedAt(roomDetails.getCreatedAt());
		room.setUpdatedAt(roomDetails.getUpdatedAt());
		room.setUser(roomDetails.getUser());
		final Room updatedRoom = roomRepository.save(room);
		return ResponseEntity.ok(updatedRoom);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId) throws Exception {
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new Exception("Room not found for this id :: " + roomId));

		roomRepository.delete(room);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/userid/{userid}")
	@Operation(summary = "get all rooms of a user by providing userid")
	public Object getRoomByUserId(@PathVariable(value = "userid") Long userId) throws Exception {
		List<Room> rooms = roomRepository.findByUserId(userId);
		List<RoomDTO> roomDTOs = new ArrayList<>();
		for (Room room : rooms) {
			roomDTOs.add(new RoomDTO(room));
		}
		return ResponseEntity.ok().body(roomDTOs);
	}

}
