package UwU_.GameSense.module.impl.Combat;

import UwU_.GameSense.Main;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventMotion;
import UwU_.GameSense.event.events.impl.EventPacket;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.event.events.impl.InteractEvent;
import UwU_.GameSense.helpers.Castt;
import UwU_.GameSense.helpers.animation.Counter;
import UwU_.GameSense.helpers.render.TPS;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.Setting;
import UwU_.GameSense.settings.options.BooleanSetting;
import UwU_.GameSense.settings.options.ListSetting;
import UwU_.GameSense.settings.options.ModeSetting;
import UwU_.GameSense.settings.options.SliderSetting;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemShield;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.Iterator;

@ModuleInfo(
        name = "KillAura",
        type = Category.Combat
)
public class KillAura extends Module {
    public SliderSetting range = new SliderSetting("Range", 3.0F, 3.0F, 6.0F, 0.1F);
    public SliderSetting prerange = new SliderSetting("Pre Range", 1.0F, 0.0F, 6.0F, 0.1F);
    public SliderSetting cooldown = new SliderSetting("Cool Down", 1.0F, 0.1F, 1.0F, 0.01F);
    public ListSetting shieldFunc = new ListSetting("Shield", new String[]{"Shield Breaker", "Better Shield", "Shield Desync"});
    public BooleanSetting sync = new BooleanSetting("TPS Sync", true);
    public BooleanSetting raycast = new BooleanSetting("Ray Cast", false);
    public BooleanSetting look = new BooleanSetting("Client Look", false);
    public ListSetting test = new ListSetting("Targets", new String[]{"Player", "Mobs", "Animals"});
    public static BooleanSetting sprint = new BooleanSetting("Keep Sprint", false);
    public BooleanSetting crit = new BooleanSetting("Falling Criticals", true);
    public ModeSetting mode = new ModeSetting("Rotation", "Matrix", new String[]{"Matrix", "Sunrise"});
    public static EntityLivingBase target;
    public Vector2f rotation;
    public float rotYaw = 0.0F;
    public float rotPitch = 0.0F;
    public Counter timer = new Counter();
    public double rX = 0.0;
    public double rY = 0.0;
    public double rZ = 0.0;

    public KillAura() {
        this.addSettings(new Setting[]{this.range, this.prerange, this.shieldFunc, this.cooldown, this.mode, this.sync, this.crit, this.look, this.raycast, sprint, this.test});
    }

    @EventTarget
    public void onUpdate(EventMotion e) {
        this.setSuffix(this.mode.get());
        this.rotYaw = this.rotation.x;
        this.rotPitch = this.rotation.y;
        if (target != null && target.getHealth() <= 0.0F || target != null && target.isDead) {
            target = null;
        } else {
            if (this.getDistanceAura(target) >= this.range.get() + this.prerange.get()) {
                target = null;
            }

            if (target != null) {
                e.setPitch(this.rotPitch);
                e.setYaw(this.rotYaw);
                if (this.look.get()) {
                    this.mc.player.rotationYaw = this.rotYaw;
                    this.mc.player.rotationPitch = this.rotPitch;
                }

                this.mc.player.rotationYawHead = this.rotYaw;
                this.mc.player.rotationPitchHead = this.rotPitch;
                this.mc.player.renderYawOffset = this.rotYaw;
            }
        }

    }

    public boolean mayAttack() {
        if (!this.crit.get()) {
            return true;
        } else {
            return this.mc.player.fallDistance > 0.0F && !this.mc.player.isInWater() && !this.mc.player.isInLava();
        }
    }

    @EventTarget
    public void onPacket(InteractEvent e) {
        if (target != null) {
            e.cancel();
        }

    }

