package com.huy8895.dictionaryapi.model.rung;

import com.huy8895.dictionaryapi.model.word.Category;
import lombok.*;

import java.util.List;

@Data
public class RungWord {
    private String pronounce;
    private String linkAudio;
    private List<Category> categories;

    RungWord addWordType(Category category) {
        this.categories.add(category);
        return this;
    }
}
