package com.alkemy.ong.domain;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OngPage<T> {
    public static final String BASE_URL = "http://localhost:8080";
    private String previous;
    private String next;
    private List<T> body;
    private String resource;
    public void setNextPage(int numberPage, int totalPages) {
        if(numberPage < totalPages) {
            this.next = buildUrl() + (numberPage + 1) ;
        } else {
            this.next = null;
        }
    }
    public void setPreviousPage(int numberPage) {
        if(numberPage > 0){
            this.previous = buildUrl() + (numberPage -1);
        } else {
            this.previous = null;
        }
    }

    private String buildUrl (){
        return BASE_URL + this.resource +  "?page=";
    }
}
