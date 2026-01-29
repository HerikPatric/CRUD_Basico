package model.dao;

import db.DataBase;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDAO createSellerDAO(){
        return  new SellerDaoJDBC(DataBase.getConnection());
    }
}
