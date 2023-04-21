package Modun3.service;

import java.util.List;

public interface iGenericService<E> {
    List<E> findAll();

    void save(E e);

    E findById(int id);

    void deleteById(int id);

}
