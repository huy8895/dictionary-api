package com.huy8895.dictionaryapi.controller;

import com.huy8895.dictionaryapi.enity.Word;
import com.huy8895.dictionaryapi.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-word")
@RequiredArgsConstructor
public class MyWordController {
    private final WordService wordService;


    @PostMapping
    public Word create(@RequestBody Word word) {
        return wordService.create(word);
    }

    @GetMapping
    public List<Word> getWords() {
        return wordService.getList();
    }

    @GetMapping("/{id}")
    public Word getWord(@PathVariable Long id){
        return wordService.get(id);
    }

    @PutMapping
    public Word updateWord(@RequestBody Word word) {
        return wordService.update(word);
    }

}
