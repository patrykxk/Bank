package app;
import java.io.*;
import java.util.*;

class ListManagement implements Serializable{
	private ArrayList <Account> myList = new ArrayList<Account>();

    public ArrayList <Account> getMyList(){ return myList;}

    void addClient(){
        boolean flag;
        Scanner klaw = new Scanner(System.in);
		System.out.println("Podaj:");
		System.out.println("Imię:");
		String firstName = klaw.nextLine();
		System.out.println("Nazwisko:");
		String name = klaw.nextLine();
		System.out.println("Pesel");
        String pesel;
        do {
            flag = false;
            pesel = klaw.nextLine();
            if(pesel.length()!=11){
                System.out.println("Pesel niepoprawny. Wprowadź 11 cyfr.");
                flag = true;
            }
        }while(flag);

		Scanner scan = new Scanner(System.in);
		System.out.println("Ulica:");
		String street;
		street = scan.nextLine();
		System.out.println("Numer domu/mieszkania:");
		String number;
		number = scan.nextLine();
		System.out.println("Miasto");
		String city = scan.nextLine();
		System.out.println("Kod pocztowy");
		String postCode = scan.nextLine();
        int id;
		Random generator = new Random();
        do {
            id = generator.nextInt(99999) + 10000;
        }while(boolFindByID(id));
        Menu.clearScreen();
        System.out.println("--------------------------------------------------------");
        System.out.println(id + "	" + firstName + " " + name + "	PESEL:" + pesel);
        System.out.println("	"  + street + " " + number);
        System.out.println("	"  + postCode + " " + city);
        System.out.println("--------------------------------------------------------");

        if(contiune()) {
            Account temp = new Account();
            Account.Address address = temp.new Address(street, number, city, postCode);
            Account account = new Account(id, firstName, name, pesel, address);
            myList.add(account);
            System.out.println("Zapamiętaj swój unikalny numer ID: " + id);
            try {
                saveAccount(account);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Błąd wyjscia zapisu do pliku");
            } catch (ClassNotFoundException ex) {
                System.out.println("Nie znaleziono klasy do zapisu pliku");
            }
        }
	}
	private boolean contiune(){
        System.out.println("Czy na pewno chcesz wykonać tę operację?");
        System.out.println("Wprowadź t lub n");
        Scanner scan = new Scanner(System.in);
        String odczyt = scan.nextLine();
        return (odczyt.equals("t") || odczyt.equals("T"));
    }
	void removeClient() {
        int i = findByID();
        if(contiune()) {
            myList.remove(i);

            try {
                updateDatabase(myList);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Błąd wyjscia aktualizacji bazy danych");
            } catch (ClassNotFoundException ex) {
                System.out.println("Nie znaleziono klasy aktualizacji bazy danych");
            }
            System.out.println("Poprawnie usunięto konto");
        }

	}

    int findByID(){
        Scanner scan = new Scanner(System.in);
        int i, j =0;
        
        System.out.println("Podaj numer ID");
        do {
            while (!scan.hasNextInt()){
                scan.next();
                System.out.println("Błąd! Wprowadz tylko wartosci liczbowe");
            }
            int skan = scan.nextInt();
            for (i = 0; i < myList.size(); i++) {
                if ((myList.get(i)).getID() == skan) {
                    print(myList.get(i));
                    return i;
                }
            }
            System.out.println("Brak ID w bazie danych, spróbuj jeszcze raz.");
            j++;
        } while (j != 5);

        System.out.println("5 razy wprowadzono id, którego nie ma w systemie.");
        System.out.println("Jeżeli nie znasz numeru id, spróbuj skorzystać z wyszukarki");
        scan.close();
        return -1;
    }

