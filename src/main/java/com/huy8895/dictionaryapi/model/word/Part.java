package com.huy8895.dictionaryapi.model.word;

import lombok.Data;

import java.util.List;

@Data
public class Part implements Cloneable{
    private String type;
    private List<String> means;

    @Override
    protected Part clone() throws CloneNotSupportedException {
        return (Part) super.clone();
    }
}
