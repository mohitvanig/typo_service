package com.vanistudios.typo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vanistudios.typo.entity.ListNotesItems;

public class ListNoteItemDTO {

	private Long id;
	
	@JsonIgnoreProperties
	private Long message_id;
	
	private String content;
    private Boolean isChecked;
    private String listType;
    

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Long message_id) {
		this.message_id = message_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public ListNoteItemDTO(Long id, Long message_id, String content, Boolean isChecked, String listType) {
		super();
		this.id = id;
		this.message_id = message_id;
		this.content = content;
		this.isChecked = isChecked;
		this.listType = listType;
	}
	
	public ListNoteItemDTO(ListNotesItems items) {
		super();
		this.message_id = items.getMessage().getId();
		this.id = items.getId();
		this.content = items.getContent();
		this.isChecked = items.getIsChecked();
		this.listType = items.getList_type();
	}

	
    
}
