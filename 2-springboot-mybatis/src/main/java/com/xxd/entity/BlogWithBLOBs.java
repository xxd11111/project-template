package com.xxd.entity;

public class BlogWithBLOBs extends Blog {
    /**
     *  content
     */
    private String content;

    /**
     *  html_content
     */
    private String htmlContent;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }
}