package io;

import model.Book;
import model.LibraryUser;
import model.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer; // tutaj mogłoby być poprostu "private ConsolePrinter printer = new ConsolePrinter();

    //wstrzykiwanie zależności, żeby nie tworzyć obiektu ConsolePrinter, bo chcemy skorzystać z metody tam się znajdującej "printer"
    //obiekt DataReader, zależy od obiektu ConsolePrinter, wiec taki obiekt powinniśmy dostarczyć przez konstruktor
    //jeżeli chcemy korzystać z obiektu DataReader, najpierw musisz mieć obiekt ConsolePrinter
    //Powyższy komentarz dotyczy tego konstrukotra, to jest to wstrzykiwanie.
    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreateBook(){
            //Book book = new Book();
            printer.printLine("Tytuł: ");
            String title = sc.nextLine();
            printer.printLine("Autor:");
            String author = sc.nextLine();
            printer.printLine("Wydawnictwo");
            String publisher = sc.nextLine();
            printer.printLine("ISBN");
            String isbn = sc.nextLine();
            printer.printLine("Rok wydania");
            int releaseDate = getInt();
            printer.printLine("Liczba stron:");
            int pages = getInt();
            return new Book(title, author, releaseDate, pages, publisher, isbn);
            //return book;
    }

    public Magazine readAndCreateMagazine(){
        //Book book = new Book();
        printer.printLine("Tytuł: ");
        String title = sc.nextLine();
        printer.printLine("Wydawnictwo: ");
        String publisher = sc.nextLine();
        printer.printLine("Język: ");
        String language = sc.nextLine();
        printer.printLine("Rok wydania: ");
        int year = getInt();
        printer.printLine("Miesiąc: ");
        int month = getInt();
        printer.printLine("Dzień: ");
        int day = getInt();
        return new Magazine(title, publisher, language, year, month, day);
        //return book;
    }

    public LibraryUser createLibraryUser(){
        printer.printLine("Imię");
        String firstName = sc.nextLine();
        printer.printLine("Nazwisko");
        String lastName = sc.nextLine();
        printer.printLine("Pesel");
        String pesel = sc.nextLine();
        return new LibraryUser(firstName, lastName, pesel);
    }

    public int getInt(){
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public String getString(){
        return sc.nextLine();
    }

    public void close(){
        sc.close();
    }
}
