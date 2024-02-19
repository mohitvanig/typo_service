package com.vanistudios.typo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "text" , nullable = false)
    private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
    
    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ListNotesItems> listNotesItems;


	public Message(Long id, String text, Room room, LocalDate createdAt, LocalDate updatedAt) {
		super();
		this.id = id;
		this.text = text;
		this.room = room;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Message() {
		super();
	}


	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", room=" + room + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", listNotesItems=" + listNotesItems + "]";
	}


    
    
}
