package br.com.desafioFinal.desafio6.dto.req;

import br.com.desafioFinal.desafio6.model.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoReq {

    private Long idCatProd;
    @NotBlank
    private String name;
    private Integer quantity;
    @NotBlank
    private String img;
    @NotBlank
    private String description;
    private Integer barcode;
    @NotBlank
    private String brand;

}
