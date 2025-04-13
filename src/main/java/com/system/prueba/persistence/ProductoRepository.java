package com.system.prueba.persistence;

import com.system.prueba.domain.Product;
import com.system.prueba.domain.repository.ProductRepository;
import com.system.prueba.persistence.crud.ProductoCrudRepository;
import com.system.prueba.persistence.entity.Producto;
import com.system.prueba.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    /*
    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        if (producto.getIdProducto() == 0) {
            producto.setIdProducto(null);
        }
        return mapper.toProduct(productoCrudRepository.save(producto));
    }*/

    @Override
    public void delete(int productId) {
        productoCrudRepository.deleteById(productId);
    }
}
