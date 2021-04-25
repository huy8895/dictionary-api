package com.huy8895.dictionaryapi.helper;

import com.huy8895.dictionaryapi.model.enums.HtmlTag;
import com.huy8895.dictionaryapi.model.word.Category;
import com.huy8895.dictionaryapi.model.word.Part;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class ElementHelper {
    public Category convertElementH2(Element element){
        if (element.tagName().equals(HtmlTag.H2.getTag())) {
            Category category = new Category();
            category.setName(element.text());
            return category;
        }
        return null;
    }

    public Part convertElementH3(Element element){
        if (element.tagName().equals(HtmlTag.H3.getTag())) {
            Part part = new Part();

            return part;
        }
        return null;
    }


}
