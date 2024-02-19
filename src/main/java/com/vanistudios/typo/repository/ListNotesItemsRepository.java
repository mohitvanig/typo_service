package com.vanistudios.typo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vanistudios.typo.entity.ListNotesItems;

public interface ListNotesItemsRepository extends JpaRepository<ListNotesItems, Long> {
    // Custom query methods or additional operations can be added here
}

