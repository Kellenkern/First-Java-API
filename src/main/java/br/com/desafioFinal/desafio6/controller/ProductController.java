package br.com.desafioFinal.desafio6.controller;

import br.com.desafioFinal.desafio6.dto.req.ProductDtoReq;
import br.com.desafioFinal.desafio6.dto.res.ResProductDTO;
import br.com.desafioFinal.desafio6.service.CrudProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProductController {

    @Autowired
    private CrudProductService productService;

    @PostMapping("/create")
    public ResProductDTO AddProduct (@RequestBody @Valid ProductDtoReq productDtoReq) {
        return productService.AddProduct(productDtoReq);
    }

    @GetMapping
    public Page<ResProductDTO> SeeProduct (@RequestParam (required = false) String name, Pageable pagination) {

        if(name == null) {
            return productService.SeeProduct(pagination);
        } else{
            return productService.FindByName(name, pagination );
        }
    }

    @GetMapping ("/{id}")
    public ResProductDTO FindById(@PathVariable Long id) {
        return productService.FindById(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResProductDTO UpdatedProduct(@PathVariable Long id, @RequestBody @Valid ProductDtoReq productDtoReq) {
        return this.productService.UpdatedProduct(id, productDtoReq);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public void DeleteProduct(@PathVariable Long id) {
        productService.DeleteProduct(id);
    }
}
