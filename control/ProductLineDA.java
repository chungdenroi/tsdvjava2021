package com.tsdv.ntc.control;
import java.sql.*;
import com.tsdv.ntc.model.ProductLine;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductLineDA extends DataAccess {
    private String table = "productlines";
    private String idCol = "productLine";

    public ArrayList<ProductLine> selectAllProductLines() throws SQLException {
        ResultSet rs = selectAll("productlines");
        ArrayList<ProductLine> lines = new ArrayList<>();
        while (rs.next()) {
            String productLine = rs.getString("productLine");
            String textDesc = rs.getString("textDescription");
            String htmlDesc = rs.getString("htmlDescription");
            ProductLine pline = new ProductLine(productLine, textDesc, htmlDesc);
            lines.add(pline);
        }
        conn.close();
        return lines;
    }

    public ProductLine selectProductLine(String productLine) throws SQLException {
        // prepare SQL statement with info
        // execute statement
        // get info from ResultSet to create a ProductLine object to return
        ResultSet rs = selectByID(table, idCol, productLine);
        ProductLine pline = new ProductLine();
        while (rs.next()) {
            String textDesc = rs.getString("textDescription");
            String htmlDesc = rs.getString("htmlDescription");
            pline = new ProductLine(productLine, textDesc, htmlDesc);
        }
        conn.close();
        return pline;
    }

    public boolean deleteProductLine(ProductLine pline) throws SQLException {
        String productLine = pline.getProductLine();
        if (deleteByID(table, idCol, productLine)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editProductLine(ProductLine pline) throws SQLException {
        // get info from pline
        // prepare SQL statement with info
        // execute statement
        conn = connectDB();
        String sql = "UPDATE " + table + " SET textDescription = ?, htmlDescription = ? WHERE " + idCol + " = ?";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1,pline.getTextDescription() );
        stm.setString(2,pline.getHtmlDescription() );
        stm.setString(3,pline.getProductLine() );
        int nRows = stm.executeUpdate();
        conn.close();
        return nRows != 0;
    }
    public boolean addProductLine(ProductLine pline) throws SQLException {
        conn = connectDB();
        String sql = "INSERT INTO " + table +" (productLine, textDescription, htmlDescription)" +
                " values (?, ?, ?);";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setString(1,pline.getProductLine() );
        stm.setString(2,pline.getTextDescription() );
        stm.setString(3,pline.getHtmlDescription() );
        int nRows = stm.executeUpdate();
        conn.close();
        return nRows != 0;
    }
}
