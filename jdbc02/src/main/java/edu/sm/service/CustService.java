package edu.sm.service;

import edu.sm.dao.CustDao;
import edu.sm.dto.Cust;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Dao;
import edu.sm.frame.Mservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustService implements Mservice<String, Cust> {

    CustDao dao;
    ConnectionPool cp;

    public CustService() {
        dao = new CustDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cust add(Cust cust) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(cust, con);
            System.out.println("Send SMS to:" + cust.getId());
            con.commit();
        }catch(DuplicatedIdException e){
            con.rollback();
            throw e;
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return cust;
    }

    @Override
    public Cust modify(Cust cust) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(cust, con);
            System.out.println("Send SMS to:" + cust.getId());
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return cust;
    }

    @Override
    public Boolean remove(String s ) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(s, con);
            System.out.println("Send SMS to:" + s);
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Cust get(String s) throws Exception {
        Connection con = cp.getConnection();
        Cust result = null;
        try {
            result = dao.select(s, con);
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Cust> get() throws Exception {
        Connection con = cp.getConnection();
        List<Cust> result = null;
        try {
            result = dao.select(con);
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }
}

