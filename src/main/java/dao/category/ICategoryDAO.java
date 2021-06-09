package dao.category;

import model.Category;
import model.Product;

import java.util.List;

public interface ICategoryDAO {
    List<Category> findAll();

    Category findById(int id);

    boolean add(Category category);

    boolean update(int id, Category category);

    boolean remove(int id);

}
