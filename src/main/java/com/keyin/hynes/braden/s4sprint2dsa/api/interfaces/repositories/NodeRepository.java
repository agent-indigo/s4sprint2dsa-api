package com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities.NodeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NodeRepository<T> extends MongoRepository<NodeEntity<T>, String> {
    List<NodeEntity<T>> findAllByValue(T value);
    NodeEntity<T> findByLeft(NodeEntity<T> left);
    NodeEntity<T> findByRight(NodeEntity<T> right);
    List<NodeEntity<T>> findAllByHeight(int height);
    int deleteAllByValue(T value);
    void deleteByLeft(NodeEntity<T> left);
    void deleteByRight(NodeEntity<T> right);
    int deleteAllByHeight(int height);
}