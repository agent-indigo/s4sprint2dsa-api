package com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.abstracts.DataEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Queue;
import java.util.LinkedList;
@Document
public final class TreeEntity<T> extends DataEntity {
    private NodeEntity<T> root;
    private NodeEntity<T> current;
    private NodeEntity<T> previous;
    private NodeEntity<T> newNode;
    private Queue<NodeEntity<T>> queue;
    private String traversalFormat;
    private String insertionMessage;
    public TreeEntity() {
        this.root = new NodeEntity<T>();
        this.newNode = new NodeEntity<T>();
        this.queue = new LinkedList<NodeEntity<T>>();
        this.traversalFormat = "%s\n";
        this.insertionMessage = "Node inserted.";
        queue.add(root);
    }
    public void traversePreOrder(NodeEntity<T> node) {
        if (node == null) {
            return;
        } else {
            System.out.printf(traversalFormat, node.getValue().toString());
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }
    public void traverseInOrder(NodeEntity<T> node) {
        if (node == null) {
            return;
        } else {
            traverseInOrder(node.getLeft());
            System.out.printf(traversalFormat, node.getValue().toString());
            traverseInOrder(node.getRight());
        }
    }
    public void traversePostOrder(NodeEntity<T> node) {
        if (node == null) {
            return;
        } else {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.printf(traversalFormat, node.getValue().toString());
        }
    }
    public void traverseLevelOrder() {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            System.out.printf(traversalFormat, current.getValue().toString());
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
    }
    public void search(T value) {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            if (current.getValue() == value) {
                System.out.println("Value found.");
            } else {
                System.err.println("Value not found.");
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
    }
    public void insert(T value) {
        this.newNode.setValue(value);
        if (queue.isEmpty()) {
            queue.add(newNode);
            System.out.println("Node inserted at root.");
            return;
        } else {
            while (!queue.isEmpty()) {
                this.current = queue.remove();
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    System.out.println(insertionMessage);
                    break;
                } else if (current.getRight() == null) {
                    current.setRight(newNode);
                    System.out.println(insertionMessage);
                    break;
                } else {
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                }
            }
        }
    }
    public NodeEntity<T> getDeepestNode() {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        return current;
    }
    public void deleteDeepestNode() {
        while (!queue.isEmpty()) {
            this.previous = this.current;
            this.current = queue.remove();
            if (current.getLeft() == null) {
                previous.setRight(null);
                return;
            } else if (current.getRight() == null) {
                current.setLeft(null);
                return;
            }
            queue.add(current.getLeft());
            queue.add(current.getRight());
        }
    }
    public void deleteNodeWithValue(T value) {
        while (!queue.isEmpty()) {
            this.current = queue.remove();
            if (current.getValue() == value) {
                current.setValue(getDeepestNode().getValue());
                deleteDeepestNode();
                System.out.println("Node deleted.");
            } else {
                System.err.println("Node not found.");
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
    }
    public void deleteTree() {
        this.root = null;
        System.out.println("Tree deleted.");
    }
}