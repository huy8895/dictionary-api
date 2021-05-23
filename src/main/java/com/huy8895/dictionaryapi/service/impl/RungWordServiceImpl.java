package com.huy8895.dictionaryapi.service.impl;

import com.huy8895.dictionaryapi.helper.DictAbstract;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import com.huy8895.dictionaryapi.repository.RungWordDAO;
import com.huy8895.dictionaryapi.service.RungWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RungWordServiceImpl implements RungWordService {
    private final RungWordDAO rungWordDAO;
    private final DictAbstract rungDict;

    @Override
    public RungWord getRungWord(String word) {
        RungWord byWord = rungWordDAO.findByWord(word);
        if (byWord == null){
            RungWord search = rungDict.search(word);
            return rungWordDAO.save(search);
        }
        return byWord;
    }

    @Override
    public RungWord create(RungWord rungWord) {
        return null;
    }

    @Override
    public RungWord get(Long id) {
        return null;
    }

    @Override
    public void delete(RungWord rungWord) {

    }

    @Override
    public RungWord update(RungWord rungWord) {
        return null;
    }

    @Override
    public List<RungWord> getList() {
        return rungWordDAO.findAllInRedis();
    }
}
