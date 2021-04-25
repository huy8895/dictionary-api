package com.huy8895.dictionaryapi.helper;

import com.huy8895.dictionaryapi.model.rung.RungWord;
import lombok.Data;

@Data
public abstract class DictAbstract implements SearchHelper<RungWord> {
    protected String url;

}
