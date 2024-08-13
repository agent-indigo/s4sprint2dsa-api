package com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories;
import com.keyin.hynes.braden.s4sprint2dsa.api.entities.NodeEntity;
import com.keyin.hynes.braden.s4sprint2dsa.api.entities.TreeEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TreeRepository<T> extends MongoRepository<TreeEntity<T>, ObjectId> {
    TreeEntity<T> findByNode(NodeEntity<T> node);
    void deleteByNode(NodeEntity<T> node);
}