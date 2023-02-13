package br.com.desafioFinal.desafio6.service;

import br.com.desafioFinal.desafio6.dto.req.ProductDtoReq;
import br.com.desafioFinal.desafio6.dto.res.ResProductDTO;
import br.com.desafioFinal.desafio6.dto.res.ResUserDTO;
import br.com.desafioFinal.desafio6.model.Product;
import br.com.desafioFinal.desafio6.model.ProductCategory;
import br.com.desafioFinal.desafio6.repository.ProductCategoryRepository;
import br.com.desafioFinal.desafio6.repository.ProductRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrudProductService {

    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ProductCategoryRepository productCategoryRepository;

    public CrudProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public ResProductDTO AddProduct(ProductDtoReq productDtoReq) {

        ProductCategory category = productCategoryRepository.findByEntity(productDtoReq.getIdCatProd());
        Product product = new Product();
        product.setName(productDtoReq.getName());
        product.setQuantity(productDtoReq.getQuantity());
        product.setImg(productDtoReq.getImg());
        product.setDescription(productDtoReq.getDescription());
        product.setBarcode(productDtoReq.getBarcode());
        product.setBrand(productDtoReq.getBrand());
        product.setCatProd(category);

        return ResProductDTO.of(this.productRepository.save(product));
    }

    public ResProductDTO UpdatedProduct(Long id, ProductDtoReq productDtoReq) {

        Product selectedProduct = productRepository.findById(id).orElseThrow(() -> new ServiceException("Id não encontrado"));
        selectedProduct.setId(id);
        selectedProduct.setName(productDtoReq.getName());
        selectedProduct.setQuantity(productDtoReq.getQuantity());
        selectedProduct.setImg(productDtoReq.getImg());
        selectedProduct.setDescription(productDtoReq.getDescription());
        selectedProduct.setBarcode(productDtoReq.getBarcode());
        selectedProduct.setBrand(productDtoReq.getBrand());
        selectedProduct.setCatProd(this.productCategoryRepository.findById(productDtoReq.getIdCatProd()).orElseThrow(() -> new ServiceException("categoria não encontrado")));
        selectedProduct.setIsDelited(false);

        productRepository.save(selectedProduct);
        return new ResProductDTO(selectedProduct);
    }

    public Page<ResProductDTO> SeeProduct (Pageable pagination){
        return this.productRepository.findAllAndNotDeleted(pagination).map(ResProductDTO::of);
    }

    public Page<ResProductDTO> FindByName (String name, Pageable pagination ){
        return this.productRepository.findByName(name, pagination).map(ResProductDTO::of);
    }

    public ResProductDTO FindById (Long id){
        Product selectedProduct = productRepository.findById(id).orElseThrow(() -> new ServiceException("Id não encontrado"));
        return ResProductDTO.of(selectedProduct);
    }

    public void DeleteProduct (Long id){

        if (this.productRepository.findById(id).isEmpty())
            throw new ServiceException("Produto não existe");
        productRepository.softDelit(id);

    }
}