package edu.sm.dao;

import edu.sm.dto.Cust;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CustDao implements Dao<String, Cust> {
    @Override
    public Cust insert(Cust cust, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertCust);
            ps.setString(1, cust.getId());
            ps.setString(2, cust.getPwd());
            ps.setString(3, cust.getName());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0001");
        } catch (Exception e) {
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return cust;
    }

    @Override
    public Cust update(Cust cust, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCust);
            ps.setString(1, cust.getPwd());
            ps.setString(2, cust.getName());
            ps.setString(3, cust.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return cust;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        Boolean b = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteCust);
            ps.setString(1, s);
            int result = ps.executeUpdate();
            if (result == 1) {
                b = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return b;
    }

    @Override
    public Cust select(String s, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cust cust = null;
        try {
            ps = con.prepareStatement(Sql.selectOneCust);
            ps.setString(1, s);
            rs = ps.executeQuery();
            rs.next();
            cust = new Cust();
            cust.setId(rs.getString("id"));
            cust.setId(rs.getString("pwd"));
            cust.setId(rs.getString("name"));
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
        return cust;
    }

    @Override
    public List<Cust> select(Connection con) throws Exception {
        List<Cust> custs = new ArrayList<Cust>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectCust);
            rs = ps.executeQuery();
            while(rs.next());
            {
                Cust cust = new Cust();
                cust.setId(rs.getString("id"));
                cust.setId(rs.getString("pwd"));
                cust.setId(rs.getString("name"));
                custs.add(cust);
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
        return custs;
    }
}