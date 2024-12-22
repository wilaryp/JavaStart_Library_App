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
                throw new NoSuchFileTypeException("Nieobs³ugiwany typ danych");
        }

    }
    //wczytanie od u¿ytkownika formatu pliku w którym chce zapisywaæ i odczytywaæ dane
    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;
        do {
            printTypes();
            //serial, SERIAL
            String type = reader.getString().toUpperCase();
            try {
                //do zamiany Stringa na enuma s³u¿y metoda valueOf, któr¹ wywo³ujemy na naszym enumie
                //przekazujemy typ przekazany przez u¿ytkownika (type)
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e){
                printer.printLine("Nieobs³ugiwany typ danych, wybierz ponownie");
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
