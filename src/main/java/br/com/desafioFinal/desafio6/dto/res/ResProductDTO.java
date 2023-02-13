package br.com.desafioFinal.desafio6.dto.res;

import br.com.desafioFinal.desafio6.model.Product;
import lombok.Getter;

@Getter
public class ResProductDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private String img;
    private String description;
    private Integer barcode;
    private String brand;


    public ResProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
        this.img = product.getImg();
        this.description = product.getDescription();
        this.barcode = product.getBarcode();
        this.brand = product.getBrand();
    }

    public static ResProductDTO of(Product product) {
        return product == null ? null : new ResProductDTO(product);
    }

}
