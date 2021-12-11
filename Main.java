package com.tsdv.ntc;

import com.tsdv.ntc.view.ProductLineManager;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        ProductLineManager p = new ProductLineManager();
        p.run();
    }
}
