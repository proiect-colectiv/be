package com.proiect_colectiv.model;

import java.io.Serializable;

public class Entity implements Serializable {

    public Long ID;

    public Entity(Long id) {
        this.ID = id;
    }

    public Entity() {}

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
