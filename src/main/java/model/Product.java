package model;

public class Product {
    private int idPr;
    private String nameProduct;
    private double pricePr;
    private int quantityPr;
    private String colorPr;
    private String descriptionPr;
    private int categoryPr;

    public Product() {
    }

    public Product(int idPr, String nameProduct, double pricePr, int quantityPr, String colorPr, String descriptionPr, int categoryPr) {
        this.idPr = idPr;
        this.nameProduct = nameProduct;
        this.pricePr = pricePr;
        this.quantityPr = quantityPr;
        this.colorPr = colorPr;
        this.descriptionPr = descriptionPr;
        this.categoryPr = categoryPr;
    }

    public Product(String nameProduct, double pricePr, int quantityPr, String colorPr, String descriptionPr, int categoryPr) {
        this.nameProduct = nameProduct;
        this.pricePr = pricePr;
        this.quantityPr = quantityPr;
        this.colorPr = colorPr;
        this.descriptionPr = descriptionPr;
        this.categoryPr = categoryPr;
    }

    public int getIdPr() {
        return idPr;
    }

    public void setIdPr(int idPr) {
        this.idPr = idPr;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPricePr() {
        return pricePr;
    }

    public void setPricePr(double pricePr) {
        this.pricePr = pricePr;
    }

    public int getQuantityPr() {
        return quantityPr;
    }

    public void setQuantityPr(int quantityPr) {
        this.quantityPr = quantityPr;
    }

    public String getColorPr() {
        return colorPr;
    }

    public void setColorPr(String colorPr) {
        this.colorPr = colorPr;
    }

    public String getDescriptionPr() {
        return descriptionPr;
    }

    public void setDescriptionPr(String descriptionPr) {
        this.descriptionPr = descriptionPr;
    }

    public int getCategoryPr() {
        return categoryPr;
    }

    public void setCategoryPr(int categoryPr) {
        this.categoryPr = categoryPr;
    }
}
