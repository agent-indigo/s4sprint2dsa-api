package com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.abstracts.DataEntity;
import com.mongodb.lang.Nullable;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public final class NodeEntity<T> extends DataEntity {
    private T value;
    @Nullable
    private NodeEntity<T> left;
    @Nullable
    private NodeEntity<T> right;
    private int height;
    private ObjectId treeId;
    public NodeEntity(
        T value,
        NodeEntity<T> left,
        NodeEntity<T> right,
        int height,
        ObjectId treeId
    ) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.height = height;
        this.treeId = treeId;
    }
    public NodeEntity(
        T value,
        int height,
        ObjectId treeId
    ) {
        this.value = value;
        this.height = height;
        this.treeId = treeId;
    }
    public NodeEntity() {}
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public NodeEntity<T> getLeft() {
        return left;
    }
    public void setLeft(NodeEntity<T> left) {
        this.left = left;
    }
    public NodeEntity<T> getRight() {
        return right;
    }
    public void setRight(NodeEntity<T> right) {
        this.right = right;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public ObjectId getTreeId() {
        return treeId;
    }
    public void setTreeId(ObjectId treeId) {
        this.treeId = treeId;
    }
}