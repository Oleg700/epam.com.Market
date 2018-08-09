package com.epam.market.model;

public class ProductTranslation {
  private int productId;
  private int languageId;
  private String productTitle;
  private String description;
  private Product product;
  private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Product getProduct() {
        return product;
    }

    public ProductTranslation setProduct(Product product) {
        this.product = product;
        return this;
    }

    public int getProductId() {
        return productId;
    }

    public ProductTranslation setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getLanguageId() {
        return this.languageId;
    }

    public ProductTranslation setLanguageId(int languageId) {
        this.languageId = languageId;
        return this;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public ProductTranslation setProductTitle(String productTitle) {
        this.productTitle = productTitle;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductTranslation setDescription(String description) {
        this.description = description;
        return this;
    }
}
