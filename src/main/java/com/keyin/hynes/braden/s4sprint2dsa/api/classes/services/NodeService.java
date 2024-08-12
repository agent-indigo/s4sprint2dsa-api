package com.keyin.hynes.braden.s4sprint2dsa.api.classes.services;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities.NodeEntity;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.Deletes;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories.NodeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public final class NodeService<T> implements Deletes {
    @Autowired
    private NodeRepository<T> repo;
    private NodeEntity<T> current;
    private String nodeDeletedMessage;
    private String nodesDeletedMessage;
    public NodeService() {
        nodeDeletedMessage = "Node deleted";
        nodesDeletedMessage = "Nodes deleted.";
    }
    /**
     * @name    getAll
     * @desc    Get all nodes
     * @route   GET /api/nodes
     * @access  public
     */
    public List<NodeEntity<T>> getAll() {
        return repo.findAll();
    }
    /**
     * @name    getById
     * @desc    Get the node with the given ID
     * @route   GET /api/nodes/{id}
     * @access  public
     */
    public NodeEntity<T> getById(ObjectId id) {
        return repo.findById(id).get();
    }
    /**
     * @name    getAllByValue
     * @desc    Get all nodes containing the given value
     * @route   GET /api/nodes/{value}
     * @access  public
     */
    public List<NodeEntity<T>> getAllByValue(T value) {
        return repo.findAllByValue(value);
    }
    /**
     * @name    getByLeft
     * @desc    Get the node to the right of the given node
     * @route   GET /api/nodes/{left}
     * @access  public
     */
    public NodeEntity<T> getByLeft(NodeEntity<T> left) {
        return repo.findByLeft(left);
    }
    /**
     * @name    getByRight
     * @desc    Get the node to the left of the given node
     * @route   GET /api/nodes/{right}
     * @access  public
     */
    public NodeEntity<T> getByRight(NodeEntity<T> right) {
        return repo.findByRight(right);
    }
    /**
     * @name    getAllByHeight
     * @desc    Get all nodes of the given height
     * @route   GET /api/nodes/{height}
     * @access  public
     */
    public List<NodeEntity<T>> getAllByHeight(int height) {
        return repo.findAllByHeight(height);
    }
    /**
     * @name    getAllByTreeId
     * @desc    Get all nodes in the given tree
     * @route   GET /api/nodes/{treeId}
     * @access  public
     */
    public List<NodeEntity<T>> getAllByTreeId(ObjectId treeId) {
        return repo.findAllByTreeId(treeId);
    }
    /**
     * @name    add
     * @desc    Add a node
     * @route   POST /api/nodes
     * @access  public
     */
    public NodeEntity<T> add(NodeEntity<T> node) {
        return repo.save(node);
    }
    /**
     * @name    edit
     * @desc    Edit a node
     * @route   PUT /api/nodes/{id}
     * @access  public
     */
    public NodeEntity<T> edit(
        ObjectId id,
        NodeEntity<T> node
    ) {
        current = repo.findById(id).get();
        current.setValue(node.getValue());
        current.setLeft(node.getLeft());
        current.setRight(node.getRight());
        current.setHeight(node.getHeight());
        return repo.save(current);
    }
    /**
     * @name    editValue
     * @desc    Edit a node's value
     * @route   PATCH /api/nodes/{id}/value
     * @access  public
     */
    public NodeEntity<T> editValue(
        ObjectId id,
        T value
    ) {
        current = repo.findById(id).get();
        current.setValue(value);
        return repo.save(current);
    }
    /**
     * @name    editLeft
     * @desc    Move a node to the right of another node
     * @route   PATCH /api/nodes/{id}/left
     * @access  public
     */
    public NodeEntity<T> editLeft(
        ObjectId id,
        NodeEntity<T> left
    ) {
        current = repo.findById(id).get();
        current.setLeft(left);
        return repo.save(current);
    }
    /**
     * @name    editRight
     * @desc    Move a node to the right of another node
     * @route   PATCH /api/nodes/{id}/right
     * @access  public
     */
    public NodeEntity<T> editRight(
        ObjectId id,
        NodeEntity<T> right
    ) {
        current = repo.findById(id).get();
        current.setRight(right);
        return repo.save(current);
    }
    /**
     * @name    editHeight
     * @desc    Edit a node's height
     * @route   PATCH /api/nodes/{id}/height
     * @access  public
     */
    public NodeEntity<T> editHeight(
        ObjectId id,
        int height
    ) {
        current = repo.findById(id).get();
        current.setHeight(height);
        return repo.save(current);
    }
    /**
     * @name    deleteAll
     * @desc    Delete all nodes
     * @route   DELETE /api/nodes
     * @access  public
     */
    @Override
    public String deleteAll() {
        repo.deleteAll();
        return nodesDeletedMessage;
    }
    /**
     * @name    deleteByid
     * @desc    Delete the node with the given ID
     * @route   DELETE /api/nodes/{id}
     * @access  public
     */
    @Override
    public String deleteById(ObjectId id) {
        repo.deleteById(id);
        return nodeDeletedMessage;
    }
    /**
     * @name    deleteAllByValue
     * @desc    Delete all nodes containing the given value
     * @route   DELETE /api/nodes/{value}
     * @access  public
     */
    public String deleteAllByValue(T value) {
        return repo.deleteAllByValue(value) + " " + nodesDeletedMessage;
    }
    /**
     * @name    deleteByLeft
     * @desc    Delete the node to the right of the given node
     * @route   DELETE /api/nodes/{left}
     * @access  public
     */
    public String deleteByLeft(NodeEntity<T> left) {
        repo.deleteByLeft(left);
        return nodeDeletedMessage;
    }
    /**
     * @name    deleteByRight
     * @desc    Delete the node to the left of the given node
     * @route   DELETE /api/nodes/{right}
     * @access  public
     */
    public String deleteByRight(NodeEntity<T> right) {
        repo.deleteByRight(right);
        return nodeDeletedMessage;
    }
    /**
     * @name    deleteAllByHeight
     * @desc    Delete all nodes of the given height
     * @route   DELETE /api/nodes/{height}
     * @access  public
     */
    public String deleteAllByHeight(int height) {
        return repo.deleteAllByHeight(height) + " " + nodesDeletedMessage;
    }
    /**
     * @name    deleteAllByTreeId
     * @desc    Delete all nodes in the given tree
     * @route   DELETE /api/nodes/{treeId}
     * @access  public
     */
    public String deleteAllByTreeId(ObjectId treeId) {
        return repo.deleteAllByTreeId(treeId) + " " + nodesDeletedMessage;
    }
}