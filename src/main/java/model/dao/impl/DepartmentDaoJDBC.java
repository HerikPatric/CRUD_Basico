package model.dao.impl;

import db.DataBase;
import db.DataBaseException;
import model.dao.DepartmentDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj){
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("INSERT INTO Department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DataBaseException("unexpected error! no rows affected!");
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            DataBase.closeResultSet(rs);
            DataBase.closeStatemente(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE department SET Name = ? WHERE department.id = ?");
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeStatemente(st);
        }
    }
    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM department WHERE id = ?");
            st.setInt(1,id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }finally {
            DataBase.closeStatemente(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department WHERE department.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Department dep = new Department(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                return dep;
            }
            return null;
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        } finally {
            DataBase.closeStatemente(st);
            DataBase.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("select * from department");
            rs = st.executeQuery();
            while (rs.next()){
                Department dep = new Department(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                departments.add(dep);
            }
            return departments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DataBase.closeStatemente(st);
            DataBase.closeResultSet(rs);
        }

    }

}
