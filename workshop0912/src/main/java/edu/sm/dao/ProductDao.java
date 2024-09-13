package edu.sm.dao;

import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<String, Product> {
    @Override
    public Product insert(Product product, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertProduct);
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setInt(3, product.getPrice());
            ps.setDate(4, product.getRegDate());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return product;
    }

    @Override
    public Product update(Product product, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateProduct);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setDate(3, product.getRegDate());
            ps.setString(4, product.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return product;
    }

    @Override
    public Boolean delete(String id, Connection con) throws Exception {
        Boolean result = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteProduct);
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                result = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return result;
    }

    @Override
    public Product select(String id, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement(Sql.selectOneProduct);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setRegDate(rs.getDate("regdate"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return product;
    }

    @Override
    public List<Product> select(Connection con) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectProduct);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setRegDate(rs.getDate("regdate"));
                products.add(product);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return products;
    }
}
