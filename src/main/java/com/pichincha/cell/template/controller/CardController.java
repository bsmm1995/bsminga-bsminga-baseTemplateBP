package com.pichincha.cell.template.controller;

import com.pichincha.cell.template.domain.base.ResponseDto;
import com.pichincha.cell.template.domain.dto.CardDto;
import com.pichincha.cell.template.service.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<ResponseDto> getCard(@PathVariable("id") Long id) {
        log.info("Endpoint to get an account by ID: id=" + id);
        return new ResponseEntity<>(new ResponseDto(cardService.getCardById(id), "Record found"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Get all cards
     *
     * @return All cards found
     */
    @GetMapping()
    public ResponseEntity<ResponseDto> getAllCards() {
        log.info("Endpoint to get all cards");
        List<CardDto> result = cardService.getAllCards();
        return new ResponseEntity<>(new ResponseDto(result, String.format("%d Records found", result.size())), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Save a card
     *
     * @param dto Object containing the new card information
     * @return New card created
     */
    @PostMapping(headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<ResponseDto> saveCard(@RequestBody CardDto dto) {
        log.info("Endpoint to save an card: data=" + dto);
        return new ResponseEntity<>(new ResponseDto(cardService.createCard(dto)), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Update a card
     *
     * @param id  ID of the card to be upgraded
     * @param dto Object containing card information to be updated
     * @return Card updated
     */
    @PutMapping(value = "/{id}", headers = "Accept=application/json;charset=UTF-8")
    public ResponseEntity<ResponseDto> updateCard(@PathVariable Long id, @RequestBody CardDto dto) {
        log.info("Endpoint to update a card: id=" + id + ", data=" + dto);
        return new ResponseEntity<>(new ResponseDto(cardService.updateCard(id, dto)), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * Delete a card
     *
     * @param id Card ID to be deleted
     * @return ID of the card that was deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteCard(@PathVariable Long id) {
        log.info("Endpoint to delete a card: id=" + id);
        return new ResponseEntity<>(new ResponseDto(cardService.deleteCardById(id)), new HttpHeaders(), HttpStatus.OK);
    }
}
