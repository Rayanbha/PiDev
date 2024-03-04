package Interfaces;

import java.util.List;

public interface CRUD <T> {
    public void add(T t);
    public void delete(T t);
    public List<T> read();
    public boolean update(T t);

}
