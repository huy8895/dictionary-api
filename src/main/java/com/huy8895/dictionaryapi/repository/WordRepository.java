package com.huy8895.dictionaryapi.repository;

import com.huy8895.dictionaryapi.enity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
