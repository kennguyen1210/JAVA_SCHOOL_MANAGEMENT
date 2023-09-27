package ra.Service;

import java.util.List;

public interface IService<T , E> {
    List<T> getAll();
    void delete(E id);
    void save(T t);
    T findById(E id);
}
