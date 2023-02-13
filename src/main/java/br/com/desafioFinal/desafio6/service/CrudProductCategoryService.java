package br.com.desafioFinal.desafio6.service;


import br.com.desafioFinal.desafio6.dto.req.ProductCategoryDtoReq;
import br.com.desafioFinal.desafio6.dto.res.ResProductCategoryDTO;
import br.com.desafioFinal.desafio6.dto.res.ResUserDTO;
import br.com.desafioFinal.desafio6.model.ProductCategory;
import br.com.desafioFinal.desafio6.repository.ProductCategoryRepository;
import br.com.desafioFinal.desafio6.repository.UsersRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrudProductCategoryService {

    @Autowired
    private final ProductCategoryRepository productCategoryRepository;
    private final UsersRepository usersRepository;

    public CrudProductCategoryService(ProductCategoryRepository productCategoryRepository, UsersRepository usersRepository){
        this.productCategoryRepository = productCategoryRepository;
        this.usersRepository = usersRepository;
    }


    public ResProductCategoryDTO AddProductCategory (ProductCategoryDtoReq productCategoryDtoReq) {

        ProductCategory category = new ProductCategory();

        category.setName(productCategoryDtoReq.getName());
        category.setDescription(productCategoryDtoReq.getDescription());

        this.productCategoryRepository.save(category);

        return new ResProductCategoryDTO(category);

    }

    public ResProductCategoryDTO UpdatedProductCategory(Long id, ProductCategoryDtoReq productCategoryDtoReqDtoReq) {

        ProductCategory category = this.productCategoryRepository.findById(id).orElseThrow(() -> new ServiceException("Id não encontrado"));

        category.setName(productCategoryDtoReqDtoReq.getName());
        category.setDescription(productCategoryDtoReqDtoReq.getDescription());

        productCategoryRepository.save(category);

        return new ResProductCategoryDTO(category);
    }

    public Page<ResProductCategoryDTO> SeeProductCategory (Pageable pagination){
        return this.productCategoryRepository.findAllAndNotDeleted(pagination).map(ResProductCategoryDTO::of);
    }

    public Page<ResProductCategoryDTO> FindByName (String name, Pageable pagination ){
        return this.productCategoryRepository.findByName(name, pagination).map(ResProductCategoryDTO::of);
    }

    public ResProductCategoryDTO FindById (Long id){
        ProductCategory selectedUser = productCategoryRepository.findById(id).orElseThrow(() -> new ServiceException("Id não encontrado"));
        return ResProductCategoryDTO.of(selectedUser);
    }

    public void DeleteProductCategory (Long id){

        if (this.productCategoryRepository.findById(id).isEmpty())
            throw new ServiceException("Categoria não existe");
        productCategoryRepository.softDelit(id);
    }


}
