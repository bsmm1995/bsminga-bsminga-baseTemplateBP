package com.pichincha.cell.template.service;

import com.pichincha.cell.template.domain.dto.CardDto;

import java.util.List;

public interface CardService {
    /**
     * Save a card
     *
     * @param data Object containing the new card information
     * @return New card created
     */
    CardDto createCard(CardDto data);

    /**
     * Update a card
     *
     * @param id   ID of the card to be upgraded
     * @param data Object containing card information to be updated
     * @return Card updated
     */
    CardDto updateCard(Long id, CardDto data);

    /**
     * Find a card by ID
     *
     * @param id ID of the card to be searched
     * @return Card found
     */
    CardDto getCardById(Long id);

    /**
     * Get all cards
     *
     * @return All cards found
     */
    List<CardDto> getAllCards();

    /**
     * Delete a card
     *
     * @param id Card ID to be deleted
     * @return ID of the card that was deleted
     */
    Long deleteCardById(Long id);
}
