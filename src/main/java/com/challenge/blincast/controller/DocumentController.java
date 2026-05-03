package com.challenge.blincast.controller;

import com.challenge.blincast.dto.DocumentRequest;
import com.challenge.blincast.dto.DocumentResponse;
import com.challenge.blincast.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
@CrossOrigin(origins = "*")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentResponse> handleDocument(
            @RequestBody DocumentRequest request) {

        // Validação dos campos obrigatórios
        if (request.action() == null || request.action().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(DocumentResponse.badRequest());
        }

        if (request.key() == null || request.key().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(DocumentResponse.badRequest());
        }

        // Roteamento baseado na ação
        return switch (request.action().toLowerCase()) {
            case "create" -> handleCreate(request);
            case "update" -> handleUpdate(request);
            case "delete" -> handleDelete(request);
            default -> ResponseEntity.badRequest()
                    .body(DocumentResponse.badRequest());
        };
    }

    private ResponseEntity<DocumentResponse> handleCreate(DocumentRequest request) {
        if (request.value() == null || request.value().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(DocumentResponse.badRequest());
        }

        documentService.create(request.key(), request.value());
        return ResponseEntity.ok(DocumentResponse.ok());
    }

    private ResponseEntity<DocumentResponse> handleUpdate(DocumentRequest request) {
        if (request.value() == null || request.value().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(DocumentResponse.badRequest());
        }

        documentService.update(request.key(), request.value());
        return ResponseEntity.ok(DocumentResponse.ok());
    }

    private ResponseEntity<DocumentResponse> handleDelete(DocumentRequest request) {
        documentService.delete(request.key());
        return ResponseEntity.ok(DocumentResponse.ok());
    }
}