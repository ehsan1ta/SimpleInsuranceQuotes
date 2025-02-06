package com.example.siqa.model.dto;

import com.example.siqa.constants.CoverageType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.example.siqa.model.da.Quote}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class QuoteDto implements Serializable {
    private Long id;

    private CoverageType coverageType;

    @DecimalMin(value = "0.0", message = "Value must be greater than or equal to 0.0")
    @DecimalMax(value = "1000.0", message = "Value must be less than or equal to 1000.0")
//    @Pattern(regexp = "-?\\d+(\\.\\d+)?", message = "Value must be a valid number")
    private Double price;
    private ProviderDto providerDto;

}