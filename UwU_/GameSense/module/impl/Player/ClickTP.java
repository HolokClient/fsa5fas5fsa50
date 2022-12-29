package UwU_.GameSense.module.impl.Player;

import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.RayTraceResult;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventRender;
import UwU_.GameSense.event.events.impl.MouseEvent;
import UwU_.GameSense.helpers.Castt;
import UwU_.GameSense.helpers.render.RenderUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;

import java.awt.*;

@ModuleInfo(name = "ClickTP", type = Category.Player)
public class ClickTP extends Module {

    @EventTarget
    public void onUpdate(MouseEvent e) {
        if (e.button == 1) {
            RayTraceResult r = Castt.rayTrace(500, mc.player.rotationYaw, mc.player.rotationPitch);
            mc.player.connection.sendPacket(new CPacketPlayer.Position(mc.player.posX, mc.player.posY, mc.player.posZ,false));
            for (int i = 0; i < 2; i++) {
                mc.player.connection.sendPacket(new CPacketPlayer.Position(r.getBlockPos().getX() + 0.5f, r.getBlockPos().getY() + 1, r.getBlockPos().getZ() + 0.5f,true));
            }
        }
    }

    @EventTarget
    public void onRender(EventRender e) {
        RayTraceResult r = Castt.rayTrace(150, mc.player.rotationYaw, mc.player.rotationPitch);
        if (r != null) {
            RenderUtil.blockEsp(r.getBlockPos(), Color.WHITE);
        }
    }

}
