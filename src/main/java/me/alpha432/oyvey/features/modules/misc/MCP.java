package me.alpha432.oyvey.features.modules.misc;


import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.oyvey.InventoryUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Mouse;

public class MCP extends Module {

    private boolean clicked = false;

    public MCP() {
        super("ClickPearl", "Throws a pearl", Category.MISC, false, false, false);
    }

    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
        }

    }

    public void onTick() {
        if (Mouse.isButtonDown(2)) {
            if (!this.clicked) {
                this.throwPearl();
            }

            this.clicked = true;
        } else {
            this.clicked = false;
        }

    }

    private void throwPearl() {
        int pearlSlot = InventoryUtil.findHotbarBlock(ItemEnderPearl.class);
        boolean offhand = MCP.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL;

        if (pearlSlot != -1 || offhand) {
            int oldslot = MCP.mc.player.inventory.currentItem;

            if (!offhand) {
                InventoryUtil.switchToHotbarSlot(pearlSlot, false);
            }

            MCP.mc.playerController.processRightClick(MCP.mc.player, MCP.mc.world, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!offhand) {
                InventoryUtil.switchToHotbarSlot(oldslot, false);
            }
        }

    }
}