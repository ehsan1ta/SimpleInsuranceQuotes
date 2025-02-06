package com.example.siqa.controller;

import com.example.siqa.constants.ResponseMessages;
import com.example.siqa.model.da.Quote;
import com.example.siqa.model.dto.QuoteDto;
import com.example.siqa.model.endpoint.Response;
import com.example.siqa.service.QuoteService;
import com.example.siqa.utils.BindingResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    BindingResultUtil bindingResultUtil;

    @PostMapping
    public ResponseEntity<Response> createQuote(@RequestBody @Validated QuoteDto quote , BindingResult bindingResult ) {

        {
            String bindingResultMessage =  bindingResultUtil.getErrors(bindingResult);
            if (bindingResultMessage != null) {
                return Response.getBadRequest(ResponseMessages.VALIDATION_FAILED.message , bindingResultMessage);
            } else {
                return Response.getOk(ResponseMessages.SUCCESSFUL.message, quoteService.createQuote(quote));
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getQuoteById(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.getQuoteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateQuote(@PathVariable Long id, @RequestBody @Validated QuoteDto updatedQuote) {
        return ResponseEntity.ok(quoteService.updateQuote(id, updatedQuote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteQuote(@PathVariable Long id) {
        return ResponseEntity.ok(quoteService.deleteQuote(id));
    }

    @GetMapping("/aggregate")
    public ResponseEntity<Response> getBestQuotes() {
        return ResponseEntity.ok(quoteService.getBestQuotes());
    }

    @GetMapping
    public ResponseEntity<Response> getAllQuotes() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }
}
