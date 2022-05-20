package com.pichincha.cell.template.service.impl;

import com.pichincha.cell.template.domain.Card;
import com.pichincha.cell.template.domain.dto.CardDto;
import com.pichincha.cell.template.repository.CardRepository;
import com.pichincha.cell.template.service.CardService;
import com.pichincha.cell.template.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public CardDto createCard(CardDto data) {
        Card card = Mapper.modelMapper().map(data, Card.class);
        return Mapper.modelMapper().map(cardRepository.save(card), CardDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardDto updateCard(Long id, CardDto data) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty()) {
            resourceNotFound(id);
        }
        cardOptional.get().setExpirationDate(data.getExpirationDate());
        cardOptional.get().setNumber(data.getNumber());
        return Mapper.modelMapper().map(cardRepository.save(cardOptional.get()), CardDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardDto getCardById(Long id) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty()) {
            resourceNotFound(id);
        }
        return Mapper.modelMapper().map(cardOptional.get(), CardDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CardDto> getAllCards() {
        List<CardDto> list = new ArrayList<>();
        for (Card element : cardRepository.findAll()) {
            list.add(Mapper.modelMapper().map(element, CardDto.class));
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long deleteCardById(Long id) {
        Optional<Card> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty()) {
            resourceNotFound(id);
        }
        cardRepository.deleteById(id);
        return id;
    }

    private void resourceNotFound(Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Card with id %d not found", id));
    }
}
