package com.keyin.hynes.braden.s4sprint2dsa.api.controllers;
import com.keyin.hynes.braden.s4sprint2dsa.api.services.TreeService;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.Deletes;
import com.keyin.hynes.braden.s4sprint2dsa.api.entities.NodeEntity;
import com.keyin.hynes.braden.s4sprint2dsa.api.entities.TreeEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@CrossOrigin
public final class TreeController<T> implements Deletes {
    @Autowired
    private TreeService<T> service;
    public TreeController() {
        service = new TreeService<T>();
    }
    @GetMapping("/api/trees")
    public List<TreeEntity<T>> getAll() {
        return service.getAll();
    }
    @GetMapping("/api/trees/{id}")
    public TreeEntity<T> getById(@PathVariable ObjectId id) {
        return service.getById(id);
    }
    @GetMapping("/api/trees/{node}")
    public TreeEntity<T> getByNode(@PathVariable NodeEntity<T> node) {
        return service.getByNode(node);
    }
    @PostMapping("/api/trees")
    public TreeEntity<T> add(@RequestBody T[] values) {
        return service.add(values);
    }
    @PostMapping("/api/trees/{id}/nodes")
    public TreeEntity<T> addNode(
        @PathVariable ObjectId id,
        @RequestBody T value
    ) {
        return service.addNode(id, value);
    }
    @GetMapping("/api/trees/{id}/traversePreOrder/{node}")
    public List<NodeEntity<T>> traversePreOrder(
        @PathVariable ObjectId id,
        @PathVariable NodeEntity<T> node
    ) {
        return service.traversePreOrder(id, node);
    }
    @GetMapping("/api/trees/{id}/traversePostOrder/{node}")
    public List<NodeEntity<T>> traversePostOrder(
        @PathVariable ObjectId id,
        @PathVariable NodeEntity<T> node
    ) {
        return service.traversePostOrder(id, node);
    }
    @GetMapping("/api/trees/{id}/traverseInOrder/{node}")
    public List<NodeEntity<T>> traverseInOrder(
        @PathVariable ObjectId id,
        @PathVariable NodeEntity<T> node
    ) {
        return service.traverseInOrder(id, node);
    }
    @GetMapping("/api/trees/{id}/traverseLevelOrder")
    public List<NodeEntity<T>> traverseLevelOrder(@PathVariable ObjectId id) {
        return service.traverseLevelOrder(id);
    }
    @GetMapping("/api/trees/{id}/search/{value}")
    public String search(
        @PathVariable ObjectId id,
        @PathVariable T value
    ) {
        return service.search(id, value);
    }
    @GetMapping("/api/trees/{id}/deepestNode")
    public NodeEntity<T> getDeepestNode(@PathVariable ObjectId id) {
        return service.getDeepestNode(id);
    }
    @DeleteMapping("/api/trees/{id}/deepestNode")
    public String deleteDeepestNode(@PathVariable ObjectId id) {
        return service.deleteDeepestNode(id);
    }
    @DeleteMapping("/api/trees/{id}/nodes/{value}")
    public String deleteNodesByValue(
        @PathVariable ObjectId id,
        @PathVariable T value
    ) {
        return service.deleteNodesByValue(id, value);
    }
    @DeleteMapping("/api/trees/{treeId}/nodes/{nodeId}")
    public String deleteNodeById(
        @PathVariable ObjectId treeId,
        @PathVariable ObjectId nodeId
    ) {
        return service.deleteNodeById(treeId, nodeId);
    }
    @Override
    @DeleteMapping("/api/trees")
    public String deleteAll() {
        return service.deleteAll();
    }
    @Override
    @DeleteMapping("/api/trees/{id}")
    public String deleteById(@PathVariable ObjectId id) {
        return service.deleteById(id);
    }
    @DeleteMapping("/api/trees/{node}")
    public String deleteByNode(@PathVariable NodeEntity<T> node) {
        return service.deleteByNode(node);
    }
}