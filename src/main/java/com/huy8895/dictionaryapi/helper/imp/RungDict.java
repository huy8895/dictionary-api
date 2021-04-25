package com.huy8895.dictionaryapi.helper.imp;

import com.huy8895.dictionaryapi.helper.DictAbstract;
import com.huy8895.dictionaryapi.model.enums.HtmlTag;
import com.huy8895.dictionaryapi.model.enums.Type;
import com.huy8895.dictionaryapi.model.rung.RungWord;
import com.huy8895.dictionaryapi.model.word.Example;
import com.huy8895.dictionaryapi.model.word.Mean;
import com.huy8895.dictionaryapi.model.word.Category;
import com.huy8895.dictionaryapi.model.word.Part;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Component
public class RungDict extends DictAbstract {
    Connection conn;
    Document doc;

    private static final String COMMON_WORD = "Thông dụng";

    @Autowired
    public RungDict(@Value("${dictionary.api.rung}") String url) {
        this.url = url;
    }

    @Override
    public RungWord search(String word) {
        return connect(word);
    }

    private RungWord connect(String word) {
        RungWord result = new RungWord();
        conn = Jsoup.connect(this.url + word);
        try {
            doc = conn.get();
            ArrayList<String> listIp = new ArrayList<>();
            Elements content = doc.select("div.mw-content-ltr");
            result = printSelect(content.select("span:has(font), h2:has(span), h3, h5:not(h5:has(font))"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private RungWord printSelect(Elements select_pronoun) {
        RungWord rungWord = new RungWord();
        ArrayList<Category> categories = new ArrayList<>();
        if (select_pronoun.size() == 0) {
            System.out.println("khong tim thay ------------------------> 0 ");
        }
        select_pronoun.forEach(element -> System.out.println(element.tagName() + " -- " + element.text()));
        Category category = new Category();
        Part part = new Part();
        ArrayList<Part> parts = new ArrayList<>();
//        Mean mean = new Mean();
//        ArrayList<Mean> means = new ArrayList<>();
        ArrayList<String> means = new ArrayList<>();
        for (Element element : select_pronoun) {
            if (element.tagName()
                       .equals(HtmlTag.SPAN.getTag())) {
                rungWord.setPronounce(element.text());
            }
            if (element.tagName()
                       .equals(HtmlTag.H2.getTag())) {
                if (parts.size() > 0) {
                    if (means.size() > 0) {
                        part.setMeans(means);
                        means = new ArrayList<>();
                        parts.add(part);
                    }
                    category.setParts(parts);
                    parts = new ArrayList<>();
                    categories.add(category);
                    category = new Category();
                }
                category.setName(element.text());
            }
            if (element.tagName()
                       .equals(HtmlTag.H3.getTag())) {
                if (means.size() > 0) {
                    part.setMeans(means);
                    means = new ArrayList<>();
                    parts.add(part);
                    part = new Part();
                }
                part.setType(element.text());

            }
            if (element.tagName()
                       .equals(HtmlTag.H5.getTag())) {
                means.add(element.text());
            }
        }
        rungWord.setCategories(categories);
        return rungWord;
    }
}
