package model;

import java.io.Serializable;

public class Library implements Serializable {
    private final int MAX_PUBLICATIONS = 2000;
    private int publicationsNumber = 0;
    private Publication[] publications = new Publication[MAX_PUBLICATIONS];

    //Zwracanie wszystkich publikacji, bez pustych miejsc w tablicy (tablica przewidziana na 2000)
    public Publication[] getPublications() {
        Publication[] result = new Publication[publicationsNumber];
        for (int i = 0; i < result.length; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addBook(Book book){
        addPublication(book);
    }

    public void addMagazine(Magazine magazine){
        addPublication(magazine);
    }

    private void addPublication(Publication publication){
        if (publicationsNumber >= MAX_PUBLICATIONS){
            throw new ArrayIndexOutOfBoundsException("Max publication exceeded" + MAX_PUBLICATIONS);
        }
        publications[publicationsNumber] = publication;
        publicationsNumber++;


    }


}
