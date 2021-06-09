package service;

import dao.category.CategoryDAO;
import dao.category.ICategoryDAO;
import dao.product.IProductDAO;
import dao.product.ProductDAO;
import model.Category;
import model.Product;

import java.util.List;

public class CategoryService implements ICategoryService{
    private ICategoryDAO categoryDAO = new CategoryDAO();
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public boolean add(Category category) {
        return categoryDAO.add(category);
    }

    @Override
    public boolean update(int id, Category category) {
        return categoryDAO.update(id, category);
    }

    @Override
    public boolean remove(int id) {
        return categoryDAO.remove(id);
    }
}
