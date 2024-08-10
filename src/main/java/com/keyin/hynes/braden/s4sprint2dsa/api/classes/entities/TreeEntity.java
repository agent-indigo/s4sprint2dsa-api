package com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.abstracts.DataEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
@Document
public final class TreeEntity<T> extends DataEntity {
    private NodeEntity<T> root;
    private NodeEntity<T> current;
    private NodeEntity<T> previous;
    private NodeEntity<T> newNode;
    private List<NodeEntity<T>> nodes;
    private Queue<NodeEntity<T>> queue;
    private String nodeInsertedMessage;
    private String nodeDeletedMessage;
    private String result;
    public TreeEntity() {
        this.root = new NodeEntity<T>();
        this.newNode = new NodeEntity<T>();
        this.queue = new LinkedList<NodeEntity<T>>();
        this.nodeInsertedMessage = "Node inserted.";
        this.nodeDeletedMessage = "Node deleted.";
        this.result = "Error";
        queue.add(root);
    }
    public List<NodeEntity<T>> traversePreOrder(NodeEntity<T> node) {
        if (node != null) {
            nodes.add(node);
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
        return nodes;
    }
    public List<NodeEntity<T>> traverseInOrder(NodeEntity<T> node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            nodes.add(node);
            traverseInOrder(node.getRight());
        }
        return nodes;
    }
    public List<NodeEntity<T>> traversePostOrder(NodeEntity<T> node) {
        if (node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            nodes.add(node);
        }
        return nodes;
    }
    public List<NodeEntity<T>> traverseLevelOrder() {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            nodes.add(current);
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        return nodes;
    }
    public String search(T value) {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            if (current.getValue() == value) {
                this.result = "Value found.";
            } else {
                this.result = "Value not found.";
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
        return result;
    }
    public String insert(T value) {
        this.newNode.setValue(value);
        if (queue.isEmpty()) {
            queue.add(newNode);
            this.result = nodeInsertedMessage;
        } else {
            while (!queue.isEmpty()) {
                this.current = queue.remove();
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    this.result = nodeInsertedMessage;
                    break;
                } else if (current.getRight() == null) {
                    current.setRight(newNode);
                    this.result = nodeInsertedMessage;
                    break;
                } else {
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                }
            }
        }
        return result;
    }
    public NodeEntity<T> getDeepestNode() {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        return current;
    }
    public String deleteDeepestNode() {
        while (!queue.isEmpty()) {
            this.previous = this.current;
            this.current = queue.remove();
            if (current.getLeft() == null) {
                previous.setRight(null);
                this.result = nodeDeletedMessage;
            } else if (current.getRight() == null) {
                current.setLeft(null);
                this.result = nodeDeletedMessage;
            }
            queue.add(current.getLeft());
            queue.add(current.getRight());
        }
        return result;
    }
    public String deleteNodeWithValue(T value) {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            if (current.getValue() == value) {
                current.setValue(getDeepestNode().getValue());
                deleteDeepestNode();
                this.result = nodeDeletedMessage;
            } else {
                this.result = "Node not found.";
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
        return result;
    }
    public String deleteTree() {
        this.root = null;
        return "Tree deleted.";
    }
}