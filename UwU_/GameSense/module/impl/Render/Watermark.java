package UwU_.GameSense.module.impl.Render;

import com.mojang.realmsclient.gui.ChatFormatting;

import UwU_.GameSense.Main;
import UwU_.GameSense.editor.Drag;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventDisplay;
import UwU_.GameSense.helpers.font.Fonts;
import UwU_.GameSense.helpers.render.RenderUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

import java.awt.*;

@ModuleInfo(name = "Watermark", type = Category.Render)
public class Watermark extends Module {

    private int tick;

    public Drag d = Main.createDrag(this, "watermark", 6, 6);

    @EventTarget
    public void onRender(EventDisplay e) {
        String ping = String.valueOf(mc.getConnection().getPlayerInfo(mc.player.getName()).getResponseTime());
        String text = "GameSense" + ChatFormatting.GRAY + " | " + ChatFormatting.RESET + Main.username + ChatFormatting.GRAY + " | " + ChatFormatting.RESET + (mc.isSingleplayer() ? "localhost" : mc.getCurrentServerData().serverIP) + ChatFormatting.GRAY + " | " + ChatFormatting.RESET + ping + "ms";

        float width = Fonts.REG16.getStringWidth(text) + 6;

        int xx = (int) d.getX();
        int yy = (int) d.getY();
        d.setWidth(width);
        d.setHeight(12);
        RenderUtil.drawBlurredShadow(xx, yy, width, 12, 15, new Color(20, 20, 20));
        RenderUtil.drawRectWH(xx, yy, width, 12, new Color(20, 20, 20).getRGB());
        RenderUtil.drawBlurredShadow(xx, yy, width, 1, 15, new Color(255, 0, 0));
        RenderUtil.drawRectWH(xx, yy, width, 0.9f, new Color(255, 0, 0).getRGB());
        Fonts.REG16.drawStringWithShadow(text, xx + 3, yy + 3f, -1);
    }

}
