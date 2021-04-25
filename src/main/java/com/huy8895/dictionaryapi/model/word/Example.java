package com.huy8895.dictionaryapi.model.word;

import lombok.Data;

@Data
public class Example implements Cloneable{
    private String origin;
    private String translate;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
