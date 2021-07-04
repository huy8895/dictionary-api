package com.huy8895.dictionaryapi.controller;

import com.huy8895.dictionaryapi.config.aop.RedisCache;
import com.huy8895.dictionaryapi.enity.Word;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import com.huy8895.dictionaryapi.repository.WordRepository;
import com.huy8895.dictionaryapi.service.RungWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rung-api")
@RequiredArgsConstructor
public class RungAPIController {
    private final WordRepository wordRepository;
    private final RungWordService rungWordService;

    @GetMapping("/{word}")
    @RedisCache
    public RungWord getRungWord(@PathVariable String word) {
        return rungWordService.getRungWord(word);
    }

    @GetMapping
    public List<RungWord> getRungWords() {
        return rungWordService.getList();
    }

    @PostMapping()
    public Word createRungWord(@RequestBody Word word) {
        return wordRepository.save(word);
    }



}
