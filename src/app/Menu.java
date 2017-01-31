package app;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

	static void clearScreen() {
		
		System.out.flush();
	}
    static void startingMenu(ListManagement listManagement) throws IOException, ClassNotFoundException {
        
		clearScreen();
        System.out.println("------------------------------------------------------------------");
        System.out.println("-----------------Witaj w naszym systemie bankowym-----------------");
        int wybor = menu();

        while(wybor!=0){
            switch(wybor) {
                case 1:
                    clearScreen();
                    listManagement.addClient();
                    break;
                case 2:
                    clearScreen();
                    listManagement.removeClient();
                    break;
                case 3:
                    clearScreen();
                    listManagement.deposit();
                    break;
                case 4:
                    clearScreen();
                    listManagement.withdraw();
                    break;
                case 5:
                    clearScreen();
                    listManagement.transfer();
                    break;
                case 6:
                    clearScreen();
                    listManagement.printList();
                    break;
                case 7:
                    clearScreen();
                    findClientChoice(listManagement);
                    break;
                case 8:
                    clearScreen();
                    System.out.println("Podaj ścieżkę pliku");
                    Scanner scan = new Scanner(System.in);
                    String fileName = scan.nextLine();
                    try{
                        listManagement.readFile(fileName);
                        System.out.println("Poprawnie wczytano bazę danych");
                    }catch(Exception e){
                        System.out.println("Błąd wczytania bazy danych");
                    }
                    break;
                case 9:
                    clearScreen();
                    listManagement.updateDatabase(listManagement.getMyList());

                    break;
                case 10:
                    clearScreen();
                    break;
            }
            System.out.println("\nWciśnij Enter, aby kontynuować...");
            try {
                System.in.read();
            }
            catch(IOException e)
            {
                System.out.println("eee");
            }
            clearScreen();
            wybor = menu();
        }


        }
    private static int menu() {
        System.out.println("----------------------Co chcesz zrobić?---------------------------");
        System.out.println("-------------Wprowadź odpowiednią cyfrę z zadaniem:---------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("                 [1] - Stwórz konto");
        System.out.println("                 [2] - Usuń konto");
        System.out.println("                 [3] - Wpłać pieniądze");
        System.out.println("                 [4] - Wypłać pieniądze");
        System.out.println("                 [5] - Wykonaj przelew");
        System.out.println("                 [6] - Wyświetl wszystkie konta");
        System.out.println("                 [7] - Znajdź konto");
        System.out.println("                 [8] - Wczytaj baze danych");
        System.out.println("                 [9] - Aktualizuj baze danych");
        System.out.println("                 [0] - Wyjdź");

        Scanner odczyt = new Scanner(System.in);
        int wybor;
        wybor = odczyt.nextInt();

        return wybor;
    }
	private static int findingMenu(){
        System.out.println("-----Jaki parametr chcesz użyć do wyszukiwania?-------");
        System.out.println("-------------Wprowadź odpowiednią cyfrę---------------");
        System.out.println("------------------------------------------------------");
        System.out.println(" ");
        System.out.println("                 [1] - ID");
        System.out.println("                 [2] - Imię");
        System.out.println("                 [3] - Nazwisko");
        System.out.println("                 [4] - PESEL");
        System.out.println("                 [5] - Adres");
        System.out.println("                 [0] - Wyjdź");

        Scanner reading = new Scanner(System.in);
        int choice;
        choice = reading.nextInt();

        return choice;
    }

    private static void findClientChoice(ListManagement listManagement){
        int choice= findingMenu();

        while(choice!=0){
            switch(choice) {
                case 1:
                    clearScreen();
                    listManagement.findByID();
                    break;
                case 2:
                    System.out.println("Podaj Imię właściciela konta, które chcesz wyszukać");
                    clearScreen();
                    listManagement.findByFirstName();
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Podaj nazwisko właściciela konta, które chcesz wyszukać");
                    listManagement.findByName();
                    break;
                case 4:
                    clearScreen();
                    System.out.println("Podaj PESEL właściciela konta, które chcesz wyszukać");
                    listManagement.findByPesel();
                    break;
                case 5:
                    clearScreen();
                    findByAdress(listManagement);
                    break;
            }
            System.out.println("\nWciśnij Enter, aby kontynuować...");
            try {
                System.in.read();
            }
            catch(IOException e)
            {
                System.out.println("eee");
            }
            clearScreen();
            choice = findingMenu();
        }
    }

    private static int addressMenu(){
        System.out.println("-----Jaki parametr chcesz użyć do wyszukiwania?-------");
        System.out.println("-------------Wprowadź odpowiednią cyfrę---------------");
        System.out.println("------------------------------------------------------");
        System.out.println(" ");
        System.out.println("                 [1] - Ulica");
        System.out.println("                 [2] - Miasto");
        System.out.println("                 [3] - Kod pocztowy");
        System.out.println("                 [0] - Wyjdź");

        Scanner reading = new Scanner(System.in);
        int choice;
        choice = reading.nextInt();

        return choice;
    }

    private static void findByAdress(ListManagement listManagement){
        int choice= addressMenu();
        while(choice!=0){
            switch(choice) {
                case 1:
                    clearScreen();
                    System.out.println("Podaj ulicę");
                    listManagement.findByStreet();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Podaj miasto");
                    listManagement. findByCity();
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Podaj kod pocztowy");
                    listManagement.findByPostCode();
                    break;
            }
            System.out.println("\nWciśnij Enter, aby kontynuować...");
            try {
                System.in.read();
            }
            catch(IOException e)
            {
                System.out.println("eee");
            }
            clearScreen();
            choice = addressMenu();
        }
    }
	
}


