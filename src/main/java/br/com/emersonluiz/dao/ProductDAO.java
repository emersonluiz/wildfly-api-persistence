package br.com.emersonluiz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.emersonluiz.model.Product;

public class ProductDAO {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Product product) {
        entityManager.persist(product);
    }

    public void update(Product product) {
        entityManager.merge(product);
    }

    public void delete(Product product) {
        entityManager.remove(product);
    }

    public Product findOne(int id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    public List<Product> findAll() {
        List<Product> products = entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        return products;
    }

}
