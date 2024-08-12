package com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities.NodeEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface NodeRepository<T> extends MongoRepository<NodeEntity<T>, ObjectId> {
    List<NodeEntity<T>> findAllByValue(T value);
    NodeEntity<T> findByLeft(NodeEntity<T> left);
    NodeEntity<T> findByRight(NodeEntity<T> right);
    List<NodeEntity<T>> findAllByHeight(int height);
    List<NodeEntity<T>> findAllByTreeId(ObjectId treeId);
    long deleteAllByValue(T value);
    void deleteByLeft(NodeEntity<T> left);
    void deleteByRight(NodeEntity<T> right);
    long deleteAllByHeight(int height);
    long deleteAllByTreeId(ObjectId treeId);
}