package UwU_.GameSense.module.impl.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Mouse;
import UwU_.GameSense.Main;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.module.impl.Combat.KillAura;
import UwU_.GameSense.notifications.NotificationType;

@ModuleInfo(name = "MiddleClickFriend", type = Category.Player)
public class MiddleClickFriend extends Module {
    public boolean onFriend = true;


   @EventTarget
    public void onUpd(EventUpdate event) {
        for (Entity in : mc.world.loadedEntityList) {
            if (in == mc.player) continue;
            if (!(in instanceof EntityPlayer)) continue;
            if (!isLookingAtTarget(mc.player.rotationYaw, mc.player.rotationPitch, in, 500)) {
                if (Mouse.isButtonDown(2) && onFriend && ((KillAura) Main.m.getModule(KillAura.class)).target == null) {
                    this.onFriend = false;
                    Entity e = in;
                    if (!Main.f.isFriend(e.getName())) {
                        Main.notify.call("FriendManager", TextFormatting.getTextWithoutFormattingCodes(e.getName()) + " added", NotificationType.INFO);
                        Main.f.add(e.getName());
                    } else {
                        Main.notify.call("FriendManager", TextFormatting.getTextWithoutFormattingCodes(e.getName()) + " removed", NotificationType.INFO);
                        Main.f.remove(e.getName());
                    }
                }

                if (!Mouse.isButtonDown(2)) {
                    this.onFriend = true;
                }
            }
        }
    }

    public boolean isLookingAtTarget(float yaw, float pitch, Entity entity, double range) {
        Vec3d src = Minecraft.getMinecraft().player.getPositionEyes(1.0F);
        Vec3d vectorForRotation = Entity.getVectorForRotation(pitch, yaw);
        Vec3d dest = src.add(vectorForRotation.x * range, vectorForRotation.y * range, vectorForRotation.z * range);

        RayTraceResult rayTraceResult = Minecraft.getMinecraft().world.rayTraceBlocks(src, dest, false, false, true);

        if (rayTraceResult == null) {
            return true;
        }

        return (entity.getEntityBoundingBox().expand(0.06, 0.06, 0.06).calculateIntercept(src, dest) == null);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        onFriend = true;
    }
}
