package com.huy8895.dictionaryapi.controller;

import com.huy8895.dictionaryapi.helper.DictAbstract;
import com.huy8895.dictionaryapi.helper.imp.RungDict;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rung-api/")
public class RungAPIController {
    private final DictAbstract rungDict;

    public RungAPIController(RungDict rungDict) {
        this.rungDict = rungDict;
    }

    @GetMapping("{word}")
    public RungWord getRungWord(@PathVariable String word){
        return rungDict.search(word);
    }

}
