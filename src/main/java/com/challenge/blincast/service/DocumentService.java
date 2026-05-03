package com.challenge.blincast.service;

import com.challenge.blincast.entity.DocumentEntity;
import com.challenge.blincast.exception.KeyAlreadyExistsException;
import com.challenge.blincast.exception.KeyNotFoundException;
import com.challenge.blincast.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public void create(String key, String value) {
        if (documentRepository.existsById(key)) {
            throw new KeyAlreadyExistsException(key);
        }

        DocumentEntity entity = new DocumentEntity(key, value);
        documentRepository.save(entity);
    }

    public void update(String key, String value) {
        DocumentEntity entity = documentRepository.findById(key)
                .orElseThrow(() -> new KeyNotFoundException(key));

        entity.setValue(value);
        documentRepository.save(entity);
    }

    public void delete(String key) {
        if (!documentRepository.existsById(key)) {
            throw new KeyNotFoundException(key);
        }

        documentRepository.deleteById(key);
    }
}