package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.util.World.EntityUtil;
import me.alpha432.oyvey.util.World.Timer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.*;

public class AutoCenter extends Module {

    public AutoCenter() {
        super("AutoCenter", "Center you", Category.MOVEMENT, true, false, false);
    }


        private final Timer timer = new Timer();
        private final Timer retryTimer = new Timer();
        private final Set<Vec3d> extendingBlocks = new HashSet<Vec3d>();
        private final Map<BlockPos, Integer> retries = new HashMap<BlockPos, Integer>();
        private int isSafe;
        private BlockPos startPos;
        private boolean didPlace = false;
        private boolean switchedItem;
        private int lastHotbarSlot;
        private boolean isSneaking;
        private int placements = 0;
        private int extenders = 1;
        private int obbySlot = -1;
        private boolean offHand = false;



        @Override
        public void onEnable() {
            if (me.alpha432.oyvey.features.modules.combat.Surround.fullNullCheck()) {
                this.disable();
            }
            this.lastHotbarSlot = me.alpha432.oyvey.features.modules.combat.Surround.mc.player.inventory.currentItem;
            this.startPos = EntityUtil.getRoundedBlockPos(me.alpha432.oyvey.features.modules.combat.Surround.mc.player);

            OyVey.positionManager.setPositionPacket((double) this.startPos.getX() + 0.5, this.startPos.getY(), (double) this.startPos.getZ() + 0.5, false, true, true);
            OyVey.positionManager.setPositionPacket((double) this.startPos.getX() + 0.5, this.startPos.getY(), (double) this.startPos.getZ() + 0.5, false, true, true);
            OyVey.positionManager.setPositionPacket((double) this.startPos.getX() + 0.5, this.startPos.getY(), (double) this.startPos.getZ() + 0.5, false, true, true);

            this.disable();
        }
    }
