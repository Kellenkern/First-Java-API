package br.com.desafioFinal.desafio6.dto.req;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoReq {

    @NotBlank
    private String name;
    @NotBlank
    private String office;
    private Integer age;
    private Integer role;
    @NotBlank
    private String email;
    @NotBlank @Length(min = 5)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String telephone;
    @NotBlank
    private String photo;


}
