package edu.sm.service;

import edu.sm.dao.ProductDao;
import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Dao;
import edu.sm.frame.Mservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements Mservice<String, Product> {

    ProductDao dao;
    ConnectionPool cp;

    public ProductService() {
        dao = new ProductDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product add(Product product) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(product, con);
            System.out.println("Send SMS to:" + product.getId());
            con.commit();
        } catch (DuplicatedIdException e) {
            con.rollback();
            throw e;
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return product;
    }

    @Override
    public Product modify(Product product) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(product, con);
            System.out.println("Send SMS to:" + product.getId());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return product;
    }

    @Override
    public Boolean remove(String id) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(id, con);
            System.out.println("Send SMS to:" + id);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Product get(String id) throws Exception {
        Connection con = cp.getConnection();
        Product result = null;
        try {
            result = dao.select(id, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Product> get() throws Exception {
        Connection con = cp.getConnection();
        List<Product> result = null;
        try {
            result = dao.select(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}
