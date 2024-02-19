package com.vanistudios.typo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "list_notes_items")
public class ListNotesItems {
	
	@Id
	@GeneratedValue (strategy  = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
	private Message message;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "checked")
	private Boolean isChecked;
	
	@Column(name = "list_type")
	private String list_type;

	public ListNotesItems(Long id, Message message, String content, Boolean isChecked, String list_type) {
		super();
		this.id = id;
		this.message = message;
		this.content = content;
		this.isChecked = isChecked;
		this.list_type = list_type;
	}

	public ListNotesItems() {
		super();
	}
	

}
