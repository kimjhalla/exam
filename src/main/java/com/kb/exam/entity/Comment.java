package com.kb.exam.entity;

import java.time.LocalDateTime;

public class Comment {
    private long seq;
    private long postSeq;
    private long userSeq;
    private String comment;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
}
