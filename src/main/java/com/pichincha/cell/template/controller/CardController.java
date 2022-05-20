package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.dto.CardDto;
import com.pichincha.cell.template.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints that manage the cards
 */
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController {

    private final CardService cardService;

    /**
     * Find a card by ID
     *
     * @param id ID of the card to be searched
     * @return Card found
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<CardDto> getCard(@PathVariable("id") Long id) {
        log.info("Endpoint to get an account by ID: id=" + id);
        return new ResponseEntity<>(cardService.getCardById(id), HttpStatus.OK);
    }

    /**
     * Get all cards
     *
     * @return All cards found
     */
    @GetMapping()
    public ResponseEntity<List<CardDto>> getAllCards() {
        log.info("Endpoint to get all cards");
        return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
    }

    /**
     * Save a card
     *
     * @param dto Object containing the new card information
     * @return New card created
     */
    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<CardDto> saveCard(@RequestBody CardDto dto) {
        log.info("Endpoint to save an card: data=" + dto);
        return new ResponseEntity<>(cardService.createCard(dto), HttpStatus.OK);
    }

    /**
     * Update a card
     *
     * @param id  ID of the card to be upgraded
     * @param dto Object containing card information to be updated
     * @return Card updated
     */
    @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<CardDto> updateCard(@PathVariable Long id, @RequestBody CardDto dto) {
        log.info("Endpoint to update a card: id=" + id + ", data=" + dto);
        return new ResponseEntity<>(cardService.updateCard(id, dto), HttpStatus.OK);
    }

    /**
     * Delete a card
     *
     * @param id Card ID to be deleted
     * @return ID of the card that was deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCard(@PathVariable Long id) {
        log.info("Endpoint to delete a card: id=" + id);
        return new ResponseEntity<>(cardService.deleteCardById(id), HttpStatus.OK);
    }
}
