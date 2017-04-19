package com.freeteam01.androidnewsgroupreader.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by marti on 4/19/17.
 */

public class NewsGroupArticle {
    private String articleID;
    private String subject;
    private String date;
    private String from;

    private List<String> references = new ArrayList<>();
    private HashMap<String, NewsGroupArticle> children = new HashMap<>();

    public NewsGroupArticle(String articleId, String subject, String date, String from) {
        this.articleID = articleId;
        this.subject = subject;
        this.date = date;
        this.from = from;
    }

    public String getArticleID() {
        return articleID;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public List<String> getReferences() {
        return references;
    }

    public void addReferences(String[] references) {
        this.references.addAll(Arrays.asList(references));
    }

    /**
     * Adds an Article to the Article Tree
     * @param ngArticle Article to add to the Article Tree
     * @throws IllegalArgumentException
     */
    public void addArticle(NewsGroupArticle ngArticle) {
        addArticle(ngArticle, 0);
    }

    private void addArticle(NewsGroupArticle ngArticle, int depth) {
        if (ngArticle.getReferences().get(depth).equals(articleID)) {
            if (ngArticle.getReferences().size() == depth + 1)
                children.put(ngArticle.getArticleID(), ngArticle);
            else {
                if (children.containsKey(ngArticle.getReferences().get(depth + 1)))
                    children.get(ngArticle.getReferences().get(depth + 1)).addArticle(ngArticle, depth + 1);
                else
                    throw new IllegalArgumentException("A intermediat node is missing");
            }
        } else {
            throw new IllegalArgumentException("The Reference is not the one it should be");
        }
    }
}
