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
        root = new NodeEntity<T>();
        newNode = new NodeEntity<T>();
        queue = new LinkedList<NodeEntity<T>>();
        nodes = new LinkedList<>();
        nodeInsertedMessage = "Node inserted.";
        nodeDeletedMessage = "Node deleted.";
        result = "Error";
        root.setTreeId(getId());
        newNode.setTreeId(getId());
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
            current = queue.remove();
            nodes.add(current);
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        return nodes;
    }
    public String search(T value) {
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.getValue().equals(value)) {
                result = "Value found.";
                return result;
            }
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        result = "Value not found.";
        return result;
    }
    public String insert(T value) {
        newNode.setValue(value);
        newNode.setLeft(null);
        newNode.setRight(null);
        newNode.setHeight(1); // Assuming height starts from 1 for a new node

        if (queue.isEmpty()) {
            queue.add(newNode);
            result = nodeInsertedMessage;
        } else {
            while (!queue.isEmpty()) {
                current = queue.remove();
                if (current.getLeft() == null) {
                    current.setLeft(newNode);
                    updateHeight(current);
                    result = nodeInsertedMessage;
                    break;
                } else if (current.getRight() == null) {
                    current.setRight(newNode);
                    updateHeight(current);
                    result = nodeInsertedMessage;
                    break;
                } else {
                    queue.add(current.getLeft());
                    queue.add(current.getRight());
                }
            }
        }
        return result;
    }
    private void updateHeight(NodeEntity<T> node) {
        if (node != null) {
            node.setHeight(Math.max(
                (node.getLeft() != null) ? node.getLeft().getHeight() : 0,
                (node.getRight() != null) ? node.getRight().getHeight() : 0
            ) + 1);
        }
    }
    public NodeEntity<T> getDeepestNode() {
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        return current;
    }
    public String deleteDeepestNode() {
        while (!queue.isEmpty()) {
            previous = current;
            current = queue.remove();
            if (current.getLeft() == null) {
                previous.setRight(null);
                result = nodeDeletedMessage;
                break;
            } else if (current.getRight() == null) {
                current.setLeft(null);
                result = nodeDeletedMessage;
                break;
            }
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        return result;
    }
    public String deleteNodeWithValue(T value) {
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.getValue().equals(value)) {
                current.setValue(getDeepestNode().getValue());
                deleteDeepestNode();
                result = nodeDeletedMessage;
                return result;
            }
            if (current.getLeft() != null) queue.add(current.getLeft());
            if (current.getRight() != null) queue.add(current.getRight());
        }
        result = "Node not found.";
        return result;
    }
    public String deleteTree() {
        root = null;
        return "Tree deleted.";
    }
}