package controller;

import model.Category;
import model.Product;
import service.CategoryService;
import service.ICategoryService;
import service.IProductService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Product_Final_Test", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();
    private ICategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            default:
                break;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if(product == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("error-404.jsp");
        }
        productService.remove(id);
        response.sendRedirect("/products");
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view.jsp");
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }
        request.setAttribute("product", product);
        dispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product = new Product(id, name, price, quantity, color, description, category);
        boolean isCreate = productService.update(id, product);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("category", categoryList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        }
        request.setAttribute("product", product);
        dispatcher.forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Product product = new Product(name, price, quantity, color, description, category);
        boolean isCreate = productService.add(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("category", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> products;
        if (search == null || search.equals("")){
            products = this.productService.findAll();
        } else {
            products = this.productService.findByName(search);
        }
        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }
}
