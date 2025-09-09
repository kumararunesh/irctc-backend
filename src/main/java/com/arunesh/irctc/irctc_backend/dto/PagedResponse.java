package com.arunesh.irctc.irctc_backend.dto;

import java.util.List;

public class PagedResponse <T>{

    List<T> content;
    int pageNumber;
    int size;
    Long totalElements;
    boolean last;

    
}
