package persistence;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
    long save(T t);
}
