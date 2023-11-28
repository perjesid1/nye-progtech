package nye.progtech.wumpus;

import java.util.*;

public class Users implements List<User> {
    List<User> usersList = new ArrayList<>();

    @Override
    public int size() {
        return usersList.size();
    }

    @Override
    public boolean isEmpty() {
        return usersList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return usersList.contains(o);
    }

    @Override
    public Iterator<User> iterator() {
        return usersList.iterator();
    }

    @Override
    public Object[] toArray() {
        return usersList.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return usersList.toArray(a);
    }

    @Override
    public boolean add(User t) {
        return usersList.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return usersList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(usersList).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends User> c) {
        return usersList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends User> c) {
        return usersList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return usersList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return usersList.retainAll(c);
    }

    @Override
    public void clear() {
        usersList.clear();
    }


    public int getSize(){
        return this.usersList.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users users)) return false;
        return Objects.equals(usersList, users.usersList);
    }

    @Override
    public User get(int index) {
        return usersList.get(index);
    }

    @Override
    public User set(int index, User element) {
        return usersList.set(index, element);
    }

    @Override
    public void add(int index, User element) {
        usersList.add(index, element);
    }

    @Override
    public User remove(int index) {
        return usersList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return usersList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return usersList.lastIndexOf(o);
    }

    @Override
    public ListIterator<User> listIterator() {
        return usersList.listIterator();
    }

    @Override
    public ListIterator<User> listIterator(int index) {
        return usersList.listIterator(index);
    }

    @Override
    public List<User> subList(int fromIndex, int toIndex) {
        return usersList.subList(fromIndex,toIndex);
    }

    public boolean userExists(String userName){
        int userIndex = this.indexOf(new User(userName));
        return userIndex != -1;
    }

    public void setHighScore(String userName, int highScore){
        int userIndex = this.indexOf(new User(userName, highScore));
        if(userIndex == -1){
            this.add(new User(userName, highScore));
        }
        else {
            this.set(userIndex, new User(userName, highScore));
        }
    }
    public int getHighScore(String userName){
        int userIndex = this.indexOf(new User(userName));
        if(userIndex == -1){
            return 0;
        }
        else {
            return this.get(userIndex).getHighScore();
        }
    }

    public User getElement(int index){
        return this.usersList.get(index);
    }
}