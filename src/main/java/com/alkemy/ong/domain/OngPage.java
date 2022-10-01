package com.alkemy.ong.domain;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OngPage<T> {
    public static final String BASE_URL = "http://localhost:8080";
    private String previousPage;
    private String nextPage;
    private List<T> body;

    public OngPage(String previousPage, String nextPage, List<T> body) {
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.body = body;
    }

    public static class OngPageBuilder<T> {
        private List<T> body;
        private int pageNumber;
        private int totalPages;
        private String resource;

        public OngPageBuilder(String resource) {
            this.resource = resource;
        }

        public OngPageBuilder body(List<T> body) {
            this.body = body;
            return this;
        }

        public OngPageBuilder pageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public OngPageBuilder totalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public OngPage<T> build() {
            return new OngPage<T>(
                    getPreviousPage(),
                    getNextPage(),
                    this.body);
        }

        private String getNextPage() {
            if(pageNumber < totalPages - 1) {
                return  buildUrl() + (pageNumber + 1) ;
            }
            return null;
        }
        private String getPreviousPage() {
            if(pageNumber > 0 && pageNumber < totalPages - 1){
                return buildUrl() + (pageNumber -1);
            }
            return null;
        }

        private String buildUrl (){
            return BASE_URL + this.resource +  "?page=";
        }
    }
}
