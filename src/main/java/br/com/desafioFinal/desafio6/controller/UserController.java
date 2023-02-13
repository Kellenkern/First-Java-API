package br.com.desafioFinal.desafio6.controller;

import br.com.desafioFinal.desafio6.dto.req.UserDtoReq;
import br.com.desafioFinal.desafio6.dto.res.ResUserDTO;
import br.com.desafioFinal.desafio6.service.CrudUsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private CrudUsersService userService;

    @PostMapping("/create")
    public ResUserDTO AddClient (@RequestBody @Valid UserDtoReq userDtoReq) {
        return userService.AddClient(userDtoReq);
    }

    @GetMapping
    //http://localhost:8080/usuario?page=0&size=2&sort=name,asc Regra para a consulta
    public Page<ResUserDTO> SeeClient (@RequestParam (required = false) String name, @PageableDefault (size = 5) Pageable pagination) {

        if(name == null) {
            return userService.SeeClient(pagination);
        } else{
            return userService.FindByName(name, pagination );
        }
    }

    @GetMapping ("/{id}")
    public ResUserDTO FindById(@PathVariable Long id) {
        return userService.FindById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResUserDTO UpdatedClient(@PathVariable Long id, @RequestBody @Valid UserDtoReq userDtoReq) {
        return userService.UpdatedClient(id,userDtoReq);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public void DeleteClient(@PathVariable Long id) {
        this.userService.DeleteClient(id);
    }

}
