/*
 * Decompiled with CFR 0.152.
 */
package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.mc.protocol.data.game.entity.metadata.MetadataType;
import com.github.steveice10.mc.protocol.util.ObjectUtil;
import java.util.Objects;

public class EntityMetadata {
    private int id;
    private MetadataType type;
    private Object value;

    public EntityMetadata(int id, MetadataType type, Object value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return this.id;
    }

    public MetadataType getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EntityMetadata)) {
            return false;
        }
        EntityMetadata that = (EntityMetadata)o;
        return this.id == that.id && this.type == that.type && Objects.equals(this.value, that.value);
    }

    public int hashCode() {
        return ObjectUtil.hashCode(new Object[]{this.id, this.type, this.value});
    }

    public String toString() {
        return ObjectUtil.toString(this);
    }
}

