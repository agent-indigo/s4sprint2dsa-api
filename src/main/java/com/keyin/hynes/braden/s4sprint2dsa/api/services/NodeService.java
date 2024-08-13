package com.keyin.hynes.braden.s4sprint2dsa.api.services;
import com.keyin.hynes.braden.s4sprint2dsa.api.entities.NodeEntity;
import com.keyin.hynes.braden.s4sprint2dsa.api.entities.TreeEntity;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.Deletes;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories.NodeRepository;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.repositories.TreeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public final class NodeService<T> implements Deletes {
    @Autowired
    private NodeRepository<T> nodeRepository;
    @Autowired
    private TreeRepository<T> treeRepository;
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
        return nodeRepository.findAll();
    }
    /**
     * @name    getById
     * @desc    Get the node with the given ID
     * @route   GET /api/nodes/{id}
     * @access  public
     */
    public NodeEntity<T> getById(ObjectId id) {
        return nodeRepository.findById(id).get();
    }
    /**
     * @name    getAllByValue
     * @desc    Get all nodes containing the given value
     * @route   GET /api/nodes/{value}
     * @access  public
     */
    public List<NodeEntity<T>> getAllByValue(T value) {
        return nodeRepository.findAllByValue(value);
    }
    /**
     * @name    getAllByHeight
     * @desc    Get all nodes of the given height
     * @route   GET /api/nodes/{height}
     * @access  public
     */
    public List<NodeEntity<T>> getAllByHeight(int height) {
        return nodeRepository.findAllByHeight(height);
    }
    /**
     * @name    getAllByTreeId
     * @desc    Get all nodes in the given tree
     * @route   GET /api/nodes/{treeId}
     * @access  public
     */
    public List<NodeEntity<T>> getAllByTreeId(ObjectId treeId) {
        return nodeRepository.findAllByTreeId(treeId);
    }
    /**
     * @name    add
     * @desc    Add a node
     * @route   POST /api/nodes
     * @access  public
     */
    public NodeEntity<T> add(NodeEntity<T> node) {
        return nodeRepository.save(node);
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
        current = nodeRepository.findById(id).get();
        current.setValue(node.getValue());
        current.setLeft(node.getLeft());
        current.setRight(node.getRight());
        current.setHeight(node.getHeight());
        return nodeRepository.save(current);
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
        current = nodeRepository.findById(id).get();
        current.setValue(value);
        return nodeRepository.save(current);
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
        current = nodeRepository.findById(id).get();
        current.setLeft(left);
        return nodeRepository.save(current);
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
        current = nodeRepository.findById(id).get();
        current.setRight(right);
        return nodeRepository.save(current);
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
        current = nodeRepository.findById(id).get();
        current.setHeight(height);
        return nodeRepository.save(current);
    }
    /**
     * @name    deleteAll
     * @desc    Delete all nodes
     * @route   DELETE /api/nodes
     * @access  public
     */
    @Override
    public String deleteAll() {
        treeRepository.deleteAll();
        nodeRepository.deleteAll();
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
        treeRepository.findById(nodeRepository.findById(id).get().getTreeId()).get().deleteNodeById(id);
        nodeRepository.deleteById(id);
        return nodeDeletedMessage;
    }
    /**
     * @name    deleteAllByValue
     * @desc    Delete all nodes containing the given value
     * @route   DELETE /api/nodes/{value}
     * @access  public
     */
    public String deleteAllByValue(T value) {
        for (TreeEntity<T> tree : treeRepository.findAll()) tree.deleteNodesByValue(value);
        return nodeRepository.deleteAllByValue(value) + " " + nodesDeletedMessage;
    }
}