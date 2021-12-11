package com.tsdv.ntc.view;

import com.tsdv.ntc.MenuProgram;
import com.tsdv.ntc.control.ProductLineDA;
import com.tsdv.ntc.model.ProductLine;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductLineManager extends MenuProgram {
    @Override
    protected void printMenu() {
        // 1. Show all
        // 2. Search by product line
        // 3. Delete by product line
        // 4. Edit by product line
        System.out.println("DEMO JDBC MVC PROGRAM");
        System.out.println("1. Show all Products");
        System.out.println("2. Search by product line");
        System.out.println("3. Delete by product line");
        System.out.println("4. Edit by product line");
        System.out.println("5. Add by product line");
        System.out.println("0. Exit");
    }

    @Override
    protected void doTask(int choice) {
        switch (choice) {
            case 1: showAllProducts(); break;
            case 2: searchProduct(); break;
            case 3: deleteProduct(); break;
            case 4: editProduct(); break;
            case 5: addProduct(); break;
        }
    }
    protected void showInfo(ProductLine p){
        System.out.println("Product Line: "+p.getProductLine());
        System.out.println("Text Description:" + p.getTextDescription());
        System.out.println("Html Description: "+ p.getHtmlDescription());
    }
    protected void inputInfo(ProductLine p) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter product line: ");
        p.setProductLine(sc.nextLine());
        System.out.print("Enter product text description: ");
        p.setTextDescription(sc.nextLine());
        System.out.print("Enter product html description: ");
        p.setHtmlDescription(sc.nextLine());
    }
    public void showAllProducts(){
        try {
            ProductLineDA productLineDA = new ProductLineDA();
            for(ProductLine p : productLineDA.selectAllProductLines()) {
                showInfo(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void searchProduct() {
        try {
            ProductLineDA productLineDA = new ProductLineDA();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter product line: ");
            String productLine =  sc.nextLine();
            ProductLine p = productLineDA.selectProductLine(productLine);
            if(p.getProductLine() != null) {
                showInfo(p);
            } else System.out.println(productLine +" not found!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void deleteProduct() {
        try {
            ProductLineDA productLineDA = new ProductLineDA();
            ProductLine p = new ProductLine();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter product line: ");
            p.setProductLine(sc.nextLine());
            if(productLineDA.deleteProductLine(p))
            {
                System.out.println("Delete success!");
            } else {
                System.out.println("Delete failed. " + p.getProductLine() + " not found!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editProduct() {
        try {
            ProductLineDA productLineDA = new ProductLineDA();
            ProductLine p = new ProductLine();
            inputInfo(p); // enter info of produclines table
            if(productLineDA.editProductLine(p)) {
                System.out.println("Update success!");
            } else {
                System.out.println("Update failed. " + p.getProductLine() + " not found!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void  addProduct() {
        try {
            ProductLineDA productLineDA = new ProductLineDA();
            ProductLine p = new ProductLine();
            inputInfo(p); // enter info of produclines table
            if(productLineDA.addProductLine(p)) {
                System.out.println("Add success!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
