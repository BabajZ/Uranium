package me.alpha432.oyvey.features.modules.combat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.alpha432.oyvey.event.events.PlayerLivingUpdateEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.oyvey.BlockUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BurrowBreaker extends Module {

    public Setting packet = this.register(new Setting("Packet", Boolean.valueOf(false)));
    public Setting rotate = this.register(new Setting("Rotate", Boolean.valueOf(true)));
    private boolean isBreaking = false;
    public BlockPos pos;
    public List burrowedEntities = new ArrayList();

    public BurrowBreaker() {
        super("BurrowBreaker", "Break burrow", Module.Category.COMBAT, true, false, false);
    }

    @SubscribeEvent
    public void onUpdate(PlayerLivingUpdateEvent event) {
        this.burrowedEntities.clear();
        Iterator iterator = BurrowBreaker.mc.world.loadedEntityList.iterator();

        while (iterator.hasNext()) {
            Entity e = (Entity) iterator.next();

            if (e instanceof EntityPlayer) {
                if (e.equals(BurrowBreaker.mc.player)) {
                    return;
                }

                this.pos = new BlockPos(e.posX, e.posY + 0.2D, e.posZ);
                if (BurrowBreaker.mc.world.getBlockState(this.pos).getBlock() == Blocks.OBSIDIAN || BurrowBreaker.mc.world.getBlockState(this.pos).getBlock() == Blocks.ENDER_CHEST) {
                    this.burrowedEntities.add(this.pos);
                }

                if (this.burrowedEntities.contains(this.pos) && BurrowBreaker.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe && !this.isBreaking) {
                    BlockUtil.damageBlock(this.pos, ((Boolean) this.packet.getValue()).booleanValue(), ((Boolean) this.rotate.getValue()).booleanValue());
                    this.isBreaking = true;
                }

                if (BurrowBreaker.mc.world.getBlockState(this.pos).getBlock() == Blocks.AIR) {
                    this.isBreaking = false;
                }
            }
        }

    }
}