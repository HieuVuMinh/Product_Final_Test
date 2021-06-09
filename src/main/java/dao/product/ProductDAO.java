package dao.product;

import dao.SQLConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{
    public static final String SELECT_ALL_PRODUCT = "select * from product";
    public static final String SELECT_BOOK_BY_ID = "select * from product where ProductId = ?";;
    public static final String SELECT_PRODUCT_BY_NAME = "select * from product where ProductName like ?";;
    public static final String INSERT_BOOK = "insert into product (ProductName, ProductPrice, ProductQuantity, ProductColor, ProductDescription, CategoryId) value (? , ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "update product set ProductName = ?, ProductPrice = ?, ProductQuantity = ?, ProductColor = ?, ProductDescription = ?, CategoryId = ? WHERE ProductId = ?";
    private static final String DELETE_PRODUCT = "delete from product where ProductId = ?";

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ProductId");
                String name = resultSet.getString("ProductName");
                double price = resultSet.getDouble("ProductPrice");
                int quantity = resultSet.getInt("ProductQuantity");
                String color = resultSet.getString("ProductColor");
                String description = resultSet.getString("ProductDescription");
                int category = resultSet.getInt("CategoryId");
                productList.add(new Product(id, name, price, quantity, color, description, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product findById(int id) {
        Product product = new Product();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id1 = resultSet.getInt("ProductId");
                String name = resultSet.getString("ProductName");
                double price = resultSet.getDouble("ProductPrice");
                int quantity = resultSet.getInt("ProductQuantity");
                String color = resultSet.getString("ProductColor");
                String description = resultSet.getString("ProductDescription");
                int category = resultSet.getInt("CategoryId");
                product.setIdPr(id1);
                product.setNameProduct(name);
                product.setPricePr(price);
                product.setQuantityPr(quantity);
                product.setColorPr(color);
                product.setDescriptionPr(description);
                product.setCategoryPr(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean add(Product product) {
        Connection connection = SQLConnection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK);
            preparedStatement.setString(1, product.getNameProduct());
            preparedStatement.setDouble(2, product.getPricePr());
            preparedStatement.setInt(3, product.getQuantityPr());
            preparedStatement.setString(4, product.getColorPr());
            preparedStatement.setString(5, product.getDescriptionPr());
            preparedStatement.setInt(6, product.getCategoryPr());
            rowInserted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted != 0;
    }

    @Override
    public boolean update(int id, Product product) {
        Connection connection = SQLConnection.getConnection();
        int rowInserted = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getNameProduct());
            preparedStatement.setDouble(2, product.getPricePr());
            preparedStatement.setInt(3, product.getQuantityPr());
            preparedStatement.setString(4, product.getColorPr());
            preparedStatement.setString(5, product.getDescriptionPr());
            preparedStatement.setInt(6, product.getCategoryPr());
            preparedStatement.setInt(7, product.getIdPr());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted != 0;
    }

    @Override
    public boolean remove(int id) {
        Connection connection = SQLConnection.getConnection();
        int rowInserted = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowInserted != 0;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> productList = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, "%" + name + "%" );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("ProductId");
                String name1 = resultSet.getString("ProductName");
                double price = resultSet.getDouble("ProductPrice");
                int quantity = resultSet.getInt("ProductQuantity");
                String color = resultSet.getString("ProductColor");
                String description = resultSet.getString("ProductDescription");
                int category = resultSet.getInt("CategoryId");
                productList.add(new Product(id,name1,price,quantity,color, description, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