    boolean boolFindByID(int reading){
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if((myList.get(i).getID()) == reading){
                flag = true;
            }
        }
        return flag;
    }

    void findByFirstName(){
        Scanner scan = new Scanner(System.in);
        String reading = scan.nextLine();
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if((myList.get(i)).getFirstName().equals(reading)){
                print(myList.get(i));
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nie znaleziono konta z podanym imieniem");
        }
    }

    void findByName(){
        Scanner scan = new Scanner(System.in);
        String reading = scan.nextLine();
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if(((myList.get(i)).getName()).equals(reading)){
                print(myList.get(i));
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nie znaleziono konta z podanym nazwiskiem");
        }
    }

    void findByPesel(){
        Scanner scan = new Scanner(System.in);
        String reading = scan.nextLine();
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if((myList.get(i)).getPesel().equals(reading)){
                print(myList.get(i));
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nie znaleziono konta z podanym peselem");
        }
    }
    void findByStreet(){
        Scanner scan = new Scanner(System.in);
        String reading = scan.nextLine();
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if(((myList.get(i)).getStreet()).equals(reading)){
                print(myList.get(i));
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nie znaleziono konta z tą ulicą.");
        }
    }

    void findByCity(){
        Scanner scan = new Scanner(System.in);
        String reading = scan.nextLine();
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if((myList.get(i)).getCity().equals(reading)){
                print(myList.get(i));
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nie znaleziono konta z podanym miastem");
        }
    }

    void findByPostCode(){
        Scanner scan = new Scanner(System.in);
        String reading = scan.nextLine();
        int i;
        boolean flag = false;
        for(i=0; i < myList.size(); i++){
            if((myList.get(i)).getPostCode().equals(reading)){
                print(myList.get(i));
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nie znaleziono konta z podanym kodem pocztowym");
        }
    }
    void printList() {
        int i;
        if (myList.isEmpty()){
            System.out.println("Brak kont w systemie");
        }else{
			System.out.println("--------------------------------------------------------");
            for(i = 0; i < myList.size(); i++){
                print(myList.get(i));
            }
        }
    }

	private void print(Account x) {
		System.out.println(x.clientID + "	" + x.firstName + " " + x.surname + "	PESEL:" + x.pesel + "	Saldo: " + x.balance + "zł");
		System.out.println("	"  + x.address.street + " " + x.address.streetNo);
		System.out.println("	"  + x.address.postCode + " " + x.address.city);
		System.out.println("--------------------------------------------------------");
	}

    void deposit() throws IOException, ClassNotFoundException {
        int i = findByID();
        if (i==-1) {
            return;
        }
        Account account = myList.get(i);
        System.out.println("Twoje saldo wynosi: " + account.balance);

        System.out.println("Ile pieniędzy chcesz przelać?");
        Scanner scan = new Scanner(System.in);
        Long skan = null;
        boolean flag;
        do {
            flag = false;
            try {
                skan = scan.nextLong();
            } catch (NumberFormatException e) {
                System.out.println("Blad! Wprowadz tylko wartosci liczbowe");
                flag = true;
            }
        } while (flag);
        System.out.println(account.firstName + ", chcesz wpłacić " + skan + "zł");
        if(contiune()) {
            account.balance += skan;
            System.out.println("Transakcja przebiegła pomyślnie!");
            System.out.println("Twoje saldo wynosi teraz: " + account.balance + "zł");
            updateDatabase(myList);
        }
    }

    void withdraw() throws IOException, ClassNotFoundException {
        int i = findByID();
        if (i==-1) {
            return;
        }
        Account account = myList.get(i);
        System.out.println("Twoje saldo wynosi: " + account.balance);
        System.out.println("Ile pieniędzy chcesz wypłacić?");
        Scanner scan = new Scanner(System.in);

        while (!scan.hasNextInt()){
            scan.next();
            System.out.println("Błąd! Wprowadz tylko wartosci liczbowe");
        }
        int reading = scan.nextInt();
        System.out.println(account.firstName + ", chcesz wypłacić " + reading + "zł.");
        if(contiune()) {
            if (reading > account.balance) {
                System.out.println("Nie masz wystarczających środków na koncie!");
            } else {
                account.balance -= reading;
                System.out.println("Transakcja przebiegła pomyślnie!");
                System.out.println("Twoje saldo wynosi teraz: " + account.balance);
                updateDatabase(myList);
            }
        }

    }

    void transfer() throws IOException, ClassNotFoundException {
        int i = findByID();
        if (i==-1) {
            return;
        }
        Account account = myList.get(i);
        System.out.println("Twoje saldo wynosi: " + account.balance);
        System.out.println("Gdzie chcesz przelać pieniądze?");
        int j = findByID();
        if (j==-1) {
            return;
        }
        Account account2 = myList.get(j);
        System.out.println("Ile pieniędzy chcesz przelać?");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()){
            scan.next();
            System.out.println("Błąd! Wprowadz tylko wartosci liczbowe");
        }
        int skan = scan.nextInt();
        System.out.println(account.firstName + ", chcesz przelać " + skan + "zł.");
        if(contiune()) {
            if (skan > account.balance) {
                System.out.println("Nie masz wystarczających środków na koncie!");
            } else {
                account.balance -= skan;
                account2.balance += skan;
                System.out.println("Transakcja przebiegła pomyślnie!");
                updateDatabase(myList);
            }
        }
    }


    void saveAccount (Account account)throws IOException, ClassNotFoundException{
        ArrayList <Account> tempList = new ArrayList<Account>();
        ObjectInputStream pl2=null;
        try{
            pl2=new ObjectInputStream(new FileInputStream("database"));
            while(true)
                tempList.add((Account)pl2.readObject());

        }catch (EOFException ex) {
            if(pl2!=null)
                pl2.close();

            ObjectOutputStream pl=null;
            try{
                pl=new ObjectOutputStream(new FileOutputStream("database"));
                for (Account aTempList : tempList) {
                    pl.writeObject(aTempList);
                }
                pl.writeObject(account);
                pl.flush();
            }
            finally{
                if(pl!=null)
                    pl.close();
            }
        }
    }

    void updateDatabase (ArrayList<Account> list)throws IOException, ClassNotFoundException{
        ObjectOutputStream pl=null;

        try{
            pl=new ObjectOutputStream(new FileOutputStream("database"));
            for (Account aList : list) {
                pl.writeObject(aList);
            }
        }
        finally{
            if(pl!=null)
                pl.close();
        }
    }

    ArrayList <Account> readFile(String fileName)throws IOException,ClassNotFoundException{
        ObjectInputStream pl2=null;
        Account x = null;
        try{
            pl2=new ObjectInputStream(new FileInputStream(fileName));
            while(true){
                x=(Account)pl2.readObject();
                if(!boolFindByID(x.clientID)){
                    myList.add(x);
                }
            }
        } catch (EOFException ex) {
            System.out.println("Koniec pliku");
        }

        finally{
            if(pl2!=null)
                pl2.close();
        }
        return myList;
    }
}
