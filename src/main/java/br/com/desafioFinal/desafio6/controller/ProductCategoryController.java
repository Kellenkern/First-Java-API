package br.com.desafioFinal.desafio6.controller;

import br.com.desafioFinal.desafio6.dto.req.ProductCategoryDtoReq;
import br.com.desafioFinal.desafio6.dto.res.ResProductCategoryDTO;
import br.com.desafioFinal.desafio6.service.CrudProductCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categoria")
public class ProductCategoryController {

    @Autowired
    private CrudProductCategoryService productCategoryService;

    @PostMapping("/create")
    public ResProductCategoryDTO AddProductCategory (@RequestBody @Valid ProductCategoryDtoReq productCategoryDtoReqDtoReq) {
        return productCategoryService.AddProductCategory(productCategoryDtoReqDtoReq);
    }

    @GetMapping
    public Page<ResProductCategoryDTO> SeeProductCategory (@RequestParam (required = false) String name, Pageable pagination) {

        if(name == null) {
            return productCategoryService.SeeProductCategory(pagination);
        } else{
            return productCategoryService.FindByName(name, pagination );
        }
    }

    @GetMapping ("/{id}")
    public ResProductCategoryDTO FindById(@PathVariable Long id) {
        return productCategoryService.FindById(id);
    }

    @PutMapping ("/{id}")
    @Transactional
    public ResProductCategoryDTO UpdatedProductCategory(@PathVariable Long id, @RequestBody @Valid ProductCategoryDtoReq productCategoryDtoReqDtoReq) {
        return this.productCategoryService.UpdatedProductCategory(id, productCategoryDtoReqDtoReq);
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public void DeleteProductCategory(@PathVariable Long id) {
        productCategoryService.DeleteProductCategory(id);
    }

}
