package controller;


import model.Category;
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

@WebServlet(name = "CategoryServlet", value = "/categorys")
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewCategory(request, response);
                break;
            case "edit":
                showEditCategory(request, response);
                break;
            case "delete":
                deleteCategory(request, response);
                break;
            default:
                showListCategory(request, response);
                break;
        }
    }

    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        if (category == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("category/error-404.jsp");
            dispatcher.forward(request, response);
        }
        categoryService.remove(id);
        response.sendRedirect("/category");
    }

    private void showEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/categoryEdit.jsp");
        if (category == null) {
            dispatcher = request.getRequestDispatcher("/category/error-404.jsp");
        }
        request.setAttribute("category", category);
        dispatcher.forward(request, response);
    }

    private void showListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories;
        categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/categoryList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createCategory(request, response);
                break;
            case "edit":
                editCategory(request, response);
                break;
        }
    }
    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(id, name);
       boolean isInserted= categoryService.update(id, category);
        if(!isInserted){
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message","Successful");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/categoryEdit.jsp");
        dispatcher.forward(request, response);
    }
    private void createNewCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/categoryCreate.jsp");
        dispatcher.forward(request, response);
    }
    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Category category = new Category(name);
        boolean isInserted = categoryService.add(category);
        if (!isInserted) {
            request.setAttribute("message","Error!");
        }else {
            request.setAttribute("message", "Successful!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/categoryCreate.jsp");
        dispatcher.forward(request, response);
    }

}

