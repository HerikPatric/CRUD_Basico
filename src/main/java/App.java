import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Date data = new Date();
        SellerDAO sellerDAO = DaoFactory.createSellerDAO();
        System.out.println("=== TESTE 1: Seller findById ===");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);
        System.out.println("\n=== TESTE 2: Seller findByDepartment ===");
        Department department = new Department(2, null);
        List<Seller> list = sellerDAO.findByDepartment(department);
        list.forEach(System.out::println);
        System.out.println("\n=== TESTE 3: Seller findAll ===");
        List<Seller> lista = sellerDAO.findAll();
        lista.forEach(System.out::println);
        System.out.println("\n=== TESTE 4: Seller insert ===");
        Seller newSeller =  new Seller(null,"Robinho","robinho@binho",data,5000.00,department);
        //sellerDAO.insert(newSeller);
       //System.out.println("Inserted! new id: " + newSeller.getId());
    }
}
