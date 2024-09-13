package edu.sm.service;

import edu.sm.dao.ProductDao;
import edu.sm.dto.Product;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements MService<Integer, Product> {
    private ProductDao productDao;
    private ConnectionPool pool;

    public ProductService() throws SQLException {
        this.pool = ConnectionPool.create();
        this.productDao = new ProductDao();
    }

    // insert 메서드 (기존 add 메서드)
    @Override
    public Product insert(Product product) throws Exception {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);  // 트랜잭션 시작
            Product result = productDao.insert(product, connection);
            connection.commit();
            return result;
        } catch (SQLException | DuplicatedIdException e) {
            if (connection != null) {
                connection.rollback();  // 예외 발생 시 롤백
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                pool.releaseConnection(connection);
            }
        }
    }

    // update 메서드 (기존 modify 메서드)
    @Override
    public Product update(Product product) throws Exception {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            Product result = productDao.update(product, connection);
            connection.commit();
            return result;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                pool.releaseConnection(connection);
            }
        }
    }

    // delete 메서드 (기존 remove 메서드)
    @Override
    public Boolean delete(Integer id) throws Exception {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.setAutoCommit(false);
            boolean result = productDao.delete(id, connection);
            connection.commit();
            return result;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                pool.releaseConnection(connection);
            }
        }
    }

    // selectOne 메서드 (기존 get(K k) 메서드)
    @Override
    public Product selectOne(Integer id) throws Exception {
        try (Connection connection = pool.getConnection()) {
            return productDao.select(id, connection);
        }
    }

    // select 메서드 (기존 get() 메서드)
    @Override
    public List<Product> select() throws Exception {
        try (Connection connection = pool.getConnection()) {
            return productDao.select(connection);
        }
    }
}
