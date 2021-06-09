package dao.product;

import model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> findAll();

    Product findById(int id);

    boolean add(Product product);

    boolean update(int id, Product product);

    boolean remove(int id);

    List<Product> findByName(String name);
}
