package io.file;

import model.Library;

public interface FileManager {
    Library importData(); // metoda do odczytu danych
    void exportData(Library library); // metoda do zapisu danych
}