    @EventTarget
    public void onPacket(EventPacket e) {
        if (target != null) {
            if (e.getPacket() instanceof CPacketUseEntity) {
                CPacketUseEntity p = (CPacketUseEntity)e.getPacket();
                if (p.getAction() != CPacketUseEntity.Action.ATTACK) {
                    e.cancel();
                }
            }

            if (e.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                CPacketPlayerTryUseItemOnBlock var3 = (CPacketPlayerTryUseItemOnBlock)e.getPacket();
            }
        }

    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        this.findTarget();
        if (Resolver.resolveAura()) {
            this.resolvePlayers();
        }

        if (target != null) {
            this.rotate();
            if (this.shieldFunc.selected.contains("Shield Desync") && target.getActiveItemStack().getItem() instanceof ItemShield && this.timer.hasReached((double)UwU_.GameSense.helpers.math.MathHelper.getRandomNumberBetween(100, 400))) {
                this.mc.player.connection.sendPacket(new CPacketPlayerDigging(net.minecraft.network.play.client.CPacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.UP));
                this.timer.reset();
            }

            if (this.getDistanceAura(target) <= this.range.get()) {
                float attackDelay = 0.83F * TPS.getTPSServer() / 20.0F;
                if ((double)this.mc.player.getCooledAttackStrength(this.sync.get() ? attackDelay : 0.0F) >= 0.93) {
                    if (this.shieldFunc.selected.contains("Better Shield") && this.mc.player.isActiveItemStackBlocking()) {
                        this.mc.playerController.onStoppedUsingItem(this.mc.player);
                    }

                    if (this.mayAttack()) {
                        this.mc.player.setSprinting(false);
                        this.rX = this.mode.is("Matrix") ? Math.sin((double)System.nanoTime()) / 1.0E9 / (double)UwU_.GameSense.helpers.math.MathHelper.getRandomNumberBetween(80, 100) : 0.0;
                        this.rY = this.mode.is("Matrix") ? Math.cos((double)System.nanoTime()) / 1.0E9 / (double)UwU_.GameSense.helpers.math.MathHelper.getRandomNumberBetween(80, 100) : 0.0;
                        this.rZ = -(this.mode.is("Matrix") ? Math.cos((double)((float)System.nanoTime() / 1.0E9F)) / (double)UwU_.GameSense.helpers.math.MathHelper.getRandomNumberBetween(80, 100) : 0.0);
                        this.rotYaw = this.rotation.x;
                        this.rotPitch = this.rotation.y;
                        if (this.shieldFunc.selected.contains("Shield Breaker") && target.getActiveItemStack().getItem() instanceof ItemShield && target.getItemInUseMaxCount() > 4 && target.isHandActive() && this.findAxe() != -1) {
                            int lastItem = this.mc.player.inventory.currentItem;
                            this.mc.player.connection.sendPacket(new CPacketHeldItemChange(this.findAxe()));
                            this.mc.player.connection.sendPacket(new CPacketUseEntity(target));
                            this.mc.player.connection.sendPacket(new CPacketHeldItemChange(lastItem));
                        }

                        if (this.raycast.get()) {
                            if (Castt.getMouseOver(target, this.rotYaw, this.rotPitch, (double)this.range.get(), false) != null) {
                                this.mc.playerController.attackEntity(this.mc.player, target);
                                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                                this.mc.player.resetCooldown();
                            }
                        } else {
                            this.mc.playerController.attackEntity(this.mc.player, target);
                            this.mc.player.swingArm(EnumHand.MAIN_HAND);
                            this.mc.player.resetCooldown();
                        }
                    }
                }
            }
        }

        if (Resolver.resolveAura()) {
            this.releaseResolver();
        }

    }

    public void resolvePlayers() {
        Iterator var1 = this.mc.world.playerEntities.iterator();

        while(var1.hasNext()) {
            EntityPlayer player = (EntityPlayer)var1.next();
            if (player instanceof EntityOtherPlayerMP) {
                ((EntityOtherPlayerMP)player).resolve();
            }
        }

    }

    public void releaseResolver() {
        Iterator var1 = this.mc.world.playerEntities.iterator();

        while(var1.hasNext()) {
            EntityPlayer player = (EntityPlayer)var1.next();
            if (player instanceof EntityOtherPlayerMP) {
                ((EntityOtherPlayerMP)player).releaseResolver();
            }
        }

    }

    public float getFixedRotation(float rot) {
        return this.getDeltaMouse(rot) * this.getGCDValue();
    }

    public float getGCDValue() {
        return (float)((double)this.getGCD() * 0.15);
    }

    public float getGCD() {
        float f1;
        return (f1 = (float)((double)this.mc.gameSettings.mouseSensitivity * 0.6 + 0.2)) * f1 * f1 * 8.0F;
    }

    public float getDeltaMouse(float delta) {
        return (float)Math.round(delta / this.getGCDValue());
    }

    public int findAxe() {
        for(int i = 0; i < 9; ++i) {
            if (this.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemAxe) {
                return i;
            }
        }

        return -1;
    }

