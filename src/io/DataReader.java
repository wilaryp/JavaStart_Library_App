package io;

import model.Book;
import model.Magazine;

import java.util.Scanner;

public class DataReader {
    private Scanner sc = new Scanner(System.in);
    private ConsolePrinter printer; // tutaj mogłoby być poprostu "private ConsolePrinter printer = new ConsolePrinter();

    //wstrzykiwanie zależności, żeby nie tworzyć obiektu ConsolePrinter, bo chcemy skorzystać za metody tam się znajdującej "printer"
    //obiekt DataReader, zależy od obiektu ConsolePrinter, wiec taki obiekt powinniśmy dostarczyć przez konstruktor
    //jeżeli chcesz korzystać z obiektu DataReader, najpierw musisz mieć obiekt ConsolePrinter

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
            return new Book(releaseDate, title, publisher, author, pages,  isbn);
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
        int releaseDate = getInt();
        printer.printLine("Miesiąc: ");
        int month = getInt();
        printer.printLine("Dzień: ");
        int day = getInt();
        return new Magazine(releaseDate, title, publisher, month, day, language);
        //return book;
    }

    public int getInt(){
        try {
            return sc.nextInt();
        } finally {
            sc.nextLine();
        }
    }

    public void close(){
        sc.close();
    }
}
