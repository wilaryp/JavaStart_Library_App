package io.file;

import exception.DataExportException;
import model.Library;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableFIleManager implements FileManager{
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
        return null;
    }

    @Override
    public void exportData(Library library) {

        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Brak zapisu danych do pliku " + FILE_NAME);;
        }

    }
}
