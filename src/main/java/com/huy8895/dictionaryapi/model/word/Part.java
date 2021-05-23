package com.huy8895.dictionaryapi.model.word;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Part implements Cloneable, Serializable {
    private static final long serialVersionUID = -1793929853454908689L;
    private String type;
    private List<String> means;

    @Override
    public Part clone() throws CloneNotSupportedException {
        return (Part) super.clone();
    }
}
