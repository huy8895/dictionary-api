package com.huy8895.dictionaryapi.service;

import com.huy8895.dictionaryapi.model.rung.RungWord;

public interface RungWordService extends BaseService<RungWord>{
    RungWord getRungWord(String word);
}
