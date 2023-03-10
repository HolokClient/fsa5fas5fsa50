/*
 * Decompiled with CFR 0.152.
 */
package com.github.steveice10.mc.protocol.data.game.entity.metadata;

import com.github.steveice10.mc.protocol.util.ObjectUtil;

public class Rotation {
    private float pitch;
    private float yaw;
    private float roll;

    public Rotation() {
        this(0.0f, 0.0f, 0.0f);
    }

    public Rotation(float pitch, float yaw, float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getRoll() {
        return this.roll;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rotation)) {
            return false;
        }
        Rotation that = (Rotation)o;
        return this.pitch == that.pitch && this.yaw == that.yaw && this.roll == that.roll;
    }

    public int hashCode() {
        return ObjectUtil.hashCode(Float.valueOf(this.pitch), Float.valueOf(this.yaw), Float.valueOf(this.roll));
    }

    public String toString() {
        return ObjectUtil.toString(this);
    }
}

