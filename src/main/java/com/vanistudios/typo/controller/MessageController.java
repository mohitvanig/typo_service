package com.vanistudios.typo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.vanistudios.typo.dto.ListNoteItemDTO;
import com.vanistudios.typo.dto.MessageDTO;
import com.vanistudios.typo.entity.ListNotesItems;
import com.vanistudios.typo.entity.Message;
import com.vanistudios.typo.entity.Room;
import com.vanistudios.typo.repository.MessageRepository;
import com.vanistudios.typo.repository.RoomRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private RoomRepository roomRepository;

	// Create a new message
	@PostMapping("/messages")
	public Object createMessage(@RequestBody Message message) throws Exception {
		
		message.setId(0L);
		Long roomId = message.getRoom().getId();
		Room room = roomRepository.findById(roomId)
				.orElseThrow(() -> new Exception("Room not found for this id :: " + roomId));
		message.setRoom(room);
		message.getListNotesItems().forEach(n->n.setMessage(message));
		Message msg = messageRepository.save(message);
		List<ListNotesItems> items = msg.getListNotesItems();
		if(items!=null) {
			List<ListNoteItemDTO> dtoItems = new ArrayList<>(items.size());
			for (ListNotesItems item : items) {
			    dtoItems.add(new ListNoteItemDTO(item));
			}
			return new MessageDTO(msg, dtoItems);
		}
		return new MessageDTO(msg);
	}

	// Get a message by id
	@GetMapping("/messages/{id}")
	@Operation(summary ="get message by providing message id")
	public Message getMessageById(@PathVariable(value = "id") Long messageId) throws Exception {
		return messageRepository.findById(messageId)
				.orElseThrow(() -> new Exception("Message not found for this id :: " + messageId));
	}

	// Update a message
	@PutMapping("/messages/{id}")
	public Message updateMessage(@PathVariable(value = "id") Long messageId, @RequestBody Message messageDetails)
			throws Exception {
		Message message = messageRepository.findById(messageId)
				.orElseThrow(() -> new Exception("Message not found for this id :: " + messageId));
		message.setText(messageDetails.getText());
		message.setUpdatedAt(LocalDate.now());
		return messageRepository.save(message);
	}

	// Delete a message
	@DeleteMapping("/messages/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable(value = "id") Long messageId) throws Exception {
		Message message = messageRepository.findById(messageId)
				.orElseThrow(() -> new Exception("Message not found for this id :: " + messageId));
		messageRepository.delete(message);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/rooms/{roomId}/messages")
	@Operation(summary ="get all messages of a room by providing room id.")
	@Deprecated
	public Object getMessagesByRoomId(@PathVariable(value = "roomId") Long roomId) {
		List<Message> msgs = messageRepository.findByroomId(roomId);
	    List<MessageDTO> msgDTOs = new ArrayList<MessageDTO>();
	    for (Message msg : msgs) {
	    	msgDTOs.add(new MessageDTO(msg));
	    }
	    return ResponseEntity.ok().body(msgDTOs);
	}
	
	
	//currently being used
	@GetMapping("/roomx/{roomId}/messages")
	public Object getMessagesByRoomIdx(@PathVariable(value = "roomId") Long roomId) {
		List<Message> msgs = messageRepository.findAllByRoomIdOrderByUpdatedAtDesc(roomId);
	    List<MessageDTO> msgDTOs = new ArrayList<MessageDTO>();
	    for (Message msg : msgs) {
	    	List<ListNoteItemDTO> listitems = new ArrayList<ListNoteItemDTO>();
	    	msg.getListNotesItems().forEach(n->listitems.add(new ListNoteItemDTO(n)));
	    	msgDTOs.add(new MessageDTO(msg,listitems));
	    }
	    return ResponseEntity.ok().body(msgDTOs);
	}

}
