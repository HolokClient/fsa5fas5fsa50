/*
 * Decompiled with CFR 0.152.
 */
package com.github.steveice10.mc.protocol.packet.ingame.server.entity;

import com.github.steveice10.mc.protocol.packet.ingame.server.entity.ServerEntityMovementPacket;

public class ServerEntityPositionPacket
extends ServerEntityMovementPacket {
    protected ServerEntityPositionPacket() {
        this.pos = true;
    }

    public ServerEntityPositionPacket(int entityId, double moveX, double moveY, double moveZ, boolean onGround) {
        super(entityId, onGround);
        this.pos = true;
        this.moveX = moveX;
        this.moveY = moveY;
        this.moveZ = moveZ;
    }
}

