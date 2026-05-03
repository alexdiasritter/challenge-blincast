package com.challenge.blincast.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @Column(name = "doc_key", length = 255, nullable = false, unique = true)
    private String key;

    @Column(name = "doc_value", length = 1000, nullable = false)
    private String value;

    // Construtor padrão (obrigatório para o JPA/Hibernate)
    public DocumentEntity() {
    }

    public DocumentEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    // Getters e Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentEntity that = (DocumentEntity) o;
        return key != null && key.equals(that.key);
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DocumentEntity{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}