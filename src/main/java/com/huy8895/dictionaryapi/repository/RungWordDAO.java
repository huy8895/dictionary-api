package com.huy8895.dictionaryapi.repository;

import com.google.gson.Gson;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RungWordDAO {
    public static final String KEY = "RungWord";
    private final RedisTemplate<String, String> template;
    private final Gson gson;

    public RungWord save(RungWord rungWord) {
        template.opsForHash()
                .put(KEY, rungWord.getWord(), gson.toJson(rungWord));
        return rungWord;
    }

    public List<RungWord> findAllInRedis() {
        return template.opsForHash()
                       .values(KEY)
                       .stream()
                       .map(o -> gson.fromJson((String) o, RungWord.class))
                       .collect(Collectors.toList());
    }

    public RungWord findByWord(String word) {
        return gson.fromJson((String) template.opsForHash()
                                       .get(KEY, word), RungWord.class);
    }

    public Long deleteWord(String word) {
        return template.opsForHash()
                       .delete(KEY, word);
    }

}
