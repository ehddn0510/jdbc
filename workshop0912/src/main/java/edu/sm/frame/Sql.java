package edu.sm.frame;

public class Sql {
    public static String insertProduct = "INSERT INTO product (id, name, price, regdate) VALUES (?, ?, ?, ?)";
    public static String selectProduct = "SELECT * FROM product";
    public static String selectOneProduct = "SELECT * FROM product WHERE id=?";
    public static String deleteProduct = "DELETE FROM product WHERE id = ?";
    public static String updateProduct =
            "UPDATE product SET name=?, price=?, regdate=? WHERE id=?";
}
