package com.huy8895.dictionaryapi.controller;

import com.huy8895.dictionaryapi.enity.Word;
import com.huy8895.dictionaryapi.helper.DictAbstract;
import com.huy8895.dictionaryapi.helper.imp.RungDict;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import com.huy8895.dictionaryapi.repository.WordRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/rung-api")
public class RungAPIController {
    private final DictAbstract rungDict;
    private final WordRepository wordRepository;

    public RungAPIController(RungDict rungDict, WordRepository wordRepository) {
        this.rungDict = rungDict;
        this.wordRepository = wordRepository;
    }

    @GetMapping("/{word}")
    public RungWord getRungWord(@PathVariable String word) {
        return rungDict.search(word);
    }

    @PostMapping()
    public Word createRungWord(@RequestBody Word word) {
        return wordRepository.save(word);
    }

    @GetMapping("/test/{word}")
    public String getWord(@PathVariable String word) {
        return word;
    }

    @GetMapping("/test")
    public Foo getFoo(
            @RequestParam("id") String id,
            @RequestParam("name") String name
    ) {
        return new Foo(Long.parseLong(id), name + "/api/rung-api");
    }

    @PostMapping("/test-stringList")
    public List<Foo> getFoos(@RequestBody List<String> stringList) throws CloneNotSupportedException {
        List<Foo> fooList = new ArrayList<>();
        Foo foo1 = new Foo();
        AtomicLong count = new AtomicLong(0);
        for (String fooName : stringList) {
            foo1.setId(count.getAndIncrement());
            foo1.setName(fooName);
            fooList.add(foo1.clone());
        }
        return fooList;
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Foo implements Serializable, Cloneable {
    private long id;

    private String name;

    @Override
    protected Foo clone() throws CloneNotSupportedException {
        return (Foo) super.clone();
    }
}