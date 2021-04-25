package com.huy8895.dictionaryapi.model;


import com.huy8895.dictionaryapi.model.enums.LanguageType;

import java.util.List;

public abstract class Word {
    private String type;
    private List<String> senses;
    private String trans;
    private String example;
    private String pronunciation;
    private LanguageType languageType;
}
