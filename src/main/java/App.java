import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class App {
    public static void main(String[] args) {

        SellerDAO sellerDAO = DaoFactory.createSellerDAO();
        System.out.println("=== TESTE 1: Seller findById ===");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);

    }
}
