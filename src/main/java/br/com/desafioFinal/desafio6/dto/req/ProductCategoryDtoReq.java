package br.com.desafioFinal.desafio6.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryDtoReq {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
