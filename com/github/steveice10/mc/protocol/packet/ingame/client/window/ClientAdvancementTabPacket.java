/*
 * Decompiled with CFR 0.152.
 */
package com.github.steveice10.mc.protocol.packet.ingame.client.window;

import com.github.steveice10.mc.protocol.data.MagicValues;
import com.github.steveice10.mc.protocol.data.game.window.AdvancementTabAction;
import com.github.steveice10.mc.protocol.packet.MinecraftPacket;
import com.github.steveice10.packetlib.io.NetInput;
import com.github.steveice10.packetlib.io.NetOutput;
import java.io.IOException;

public class ClientAdvancementTabPacket
extends MinecraftPacket {
    private AdvancementTabAction action;
    private String tabId;

    public ClientAdvancementTabPacket() {
        this.action = AdvancementTabAction.CLOSED_SCREEN;
    }

    public ClientAdvancementTabPacket(String tabId) {
        this.action = AdvancementTabAction.OPENED_TAB;
        this.tabId = tabId;
    }

    public String getTabId() {
        if (this.action != AdvancementTabAction.OPENED_TAB) {
            throw new IllegalStateException("tabId is only set if action is " + (Object)((Object)AdvancementTabAction.OPENED_TAB) + " but it was " + (Object)((Object)this.action));
        }
        return this.tabId;
    }

    @Override
    public void read(NetInput in) throws IOException {
        this.action = MagicValues.key(AdvancementTabAction.class, in.readVarInt());
        switch (this.action) {
            case CLOSED_SCREEN: {
                break;
            }
            case OPENED_TAB: {
                this.tabId = in.readString();
                break;
            }
            default: {
                throw new IOException("Unknown advancement tab action: " + (Object)((Object)this.action));
            }
        }
    }

    @Override
    public void write(NetOutput out) throws IOException {
        out.writeVarInt(MagicValues.value(Integer.class, (Object)this.action));
        switch (this.action) {
            case CLOSED_SCREEN: {
                break;
            }
            case OPENED_TAB: {
                out.writeString(this.tabId);
                break;
            }
            default: {
                throw new IOException("Unknown advancement tab action: " + (Object)((Object)this.action));
            }
        }
    }
}

