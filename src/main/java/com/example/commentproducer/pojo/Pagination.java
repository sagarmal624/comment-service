package com.example.commentproducer.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Pagination {
    private int totalPages;
    private long totalElements;
    private int size;
    private List items;
}
