package com.huy8895.dictionaryapi.model.word;

import lombok.Data;


@Data
public class Mean implements Cloneable{
    String mean;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
