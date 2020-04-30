package com.david.springbootboard.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@ToString
public class PageDto {
    private Page page;
    private Integer startPage;
    private Integer endPage;
    private Integer currentPage;
    private Integer amount;
    private boolean prev, next;
    private String type;
    private String keyword;

    public PageDto(Page page, String type, String keyword) {
        this.page = page;
        this.currentPage = page.getNumber() + 1;
        int totalElements = page.getNumberOfElements();
        this.amount = page.getSize();
        this.type = type;
        this.keyword = keyword;


        this.endPage = (int) (Math.ceil(currentPage / 10.0)) * 10;
        this.startPage = this.endPage - 9;
        int realEnd = page.getTotalPages();
        if (realEnd < this.endPage) {
            this.endPage = realEnd;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;
    }
}
