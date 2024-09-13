package edu.sm.dao;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Integer, Product> {

    @Override
    public Product insert(Product product, Connection connection) throws SQLException, DuplicatedIdException {
        try (PreparedStatement pstmt = connection.prepareStatement(Sql.insertProduct)) {
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setString(3, product.getSize());
            pstmt.setString(4, product.getColor());
            pstmt.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
            return product;
        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) {  // SQL Integrity Constraint Violation
                throw new DuplicatedIdException("중복된 제품 이름: " + product.getName());
            }
            throw e;
        }
    }

    @Override
    public Product update(Product product, Connection connection) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(Sql.updateProduct)) {
            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setString(3, product.getSize());
            pstmt.setString(4, product.getColor());
            pstmt.setInt(5, product.getId());
            pstmt.executeUpdate();
            return product;
        }
    }

    @Override
    public boolean delete(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement(Sql.deleteProduct)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        }
    }

    @Override
    public Product select(Integer id, Connection connection) throws SQLException {
        Product product = null;
        try (PreparedStatement pstmt = connection.prepareStatement(Sql.selectOneProduct)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getTimestamp("registration_date")
                );
            }
        }
        return product;
    }

    @Override
    public List<Product> select(Connection connection) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(Sql.selectProduct)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("size"),
                        rs.getString("color"),
                        rs.getTimestamp("registration_date")
                );
                products.add(product);
            }
        }
        return products;
    }
}
