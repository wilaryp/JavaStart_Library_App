package model;

import exception.PublicationAlreadyExistsException;
import exception.UserAlreadyExistsException;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private Map<String, Publication>  publications = new HashMap<>();
    private Map<String, LibraryUser>  users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Collection<Publication> getSortedPublications(Comparator<Publication> comparator){
       List<Publication> list =  new ArrayList<>(this.publications.values());
       list.sort(comparator);
       return list;

    }

    public Optional<Publication> findPublicationByTitle(String title){
        return Optional.ofNullable(publications.get(title));
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public Collection<LibraryUser> getSortedUsers(Comparator<LibraryUser> comparator){
        ArrayList<LibraryUser> list = new ArrayList<>(users.values());
        list.sort(comparator);
        return list;
    }

    //dla ćwiczenia, chcemy mieć po 1 egzemplarzu 1 ksiażki
    public void addPublication(Publication publication){
        if (publications.containsKey(publication.getTitle())){
            throw new PublicationAlreadyExistsException(
                    "Publikacja o takim tytaule już istnieje " + publication.getTitle()
            );
        }
        publications.put(publication.getTitle(), publication);
    }

    public void addUser(LibraryUser user){
        if (users.containsKey(user.getPesel())){
            throw new UserAlreadyExistsException(
                    "Uzytkownik ze wskazanym peselem juz istnieje " + user.getPesel()
            );
        }
        users.put(user.getPesel(), user);
    }

    public boolean removePublication(Publication pub){
        if (publications.containsValue(pub)){
            publications.remove(pub.getTitle());
            return true;
        } else {
            return false;
        }
    }

}
