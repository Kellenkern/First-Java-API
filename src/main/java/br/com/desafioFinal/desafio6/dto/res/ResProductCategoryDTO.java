package br.com.desafioFinal.desafio6.dto.res;

import br.com.desafioFinal.desafio6.model.ProductCategory;
import lombok.Getter;

@Getter
public class ResProductCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean isDelited;


    public ResProductCategoryDTO(ProductCategory productCategory) {
        this.name = productCategory.getName();
        this.description = productCategory.getDescription();
        this.isDelited = productCategory.getIsDelited();
        this.id = productCategory.getId();
    }

    public static ResProductCategoryDTO of(ProductCategory productCategory) {
        return productCategory == null ? null : new ResProductCategoryDTO(productCategory);
    }
}
