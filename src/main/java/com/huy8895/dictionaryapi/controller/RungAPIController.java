package com.huy8895.dictionaryapi.controller;

import com.huy8895.dictionaryapi.enity.Word;
import com.huy8895.dictionaryapi.helper.DictAbstract;
import com.huy8895.dictionaryapi.helper.imp.RungDict;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import com.huy8895.dictionaryapi.repository.WordRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rung-api/")
public class RungAPIController {
    private final DictAbstract rungDict;
    private final WordRepository wordRepository;

    public RungAPIController(RungDict rungDict, WordRepository wordRepository) {
        this.rungDict = rungDict;
        this.wordRepository = wordRepository;
    }

    @GetMapping("{word}")
    public RungWord getRungWord(@PathVariable String word){
        return rungDict.search(word);
    }

    @PostMapping()
    public Word createRungWord (@RequestBody Word word){
        return wordRepository.save(word);
    }

}
