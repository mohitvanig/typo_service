package com.vanistudios.typo.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanistudios.typo.entity.Message;

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
public class MessageDTO {
	
	private Long id;
    private String text;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long room_id;
    
    @JsonProperty(value = "listNoteItems")
    private List<ListNoteItemDTO> listNoteItemDTO;

    public MessageDTO(Message msg) {
        this.text = msg.getText();
        this.createdAt = msg.getCreatedAt();
        this.updatedAt = msg.getUpdatedAt();
        this.room_id = msg.getRoom().getId();
    }

    public MessageDTO(Message msg, List<ListNoteItemDTO> listitems) {
        this.listNoteItemDTO = listitems;
    }
}
