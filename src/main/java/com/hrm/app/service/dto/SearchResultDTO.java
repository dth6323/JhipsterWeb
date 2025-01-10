package com.hrm.app.service.dto;

import java.util.List;
import java.util.Map;

public class SearchResultDTO {

    private String fileName;
    private String content;
    private String highlight;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
}
