package io.file;

import exception.DataExportException;
import model.Library;

import java.io.*;

public class SerializableFIleManager implements FileManager{
    private static final String FILE_NAME = "Library.o";

    @Override
    public Library importData() {
         try (
              FileInputStream fis = new FileInputStream(FILE_NAME);
              ObjectInputStream ois = new ObjectInputStream(fis);
              ){
            return (Library)ois.readObject(); //rzutowanie na obiekt Library, bo metoda readObject zwraca referencję typu Object
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportData(Library library) {
        try(
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ){
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku " + FILE_NAME);
        } catch (IOException e) {
            throw new DataExportException("Brak zapisu danych do pliku " + FILE_NAME);
        }

    }
}
