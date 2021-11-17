package main.Repository.Inheritable;

import main.Model.Identifiable;

import java.util.Collection;

public interface ICrudRepository<Tid, T extends Identifiable<Tid>> {
    T add(T elem);

    void delete(T elem);

    void update(T elem, Tid id);

    T findById(Tid id);

    Iterable<T> findAll();

    Collection<T> getAll();
}