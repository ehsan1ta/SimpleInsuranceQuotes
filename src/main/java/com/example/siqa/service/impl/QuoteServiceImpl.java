package com.example.siqa.service.impl;

import com.example.siqa.constants.ResponseMessages;
import com.example.siqa.model.da.Provider;
import com.example.siqa.model.da.Quote;
import com.example.siqa.model.dto.ProviderDto;
import com.example.siqa.model.dto.QuoteDto;
import com.example.siqa.model.endpoint.Response;
import com.example.siqa.model.repository.ProviderRepository;
import com.example.siqa.model.repository.QuoteRepository;
import com.example.siqa.service.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ProviderRepository providerRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    @CacheEvict(value = "quotes", allEntries = true)
    public Response createQuote(QuoteDto requestQuoteDto) {
        Optional<Provider> providerOptional = providerRepository.findById(requestQuoteDto.getProviderDto().getId());
        if (providerOptional.isPresent()) {
            // to build quote entity
            Quote quote = modelMapper.map(requestQuoteDto, Quote.class);
            quote.setProvider(providerOptional.get());
            if (quoteRepository.existsByProvider_IdAndCoverageType(requestQuoteDto.getProviderDto().getId() , requestQuoteDto.getCoverageType().name()))
                return new Response(HttpStatus.BAD_REQUEST , ResponseMessages.DATA_EXISTS.message, null);
            quote = quoteRepository.save(quote);
            // to map saved entity to dto
            QuoteDto responseQuoteDto = modelMapper.map(quote, QuoteDto.class);
            responseQuoteDto.setProviderDto(modelMapper.map(providerOptional.get(), ProviderDto.class));
            return new Response(HttpStatus.OK , ResponseMessages.SUCCESSFUL.message , responseQuoteDto);
        } else {
            return new Response(HttpStatus.BAD_REQUEST , "provider not found", null);
        }
    }

    @Override
    @Cacheable(value = "quotes", key = "#id")
    public Response getQuoteById(Long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        if (quote.getId() != null) {
            QuoteDto quoteDto =  modelMapper.map(quoteRepository.findById(id).orElse(null) , QuoteDto.class);
            quoteDto.setProviderDto(modelMapper.map(quote.getProvider(), ProviderDto.class));
            return new Response(HttpStatus.OK, ResponseMessages.SUCCESSFUL.message, quoteDto);
        }
        else
            return new Response(HttpStatus.BAD_REQUEST , ResponseMessages.NO_DATA_FOUND.message, null);

    }

    @Override
    @CacheEvict(value = "quotes", allEntries = true)
    public Response updateQuote(Long id, QuoteDto updatedQuote) {
        Optional<Provider> providerOptional = providerRepository.findById(updatedQuote.getProviderDto().getId());
        if (providerOptional.isEmpty()) {
            return new Response(HttpStatus.BAD_REQUEST , "provider not found", null);
        }

        QuoteDto output =  quoteRepository.findById(id).map(quote -> {
            quote.setCoverageType(updatedQuote.getCoverageType().name());
            quote.setPrice(updatedQuote.getPrice());
            quote.setProvider(providerOptional.get());
            return modelMapper.map(quoteRepository.save(quote), QuoteDto.class);
        }).orElse(null);

        if (output != null)
            return new Response(HttpStatus.OK , ResponseMessages.SUCCESSFUL.message , output);
        else
            return new Response(HttpStatus.BAD_REQUEST , ResponseMessages.NO_DATA_FOUND.message, null);

    }

    @Override
    @CacheEvict(value = "quotes", allEntries = true)
    public Response deleteQuote(Long id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
            return new Response(HttpStatus.OK , ResponseMessages.DELETED.message , null);
        }
        else
            return new Response(HttpStatus.BAD_REQUEST , ResponseMessages.NO_DATA_FOUND.message, null);
    }

    @Override
    public Response getAllQuotes() {
        List<QuoteDto> quoteDtos = quoteRepository.findAll().stream()
                .map(quote -> {
                    QuoteDto quoteDto = modelMapper.map(quote, QuoteDto.class);
                    quoteDto.setProviderDto(modelMapper.map(quote.getProvider(), ProviderDto.class));
                    return quoteDto;
                })
                .collect(Collectors.toList());
        return new Response(HttpStatus.OK , ResponseMessages.SUCCESSFUL.message, quoteDtos);
    }

    @Override
    @Cacheable(value = "bestQuotes")
    public Response getBestQuotes() {
        List<QuoteDto> quoteDtos =  quoteRepository.findAll().stream()
                .sorted(Comparator.comparing(Quote::getPrice))
                .toList().stream().map( quote -> {
                            QuoteDto quoteDto = modelMapper.map(quote, QuoteDto.class);
                            quoteDto.setProviderDto(modelMapper.map(quote.getProvider(), ProviderDto.class));
                            return quoteDto;
                        }
                ).toList();
        return new Response(HttpStatus.OK , ResponseMessages.SUCCESSFUL.message, quoteDtos);

    }
}