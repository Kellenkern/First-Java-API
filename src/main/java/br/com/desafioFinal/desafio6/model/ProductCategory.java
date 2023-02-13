package br.com.desafioFinal.desafio6.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.function.Supplier;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="categoriaProduto")
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private List<Product> products;

    @Column(nullable = false)
    private Boolean isDelited = false;


    public ProductCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Categoria: Id="+id+", Nome="+name+", Descrição="+description+";";
    }


}
