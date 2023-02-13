package br.com.desafioFinal.desafio6.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String office;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String photo;

    @Column(nullable = false)
    private Boolean isDelited = false;


    public User(String name, String office, Integer age, Integer role, String email, String username, String password, String telephone, String photo) {
        this.name = name;
        this.office = office;
        this.age = age;
        this.role = role;
        this.email = email;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.photo = photo;
    }


    @Override
    public String toString() {
        return "Cliente: Id="+id+", Nome="+name+", Office="+office+", Age="+age+", Email="+email+", Telefone="+telephone+", Username="+username+", Password="+password+", Role="+role+", Photo="+photo+";";
    }
}

