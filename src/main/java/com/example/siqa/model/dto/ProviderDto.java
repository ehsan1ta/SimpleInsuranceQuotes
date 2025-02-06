package com.example.siqa.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
     * DTO for {@link com.example.siqa.model.da.Provider}
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    public class ProviderDto implements Serializable {
        private Long id;
        private String name;
        private List<QuoteDto> quoteList;
        
    }