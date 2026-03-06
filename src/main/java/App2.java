import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;

import java.util.Scanner;

public class App2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDAO();
        System.out.println("=== TESTE 1: Department insert ===");
        Department department = new Department(null,"Department test");
        departmentDAO.insert(department);
        System.out.println("New department is: " + department.getName());
        System.out.println("=== TESTE 2: Department DeleteById ===");
        System.out.println("Enter the id:");
        int idDelete = sc.nextInt();
        departmentDAO.deleteById(idDelete);
        System.out.println("=== TESTE 3: Department findById ===");
        System.out.println("Enter the id:");
        int idFind = sc.nextInt();
        System.out.println(departmentDAO.findById(idFind));
        System.out.println("=== TESTE 4: Department findAll ===");
        System.out.println("All departments");
        System.out.println(departmentDAO.findAll());
        System.out.println("=== TESTE 5: Department update ===");
        department =  departmentDAO.findById(4);
        department.setName("Novo Nome Teste Silva");
        departmentDAO.update(department);

    }
}
