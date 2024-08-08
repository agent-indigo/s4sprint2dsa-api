package com.keyin.hynes.braden.s4sprint2dsa.api.classes.abstracts;
import org.springframework.data.annotation.Id;
public abstract class DataEntity {
    @Id
    private String _id;
    public DataEntity() {}
    public final String get_id() {
        return _id;
    }
}