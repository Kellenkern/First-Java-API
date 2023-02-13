package br.com.desafioFinal.desafio6.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @ManyToOne
    private ProductCategory catProd;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @Column (nullable = false)
    private String img;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer barcode;

    @Column (nullable = false)
    private String brand;

    @Column(nullable = false)
    private Boolean isDelited = false;


    public Product(ProductCategory catProd, Integer quantity, String name, String img, String description, Integer barcode, String brand) {
        this.catProd = catProd;
        this.name = name;
        this.quantity = quantity;
        this.img = img;
        this.description = description;
        this.barcode = barcode;
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "Cliente: Id="+id+", Nome="+name+", Quantidade="+quantity+",Img="+img+", Categoria="+catProd+", Descrição="+description+", Código de Barras="+barcode+", Brand="+brand+";";
    }
}
