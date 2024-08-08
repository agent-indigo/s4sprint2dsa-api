package com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.abstracts.DataEntity;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public final class NodeEntity<T> extends DataEntity {
    private T value;
    private NodeEntity<T> left;
    private NodeEntity<T> right;
    private int height;
    public NodeEntity(
        T value,
        NodeEntity<T> left,
        NodeEntity<T> right,
        int height
    ) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.height = height;
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
}