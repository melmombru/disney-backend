package com.example.disney.service;

import com.example.disney.exception.CharacterException;
import com.example.disney.model.DisneyCharacter;
import com.example.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    public DisneyCharacter loadCharacter(DisneyCharacter character) {
        validateFields(character);
        return characterRepository.save(character);
    }

    public DisneyCharacter modifyCharacter(DisneyCharacter disneyCharacter) {
        findByCode(disneyCharacter.getCode());
        return characterRepository.save(disneyCharacter);
    }

    public DisneyCharacter findByCode(Long code) {
        return characterRepository.findByCode(code).orElseThrow(() ->
                new CharacterException());
    }
    public void deleteCharacter(Long code) {
        DisneyCharacter disneyCharacter = findByCode(code);
        characterRepository.delete(disneyCharacter);
    }

    private void validateFields(DisneyCharacter character) {
        if (character.getName().isEmpty() || character.getAge() < 0) {
            throw new CharacterException();
        }
    }
}
