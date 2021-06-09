package service;

import dao.product.IProductDAO;
import dao.product.ProductDAO;
import model.Product;

import java.util.List;

public class ProductService implements IProductService{
    private IProductDAO productDAO = new ProductDAO();

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean add(Product product) {
        return productDAO.add(product);
    }

    @Override
    public boolean update(int id, Product product) {
        return productDAO.update(id, product);
    }

    @Override
    public boolean remove(int id) {
        return productDAO.remove(id);
    }

    @Override
    public List<Product> findByName(String name) {
        return productDAO.findByName(name);
    }
}
