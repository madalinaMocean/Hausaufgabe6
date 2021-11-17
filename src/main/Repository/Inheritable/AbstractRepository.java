package main.Repository.Inheritable;

import main.Repository.RepositoryException;
import main.Model.Identifiable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbstractRepository<Id, K extends Identifiable<Id>> implements ICrudRepository<Id,K> {
    protected Map <Id,K> elem;
    public AbstractRepository(){
        elem=new HashMap<>();
    }
    public K add(K value){
        if (elem.containsKey(value.getId())) {
            throw new RepositoryException("element already exists" + value);
        }
        else
            elem.put(value.getId(),value);
            return value;
    }

    public void delete(K value){
        if(elem.containsKey(value.getId()))
            elem.remove(value.getId());
    }

    public void update(K value,Id id){
        if(elem.containsKey(id)){
            value.setId(id);
            elem.replace(id,value);
        }
        else
            throw new RepositoryException("Element doesn’t exist"+id);
    }
    public K findById( Id id){
        if(elem.containsKey(id))
            return elem.get(id);
        else
            throw new RepositoryException("Element doesn't exist"+id);
    }
    public Iterable<K> findAll() {
        return elem.values();
    }

    @Override
    public Collection<K> getAll() {
        return elem.values();
    }

}
