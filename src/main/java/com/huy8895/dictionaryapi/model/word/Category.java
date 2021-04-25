package com.huy8895.dictionaryapi.model.word;

import lombok.Data;

import java.util.List;

@Data
public class Category implements Cloneable{
    private String name;
    private List<Part> parts;

    @Override
    public Category clone() throws CloneNotSupportedException {
        return (Category) super.clone();
    }
}
