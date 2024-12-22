package app;

import exception.DataExportException;
import exception.DataImportException;
import exception.InvalidDataException;
import exception.NoSuchOptionException;
import io.ConsolePrinter;
import io.DataReader;
import io.file.FileManager;
import io.file.FileManagerBuilder;
import model.Library;
import model.Book;
import model.Magazine;
import model.Publication;


import java.util.InputMismatchException;

public class LibraryControl {

/*  najpierw były poniższe stałe, przed wprowadzeniem typu wyliczeniowego Enum (w tym przypadku Option)
    //pola do pętli while - menu
    private final int EXIT = 0;
    private final int ADD_BOOK = 1;
    private final int PRINT_BOOKS = 2;
    private final int ADD_MAGAZINE = 3;
    private final int PRINT_MAGAZINES = 4;
*/
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private FileManager fileManager;

    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, dataReader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Zaimportowano dane z pliku");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("Zainicjowano nową bazę.");
            library = new Library();
        }

    }

    void controlLoop(){
        //wcześniej przed wprowadzeniem typu enum (Option) był typ int.
        // int option;
        Option option;
        do {
            printOptions();
            option = getOption();
            //(dataReader.getInt()) - w powyższym zwracamy int, którego poda nam użytkownik ale obecnie musi to być typ Enum (Option)
            // wcześniej było poprostu: option = dataReader.getInt();
            switch (option) {
                case ADD_BOOK:
                    addBook();
                    break;
                case PRINT_BOOKS:
                    printBooks();
                    break;
                case ADD_MAGAZINE:
                    addMagazine();
                    break;
                case PRINT_MAGAZINES:
                    printMagazines();
                    break;
                case EXIT:
                    exit();
                    break;
                default:
                    printer.printLine("Nie ma takiej opcji, wprowadź ponownie");
            }
        }
        while (option != Option.EXIT);
        //przed typem wyliczeniowym było while (option != EXIT); - ale teraz też by działało,
        // nie wiem czemu zmieniamy na Option.EXIT
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;
        while (!optionOk){
            try{
                //W zasadzie to wywołane są tutaj dwie metody:
                //dataReader.getInt() - wczytanie od użytkownika liczby
                //Option.createFromInt(); - zamiana liczby na wartość typu Option
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e){
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("Wprowadzono wartość, która nie jest liczbą, podaj ponownie: ");
            }
        }
        return option;
    }

    private void exit() {
        //eksport danych do pliku po wyjściu z programu:
        try {
            printer.printLine("Eksport danych do pliku zakończony powodzeniem");
            fileManager.exportData(library);
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Koniec programu, papa!");
        dataReader.close();
    }

    private void printBooks() {
        Publication[] publications = library.getPublications();
        printer.printBooks(publications);

    }

    private void addBook() {
        try{
            Book book = dataReader.readAndCreateBook();
            library.addPublication(book);
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć książki, niepoprawne dane.");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit pojemności, nie mozna dodać kolejnej książki");
        }
    }

    private void printMagazines() {
        Publication[] publications = library.getPublications();
        printer.printMagazines(publications);
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addPublication(magazine);
        } catch (InputMismatchException e){
            printer.printLine("Nie udało się utworzyć magazynu, niepoprawne dane.");
        } catch (ArrayIndexOutOfBoundsException e){
            printer.printLine("Osiągnięto limit polemności, nie można dodać kolejnegp magazynu");
        }

    }

    // wyświetlanie menu
    private void printOptions() {
        //przed wprowadzeniem enum było np. System.out.println(EXIT + " - wyjście z programu");
        printer.printLine("Wybierz opcję:");
        for (Option option: Option.values()
             ) {
            printer.printLine(option.toString());
        }
    }

    public enum Option {
        EXIT (0, "wyjście z programu"),
        ADD_BOOK (1, "dodanie nowej książki"),
        PRINT_BOOKS (2, "wyświetl dostępne książki"),
        ADD_MAGAZINE(3, "dodanie nowego magazynu"),
        PRINT_MAGAZINES(4, "wyświetl dostępne magazyny");

        private int value;
        private String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        public int getValue() {
            return value;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }

        // w LibraryControl - będziemy porównywali już nie int option tylko wartość typu enum do innych wartości,
        // więc musimy mieć metodę, która zamieni liczbę na jedną z wartości wymienioną na samej górze.
        // metoda jest statyczna więc nie musimy jej wywoływać Option.EXIT.coś tam, tylko będziemy się odwoływali
        // do tej metody przez typ Option. Metoda będzie zwracałą jedną z wyższych wartości, czyli coś typu OPTION
        // Option.values() - zwraca nam tablicę wszystkich wartości w danym typie wyliczeniowym. Dzięki temu,
        // że opcje wyżej są ponumerowane od 0-4 to możemy się odwołać do konkretnego elementu tej tablicy
        // zwracanej przez values() - Zostanie zwrócona jedna z wartości czyli EXIT, ADD_BOOK itd.
        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("Brak opcji o id " + option);
            }

        }
    }
}
