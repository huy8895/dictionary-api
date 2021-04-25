package com.huy8895.dictionaryapi.model.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum Type {
    NOUN("Danh từ"),
    PRONOUN("Đại từ"),
    VERB("Động từ"),
    ADJECTIVE("Tính từ"),
    ADVERB("Trạng từ"),
    PREPOSITION("Giới từ"),
    CONJUNCTION("Liên từ"),
    INTERJECTION("Thán từ"),
    TRANSITIVE_VERB("Ngoại động từ");

    private final String vnMean;

    Type(String vnMean) {
        this.vnMean = vnMean;
    }

    public String getVnMean() {
        return vnMean;
    }

    public static Optional<Type> getType(String text) {
        for (Type type : values()) {
            if (type.getVnMean()
                    .equals(text.trim())) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }

    public static List<String> getVNWordType(){
        ArrayList<String> list = new ArrayList<>();
        for (Type type : values()) {
            list.add(type.getVnMean());
        }
        return list;
    }
}
