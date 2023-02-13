package br.com.desafioFinal.desafio6.dto.res;

import br.com.desafioFinal.desafio6.model.User;
import lombok.Getter;



@Getter
public class ResUserDTO {

    private Long id;
    private String name;
    private String office;
    private Integer age;
    private String email;
    private String username;
    private String password;
    private String telephone;
    private Boolean isDelited;
    private String photo;


    public ResUserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.office = user.getOffice();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.telephone = user.getTelephone();
        this.photo = user.getPhoto();
        this.isDelited = user.getIsDelited();
    }

    public static ResUserDTO of(User user) {
        return user == null ? null : new ResUserDTO(user);
    }
}

