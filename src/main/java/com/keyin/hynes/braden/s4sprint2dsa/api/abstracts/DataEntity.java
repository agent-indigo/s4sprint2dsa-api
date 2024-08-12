package com.keyin.hynes.braden.s4sprint2dsa.api.abstracts;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
public abstract class DataEntity {
    @Id
    private ObjectId id;
    public DataEntity() {}
    public final ObjectId getId() {
        return id;
    }
}