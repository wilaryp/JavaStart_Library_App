package io.file;

import model.Library;

interface FileManager {
    Library importData();
    void exportData(Library library);
}
