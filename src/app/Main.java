package app;
import java.io.*;

public class Main implements Serializable {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
		ListManagement listManagement = new ListManagement();
		try{
			listManagement.readFile("database");
			System.out.println("Poprawnie wczytano bazę danych");
		}catch(IOException e){
			System.out.println("Błąd wczytania bazy danych. Błąd wejscia");
		}catch(ClassNotFoundException ex){
			System.out.println("Błąd wczytania bazy danych. ClassNotFound");
		}
		Menu.clearScreen();
		Menu.startingMenu(listManagement);
		Menu.clearScreen();

	}


}
