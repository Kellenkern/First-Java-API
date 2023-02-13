package br.com.desafioFinal.desafio6.repository;

import br.com.desafioFinal.desafio6.model.ProductCategory;
import br.com.desafioFinal.desafio6.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {


    @Query (value = "SELECT * FROM `categoria_produto` WHERE `is_delited` = 0", nativeQuery = true)
    Page<ProductCategory> findAllAndNotDeleted(Pageable pagination);

    @Override
    @Query(value = "SELECT * FROM `categoria_produto` WHERE `id` = :id", nativeQuery = true)
    Optional<ProductCategory> findById(@Param("id") Long id);

    @Query(value = "SELECT * FROM `categoria_produto` WHERE `id` = :id", nativeQuery = true)
    ProductCategory findByEntity(@Param("id") Long id);

    @Query(value = "SELECT * FROM `categoria_produto` WHERE name = \":name\"", nativeQuery = true)
    Page<ProductCategory> findByName(@Param("name") String name, Pageable pagination);

    @Query(value = "UPDATE `categoria_produto` SET `is_delited` = true WHERE `id` = :id", nativeQuery = true)
    @Transactional
    @Modifying
    void softDelit(@Param("id") Long id);

}


