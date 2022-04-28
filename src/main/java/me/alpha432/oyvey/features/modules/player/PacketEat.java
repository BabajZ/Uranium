package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.util.Player.InventoryUtil;
import me.alpha432.oyvey.util.Player.PlayerUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.item.ItemAppleGold;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.features.modules.Module;

public class PacketEat extends Module
{
    private final Setting<Mode> mode;
    private final Setting<Double> health;
    private final Setting<Double> packetSize;

    public PacketEat() {
        super("PacketEat", "", Category.PLAYER, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", Mode.Packet));
        this.health = (Setting<Double>)this.register(new Setting("Health", 28.0, 0.0, 36.0));
        this.packetSize = (Setting<Double>)this.register(new Setting("PacketIteration", 20.0, 0.0, 40.0));
    }

    @Override
    public void onUpdate() {
        if (PacketEat.mc.player.isHandActive() && PacketEat.mc.player.getHeldItemMainhand().getItem() instanceof ItemAppleGold && (this.mode.getValue() == Mode.Packet || this.mode.getValue() == Mode.Auto)) {
            for (int i = 0; i < this.packetSize.getValue(); ++i) {
                PacketEat.mc.player.connection.sendPacket((Packet)new CPacketPlayer());
            }
            PacketEat.mc.player.stopActiveHand();
        }
        if (this.mode.getValue() == Mode.Auto && PlayerUtil.getHealth() <= this.health.getValue()) {
            InventoryUtil.switchToSlotGhost(InventoryUtil.getHotbarItemSlot(Items.GOLDEN_APPLE));
            PacketEat.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
    }



    @SubscribeEvent
    public void onPlayerRightClick(final PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem().equals(Items.GOLDEN_APPLE) && this.mode.getValue() == Mode.Desync) {
            event.setCanceled(true);
            event.getItemStack().getItem().onItemUseFinish(event.getItemStack(), event.getWorld(), (EntityLivingBase)event.getEntityPlayer());
        }
    }

    public enum Mode
    {
        Packet,
        Desync,
        Auto;
    }
}