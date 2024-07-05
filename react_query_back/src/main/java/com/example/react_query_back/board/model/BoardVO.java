package com.example.react_query_back.board.model;

import lombok.Data;

@Data
public class BoardVO {
    private int idx;
    private String title;
    private String content;
    private String createWriter;
    private String updateWriter;
    private String createDt;
    private String updateDt;
}
