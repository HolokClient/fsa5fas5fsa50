package UwU_.GameSense.module.impl.Combat;


import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import UwU_.GameSense.Main;
import UwU_.GameSense.event.EventTarget;
import UwU_.GameSense.event.events.impl.EventUpdate;
import UwU_.GameSense.module.Category;
import UwU_.GameSense.module.Module;
import UwU_.GameSense.module.ModuleInfo;
import UwU_.GameSense.settings.options.BooleanSetting;
@ModuleInfo(name = "Resolver", type = Category.Combat)
public class Resolver extends Module {

    public BooleanSetting auraResolver = new BooleanSetting("Aura Resolver", true);
    public BooleanSetting resolver = new BooleanSetting("Player Resolver", true);

    public Resolver() {
        addSettings(auraResolver, resolver);
    }

    @EventTarget
    public void onResolve(EventUpdate e) {
        if (resolver.get())
            resolvePlayers();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (mc.player != null)
            releaseResolver();
    }

    public void resolvePlayers() {
        for (EntityPlayer player : mc.world.playerEntities) {
            if (player instanceof EntityOtherPlayerMP) {
                ((EntityOtherPlayerMP) player).resolve();
            }
        }
    }

    public void releaseResolver() {
        for (EntityPlayer player : mc.world.playerEntities) {
            if (player instanceof EntityOtherPlayerMP) {
                ((EntityOtherPlayerMP) player).releaseResolver();
            }
        }
    }

    public static boolean resolveAura() {
        return ((Resolver) Main.m.getModule(Resolver.class)).auraResolver.get() && Main.m.getModule(Resolver.class).state;
    }

}
