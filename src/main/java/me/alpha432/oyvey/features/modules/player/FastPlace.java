package me.alpha432.oyvey.features.modules.player;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.oyvey.InventoryUtil;
import net.minecraft.item.ItemExpBottle;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace", "Fast everything.", Module.Category.PLAYER, true, false, false);
    }

    public void onUpdate() {
        if (!fullNullCheck()) {
            if (InventoryUtil.holdingItem(ItemExpBottle.class)) {
                mc.rightClickDelayTimer = 0;
            }

        }
    }
}