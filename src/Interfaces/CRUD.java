package Interfaces;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface CRUD <T> {
    public void add(T t);
    public void delete(T t);
    public List<T> read();
    public void update(T t);

}
