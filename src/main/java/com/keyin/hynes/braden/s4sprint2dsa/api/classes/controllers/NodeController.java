package com.keyin.hynes.braden.s4sprint2dsa.api.classes.controllers;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.services.NodeService;
import com.keyin.hynes.braden.s4sprint2dsa.api.interfaces.Deletes;
import com.keyin.hynes.braden.s4sprint2dsa.api.classes.entities.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@CrossOrigin
public final class NodeController<T> implements Deletes {
    @Autowired
    private NodeService<T> service;
    public NodeController() {
        this.service = new NodeService<T>();
    }
    @GetMapping("/api/nodes")
    public List<NodeEntity<T>> getAll() {
        return service.getAll();
    }
    @GetMapping("/api/nodes/{_id}")
    public NodeEntity<T> getBy_id(@PathVariable String _id) {
        return service.getBy_id(_id);
    }
    @GetMapping("/api/nodes/{value}")
    public List<NodeEntity<T>> getAllByValue(@PathVariable T value) {
        return service.getAllByValue(value);
    }
    @GetMapping("/api/nodes/{left}")
    public NodeEntity<T> getByLeft(@PathVariable NodeEntity<T> left) {
        return service.getByLeft(left);
    }
    @GetMapping("/api/nodes/{right}")
    public NodeEntity<T> getByRight(@PathVariable NodeEntity<T> right) {
        return service.getByRight(right);
    }
    @GetMapping("/api/nodes/{height}")
    public List<NodeEntity<T>> getByHeight(@PathVariable int height) {
        return service.getAllByHeight(height);
    }
    @PostMapping("/api/nodes")
    public NodeEntity<T> add(@PathVariable NodeEntity<T> node) {
        return service.add(node);
    }
    @PutMapping("/api/nodes/{_id}")
    public NodeEntity<T> edit(
        @PathVariable String _id,
        @RequestBody NodeEntity<T> node
    ) {
        return service.edit(_id, node);
    }
    @PatchMapping("/api/nodes/{_id}/value")
    public NodeEntity<T> editValue(
        @PathVariable String _id,
        @RequestBody T value
    ) {
        return service.editValue(_id, value);
    }
    @PatchMapping("/api/nodes/{_id}/left")
    public NodeEntity<T> editLeft(
        @PathVariable String _id,
        @RequestBody NodeEntity<T> left
    ) {
        return service.editLeft(_id, left);
    }
    @PatchMapping("/api/nodes/{_id}/right")
    public NodeEntity<T> editRight(
        @PathVariable String _id,
        @RequestBody NodeEntity<T> right
    ) {
        return service.editRight(_id, right);
    }
    @PatchMapping("/api/nodes/{_id}/height")
    public NodeEntity<T> editHeight(
        @PathVariable String _id,
        @RequestBody int height
    ) {
        return service.editHeight(_id, height);
    }
    @Override
    @DeleteMapping("/api/nodes")
    public String deleteAll() {
        return service.deleteAll();
    }
    @Override
    @DeleteMapping("/api/nodes/{_id}")
    public String deleteBy_id(@PathVariable String _id) {
        return service.deleteBy_id(_id);
    }
    @DeleteMapping("/api/nodes/{value}")
    public String deleteAllByValue(@PathVariable T value) {
        return service.deleteAllByValue(value);
    }
    @DeleteMapping("/api/nodes/{left}")
    public String deleteByLeft(@PathVariable NodeEntity<T> left) {
        return service.deleteByLeft(left);
    }
    @DeleteMapping("/api/nodes/{right}")
    public String deleteByRight(@PathVariable NodeEntity<T> right) {
        return service.deleteByRight(right);
    }
    @DeleteMapping("/api/nodes/{height}")
    public String deleteAllByHeight(@PathVariable int height) {
        return service.deleteAllByHeight(height);
    }
}