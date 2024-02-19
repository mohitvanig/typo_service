package com.vanistudios.typo.dto;

import java.time.LocalDate;

import com.vanistudios.typo.entity.Room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
	
	   	private Long id;
	    private String room_name;
	    private LocalDate createdAt;
	    private LocalDate updatedAt;
	    private Long user_id;

	    public RoomDTO(Room room) {
	        this.id = room.getId();
	        this.room_name = room.getRoom_name();
	        this.createdAt = room.getCreatedAt();
	        this.updatedAt = room.getUpdatedAt();
	        this.user_id = room.getUser().getId();
	    }

		
}
