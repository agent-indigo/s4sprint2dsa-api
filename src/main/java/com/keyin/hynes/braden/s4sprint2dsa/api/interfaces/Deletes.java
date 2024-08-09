package com.keyin.hynes.braden.s4sprint2dsa.api.interfaces;
import org.bson.types.ObjectId;
public interface Deletes {
    String deleteAll();
    String deleteById(ObjectId id);
}