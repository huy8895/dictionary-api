package com.huy8895.dictionaryapi.helper.imp;

import com.huy8895.dictionaryapi.helper.DictAbstract;
import com.huy8895.dictionaryapi.model.enums.HtmlTag;
import com.huy8895.dictionaryapi.model.rung.RungWord;
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

@Component
public class RungDict extends DictAbstract {
    Connection conn;
    Document doc;

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
            Elements content = doc.select("div.mw-content-ltr");
            result = getResult(content.select("span:has(font), h2:has(span), h3, h5:not(h5:has(font))"));
            result.setLinkAudio(doc.select("a.ms-s-icon.btnPlay.startPlay")
                                   .attr("data-link"));
            result.setWord(word);
        } catch (IOException | CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private RungWord getResult(Elements select_pronoun) throws CloneNotSupportedException {
        RungWord rungWord = new RungWord();
        ArrayList<Category> categories = new ArrayList<>();
        Category category = new Category();
        Part part = new Part();
        ArrayList<Part> parts = new ArrayList<>();
        ArrayList<String> means = new ArrayList<>();
        for (Element element : select_pronoun) {
            boolean isPronounce = element.tagName()
                                         .equals(HtmlTag.SPAN.getTag());
            boolean isCategory = element.tagName()
                                        .equals(HtmlTag.H2.getTag());
            boolean isPart = element.tagName()
                                    .equals(HtmlTag.H3.getTag());
            boolean isMean = element.tagName()
                                    .equals(HtmlTag.H5.getTag());

            if (isPronounce) {
                rungWord.setPronounce(element.text());
            }

            if (isCategory) {
                if (!parts.isEmpty()) {
                    if (!means.isEmpty()) {
                        part.setMeans((List<String>) means.clone());
                        means.clear();
                        parts.add(part.clone());
                    }
                    category.setParts((List<Part>) parts.clone());
                    parts.clear();
                    categories.add(category.clone());
                }
                category.setName(element.text());
            }

            if (isPart) {
                if (!means.isEmpty()) {
                    part.setMeans((List<String>) means.clone());
                    means.clear();
                    parts.add(part.clone());
                }
                part.setType(element.text());
            }
            if (isMean) {
                means.add(element.text());
            }
        }
        rungWord.setCategories(categories);
        return rungWord;
    }
}
