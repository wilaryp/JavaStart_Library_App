package io.file;

import exception.NoSuchFileTypeException;
import io.ConsolePrinter;
import io.DataReader;

public class FileManagerBuilder {
    private ConsolePrinter printer;
    private DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build(){
        printer.printLine("Wybierz format danych:");
        FileType fileType =  getFileType();
        switch (fileType) {
            case SERIAL:
                return new SerializableFIleManager();
            case CSV:
                return new CsvFileManager();
            default:
                throw new NoSuchFileTypeException("Nieobs�ugiwany typ danych");
        }

    }
    //wczytanie od u�ytkownika formatu pliku w kt�rym chce zapisywa� i odczytywa� dane
    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            //serial, SERIAL
            String type = reader.getString().toUpperCase();
            try {
                //do zamiany Stringa na enuma s�u�y metoda valueOf, kt�r� wywo�ujemy na naszym enumie
                //przekazujemy typ przekazany przez u�ytkownika (type)
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e){
                printer.printLine("Nieobs�ugiwany typ danych, wybierz ponownie");
            }
        } while (!typeOk);
        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.name());
        }
    }
}
