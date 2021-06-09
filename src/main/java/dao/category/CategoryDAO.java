package dao.category;

import dao.SQLConnection;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    public static final String SELECT_ALL_CATEGORY = "select * from category;";
    public static final String SELECT_CATEGORY_BY_ID = "select * from category where CategoryId = ?;";
    public static final String INSERT_CATEGORY = "insert into category(CategoryName) values (?);";
    public static final String UPDATE_CATEGORY_BY_ID = "update category set CategoryName = ? where CategoryId = ?;";
    public static final String DELETE_CATEGORY = "delete from category where CategoryId =?;";

    @Override
    public List<Category> findAll() {
        List<Category> categories= new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("CategoryId");
                String name = resultSet.getString("CategoryName");
                categories.add(new Category(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        Category category = new Category();
        Connection connection = SQLConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("CategoryName");
                category.setName(name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean add(model.Category category) {
        Connection connection = SQLConnection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY);
            preparedStatement.setString(1, category.getName());
            rowInserted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted!=0;
    }

    @Override
    public boolean update(int id, model.Category category) {
        Connection connection = SQLConnection.getConnection();
        int rowUpdated = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_BY_ID);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, id);
            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated !=0;
    }

    @Override
    public boolean remove(int id) {
        Connection connection = SQLConnection.getConnection();
        int rowDeleted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted !=0;
    }

}
