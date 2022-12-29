package UwU_.GameSense.module.impl.Player;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.helpers.MovementUtil;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.BooleanSetting;

@ModuleInfo(name = "Freecam", type = Category.Player)
public class Freecam extends Module {

    public BooleanSetting notp = new BooleanSetting("No Back Teleport", true).call(this);

    public double savedX;
    public double savedY;
    public double savedZ;
    public float savedYaw;
    public float savedPitch;
    public double savedMotionX;
    public double savedMotionY;
    public double savedMotionZ;

    @Override
    public void onEnable() {
        super.onEnable();
        if (mc.player == null) return;
        savedX = mc.player.posX;
        savedY = mc.player.posY;
        savedZ = mc.player.posZ;
        savedYaw = mc.player.rotationYaw;
        savedPitch = mc.player.rotationPitch;
        savedMotionX = mc.player.motionX;
        savedMotionY = mc.player.motionY;
        savedMotionZ = mc.player.motionZ;
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        mc.player.motionY = 0;
        if (mc.gameSettings.keyBindJump.pressed) mc.player.motionY = 1;
        if (mc.gameSettings.keyBindSneak.pressed) mc.player.motionY = -1;
        if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindLeft.pressed || mc.gameSettings.keyBindRight.pressed)
            MovementUtil.setSpeed(1);
        else
            MovementUtil.setSpeed(0);

        mc.player.noClip = true;

    }


    @EventTarget
    public void onPacket(EventPacket e) {
        if (!notp.get()) return;
        if (e.getPacket() instanceof SPacketPlayerPosLook) {
            if (mc.player != null && mc.world != null && mc.getRenderViewEntity() instanceof EntityPlayerSP)
            e.cancel();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.player == null) return;
        mc.player.setPosition(savedX, savedY, savedZ);
        mc.player.rotationYaw = savedYaw;
        mc.player.rotationPitch = savedPitch;
        mc.player.motionX = savedMotionX;
        mc.player.motionY = savedMotionY;
        mc.player.motionZ = savedMotionZ;
    }
}
