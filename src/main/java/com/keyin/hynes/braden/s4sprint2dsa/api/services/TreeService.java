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
public final class TreeService<T> implements Deletes {
    @Autowired
    private TreeRepository<T> treeRepository;
    @Autowired
    private NodeRepository<T> nodeRepository;
    private TreeEntity<T> tree;
    private String treeDeletedMessage;
    private String nodeDeletionMessage;
    public TreeService() {
        treeDeletedMessage = "Tree deleted.";
    }
    /**
     * @name    getAll
     * @desc    Get all trees
     * @route   GET /api/trees
     * @access  public
     */
    public List<TreeEntity<T>> getAll() {
        return treeRepository.findAll();
    }
    /**
     * @name    getById
     * @desc    Get the tree with the given ID
     * @route   GET /api/trees/{id}
     * @access  public
     */
    public TreeEntity<T> getById(ObjectId id) {
        return treeRepository.findById(id).get();
    }
    /**
     * @name    getByNode
     * @desc    Get the tree containing the given node
     * @route   GET /api/trees/{node}
     * @access  public
     */
    public TreeEntity<T> getByNode(NodeEntity<T> node) {
        return treeRepository.findByNode(node);
    }
    /**
     * @name    add
     * @desc    Add a new tree
     * @route   POST /api/trees
     * @access  public
     */
    public TreeEntity<T> add(T[] values) {
        tree = new TreeEntity<>();
        for (T value : values) tree.insert(value);
        return treeRepository.save(tree);
    }
    /**
     * @name    addNode
     * @desc    Add a new node to a tree
     * @route   POST /api/trees/{id}/nodes
     * @access  public
     */
    public TreeEntity<T> addNode(
        ObjectId id,
        T value
    ) {
        tree = treeRepository.findById(id).get();
        tree.insert(value);
        return treeRepository.save(tree);
    }
    /**
     * @name    traversePreOrder
     * @desc    Pre-order traverse a tree from the given node
     * @route   GET /api/trees/{id}/traversePreOrder/{node}
     * @access  public
     */
    public List<NodeEntity<T>> traversePreOrder(
        ObjectId id,
        NodeEntity<T> node
    ) {
        return treeRepository.findById(id).get().traversePreOrder(node);
    }
    /**
     * @name    traversePostOrder
     * @desc    Post-order traverse a tree from the given node
     * @route   GET /api/trees/{id}/traversePostOrder/{node}
     * @access  public
     */
    public List<NodeEntity<T>> traversePostOrder(
        ObjectId id,
        NodeEntity<T> node
    ) {
        return treeRepository.findById(id).get().traversePostOrder(node);
    }
    /**
     * @name    traverseInOrder
     * @desc    Traverse a tree in order from the given node
     * @route   GET /api/trees/{id}/traverseInOrder/{node}
     * @access  public
     */
    public List<NodeEntity<T>> traverseInOrder(
        ObjectId id,
        NodeEntity<T> node
    ) {
        return treeRepository.findById(id).get().traverseInOrder(node);
    }
    /**
     * @name    traverseLevelOrder
     * @desc    Traverse a tree by level order
     * @route   GET /api/trees/{id}/traverseLevelOrder
     * @access  public
     */
    public List<NodeEntity<T>> traverseLevelOrder(ObjectId id) {
        return treeRepository.findById(id).get().traverseLevelOrder();
    }
    /**
     * @name    search
     * @desc    Search for a value in a tree
     * @route   GET /api/trees/{id}/search/{value}
     * @access  public
     */
    public String search(
        ObjectId id,
        T value
    ) {
        return treeRepository.findById(id).get().search(value);
    }
    /**
     * @name    getDeepestNode
     * @desc    Get the deepest node in a tree
     * @route   GET /api/trees/{id}/deepestNode
     * @access  public
     */
    public NodeEntity<T> getDeepestNode(ObjectId id) {
        return treeRepository.findById(id).get().getDeepestNode();
    }
    /**
     * @name    deleteDeepestNode
     * @desc    Delete the deepest node in a tree
     * @route   DELETE /api/trees/{id}/deepestNode
     * @access  public
     */
    public String deleteDeepestNode(ObjectId id) {
        nodeDeletionMessage = treeRepository.findById(id).get().deleteDeepestNode();
        nodeRepository.deleteById(treeRepository.findById(id).get().getDeepestNode().getId());
        return nodeDeletionMessage;
    }
    /**
     * @name    deleteNodesByValue
     * @desc    Delete all nodes containing a given value from a tree
     * @route   DELETE /api/trees/{id}/nodes/{value}
     * @addess  public
     */
    public String deleteNodesByValue(
        ObjectId id,
        T value
    ) {
        nodeDeletionMessage = treeRepository.findById(id).get().deleteNodesByValue(value);
        nodeRepository.deleteAllByValue(value);
        return nodeDeletionMessage;
    }
    /**
     * @name    deleteNodeById
     * @desc    Delete the node with the given ID from the given tree
     * @route   DELETE /api/trees/{treeId}/nodes/{nodeId}
     * @access  public
     */
    public String deleteNodeById(
        ObjectId treeId,
        ObjectId nodeId
    ) {
        nodeDeletionMessage = treeRepository.findById(treeId).get().deleteNodeById(nodeId);
        nodeRepository.deleteById(nodeId);
        return nodeDeletionMessage;
    }
    /**
     * @name    deleteAll
     * @desc    Delete all trees
     * @route   DELETE /api/trees
     * @access  public
     */
    @Override
    public String deleteAll() {
        treeRepository.deleteAll();
        nodeRepository.deleteAll();
        return "Trees deleted.";
    }
    /**
     * @name    deleteById
     * @desc    Delete the tree with the given ID
     * @route   DELETE /api/trees/{id}
     * @access  public
     */
    @Override
    public String deleteById(ObjectId id) {
        treeRepository.deleteById(id);
        nodeRepository.deleteAllByTreeId(id);
        return treeDeletedMessage;
    }
    /**
     * @name    deleteByNode
     * @desc    Delete the tree containing the given node
     * @route   DELETE /api/trees/{node}
     * @access  public
     */
    public String deleteByNode(NodeEntity<T> node) {
        treeRepository.deleteByNode(node);
        nodeRepository.deleteAllByTreeId(node.getTreeId());
        return treeDeletedMessage;
    }
}