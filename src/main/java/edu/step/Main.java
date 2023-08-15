package edu.step;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//     try{
//            EmployeeDAO db = new EmployeeDAO();
//            db.getConnection();
//            System.out.println("Succes!");
//        } catch (SQLException ex) {
//            System.out.println("Nu s-a putut ob'ine conexiunea"+ ex.getMessage());
//        }

        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Meniu:");
            System.out.println("1. Adaugă angajat nou");
            System.out.println("2. Afișează detalii angajat după ID");
            System.out.println("3. Actualizează detalii angajat");
            System.out.println("4. Șterge angajat");
            System.out.println("5. Afiseaza toti angajatii");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consumăm newline lăsat în buffer

            switch (option) {
                case 1:
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Departament: ");
                    String departament = scanner.nextLine();
                    System.out.print("Salariu: ");
                    double salariu = scanner.nextDouble();
                    employeeDAO.createEmployee(nume, departament, salariu);
                    break;

                case 2:
                    System.out.print("ID angajat: ");
                    int employeeId = scanner.nextInt();
                    employeeDAO.getEmployeeById(employeeId);
                    break;

                case 3:
                    System.out.print("ID angajat: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nume nou: ");
                    String newNume = scanner.nextLine();
                    System.out.print("Departament nou: ");
                    String newDepartament = scanner.nextLine();
                    System.out.print("Salariu nou: ");
                    double newSalariu = scanner.nextDouble();
                    employeeDAO.updateEmployee(idToUpdate, newNume, newDepartament, newSalariu);
                    break;

                case 4:
                    System.out.print("ID angajat de șters: ");
                    int idToDelete = scanner.nextInt();
                    employeeDAO.deleteEmployee(idToDelete);
                    break;

                case 5:
                    employeeDAO.selectAllEmployees();
                    break;

                case 0:
                    System.out.println("La revedere!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opțiune invalidă. Alegeți din nou.");
                    break;
            }
        }
    }
}