package com.example.siqa.service;

import com.example.siqa.model.da.Quote;
import com.example.siqa.model.dto.QuoteDto;
import com.example.siqa.model.endpoint.Response;

import java.util.List;

public interface QuoteService {
    Response createQuote(QuoteDto quote);
    Response getQuoteById(Long id);
    Response updateQuote(Long id, QuoteDto updatedQuote);
    Response deleteQuote(Long id);
    Response getAllQuotes();

    Response getBestQuotes();
}
