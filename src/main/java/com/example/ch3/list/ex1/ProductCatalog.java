package com.example.ch3.list.ex1;

import java.util.ArrayList;

public class ProductCatalog {
    private ArrayList<Product> productList;

    public ProductCatalog(){
        productList = new ArrayList<>();
    }
    // 상품 추가
    public void addProduct(Product product){
        productList.add(product);
    }

    // 상품 조회
    public Product getProduct(int id){
        return productList.get(id);
    }

    // 상품 삭제
    public void removeProduct(int id){
        productList.remove(id);
    }

    // 상품 목록 출력
    public void displayProducts(){
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    class Object {
        public  void main(String[] args) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("노트북 - 1500000원");
            list.add("스마트폰 - 800000원");
            list.add("태블릿 - 600000원");

            for (String object : list) {
                System.out.println(object);
            }

            System.out.println("두 번쨰 상품: " + list.get(1));

            for (int i = 1; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }


    public static void main(String[] args) {

        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct(new Product("노트북",1500000));
        catalog.addProduct(new Product("스마트폰", 800000));
        catalog.addProduct(new Product("Tablet", 600000));

        catalog.displayProducts();

    }
}
