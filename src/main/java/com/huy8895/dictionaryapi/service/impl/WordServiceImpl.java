package com.huy8895.dictionaryapi.service.impl;

import com.google.gson.Gson;
import com.huy8895.dictionaryapi.enity.Word;
import com.huy8895.dictionaryapi.repository.WordRepository;
import com.huy8895.dictionaryapi.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final String HASH_KEY = "Word";
    private final Gson gson;


    @Override
    public Word create(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public Word get(Long id) {
        log.info("get word in redis: {}", id );
        String o = (String) redisTemplate.opsForHash()
                                         .get(HASH_KEY, String.valueOf(id));
        if (o == null) {
            log.info("get word in wordRepository: {}", id );
            Optional<Word> repositoryOne = wordRepository.findById(id);
            if (repositoryOne.isPresent()){
                Word word = repositoryOne.get();
                String s = gson.toJson(word);
                redisTemplate.opsForHash()
                             .put(HASH_KEY, String.valueOf(id), s);
                redisTemplate.expire(HASH_KEY,10000, TimeUnit.MILLISECONDS);
                return word;
            }

        }

        return gson.fromJson(o,Word.class);
    }

    @Override
    public void delete(Word word) {

    }

    @Override
    public Word update(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public List<Word> getList() {
        return wordRepository.findAll();
    }

}