    public Vec3d getVecTarget() {
        double[] rax = this.mc.player.razXZ(target);
        Vec3d vec = target.getPositionVector().add(new Vec3d(rax[0], net.minecraft.util.math.MathHelper.clamp((double)(target.getEyeHeight() * (this.mc.player.getDistance(target) / (this.range.get() + target.width))), 0.2, (double)this.mc.player.getEyeHeight()), rax[1]));
        if (!this.isHitBoxVisible(vec)) {
            for(double i = (double)target.width * 0.05; i <= (double)target.width * 0.95; i += (double)target.width * 0.9 / 8.0) {
                for(double j = (double)target.width * 0.05; j <= (double)target.width * 0.95; j += (double)target.width * 0.9 / 8.0) {
                    for(double k = 0.0; k <= (double)target.height; k += (double)(target.height / 8.0F)) {
                        if (this.isHitBoxVisible((new Vec3d(i, k, j)).add(target.getPositionVector().add(new Vec3d((double)(-target.width / 2.0F), 0.0, (double)(-target.width / 2.0F)))))) {
                            vec = (new Vec3d(i, k, j)).add(target.getPositionVector().add(new Vec3d((double)(-target.width / 2.0F), 0.0, (double)(-target.width / 2.0F))));
                            break;
                        }
                    }
                }
            }
        }

        return vec;
    }

    boolean isHitBoxVisible(Vec3d vec3d) {
        Vec3d eyesPos = new Vec3d(this.mc.player.posX, this.mc.player.getEntityBoundingBox().minY + (double)this.mc.player.getEyeHeight(), this.mc.player.posZ);
        return this.mc.world.rayTraceBlocks(eyesPos, vec3d, false, true, false) == null;
    }

    public void rotate() {
        double xDiff = (this.mode.is("Sunrise") ? target.posX : this.getVecTarget().x) - this.mc.player.posX + this.rX + (this.mode.is("Sunrise") ? target.posX - target.prevPosX : 0.0);
        double yDiff = (this.mode.is("Sunrise") ? target.posY : this.getVecTarget().y) - this.mc.player.posY - (double)this.mc.player.getEyeHeight() + 0.30000001192092896 - (this.getDistanceAura(target) > 1.0F ? this.rY : 0.0) + (double)(this.mode.is("Sunrise") ? 1 : 0) + (this.mode.is("Sunrise") ? target.posY - target.prevPosY : 0.0);
        double zDiff = (this.mode.is("Sunrise") ? target.posZ : this.getVecTarget().z) - this.mc.player.posZ - this.rZ + (this.mode.is("Sunrise") ? target.posZ - target.prevPosZ : 0.0);
        double yaw = (double)this.getFixedRotation((float)net.minecraft.util.math.MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(zDiff, xDiff)) - 90.0) + (float)UwU_.GameSense.helpers.math.MathHelper.getRandomNumberBetween(-2, 2));
        double pitch = (double)this.getFixedRotation((float)net.minecraft.util.math.MathHelper.wrapDegrees(Math.toDegrees(-Math.atan2(yDiff, Math.hypot(xDiff, zDiff)))) + (float)UwU_.GameSense.helpers.math.MathHelper.getRandomNumberBetween(-2, 2));
        pitch = net.minecraft.util.math.MathHelper.clamp(pitch, -90.0, 90.0);
        this.rotation = new Vector2f((float)yaw, (float)pitch);
    }

    public float getDistanceAura(Entity entityIn) {
        float f = (float)(this.mc.player.posX - (entityIn.posX + this.mc.player.razXZ(entityIn)[0]));
        float f1 = (float)(this.mc.player.posY - entityIn.posY);
        float f2 = (float)(this.mc.player.posZ - entityIn.posZ + this.mc.player.razXZ(entityIn)[1]);
        return net.minecraft.util.math.MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
    }

    public boolean isValidTarget(EntityLivingBase e) {
        if (e == this.mc.player) {
            return false;
        } else if (e.isDead) {
            return false;
        } else if (AntiBot.isBot.contains(e)) {
            return false;
        } else if (this.getDistanceAura(e) > this.range.get() + this.prerange.get()) {
            return false;
        } else if (Main.f.isFriend(e.getName())) {
            return false;
        } else if (e instanceof EntityPlayer && this.test.selected.contains("Player")) {
            return true;
        } else if (e instanceof EntityMob && this.test.selected.contains("Mobs")) {
            return true;
        } else {
            return e instanceof IAnimals && this.test.selected.contains("Animals");
        }
    }

    public void onDisable() {
        super.onDisable();
        if (this.mc.player != null) {
            this.rotYaw = this.mc.player.rotationYaw;
            this.rotPitch = this.mc.player.rotationPitch;
        }

        target = null;
    }

    public void findTarget() {
        ArrayList<EntityLivingBase> parsedEntities = new ArrayList(this.mc.world.getEntities(EntityLivingBase.class, this::isValidTarget));
        parsedEntities.sort((e1, e2) -> {
            return e1.getHealth() > e2.getHealth() ? 1 : -1;
        });
        target = (EntityLivingBase)parsedEntities.get(0);
    }
}
