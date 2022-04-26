package me.alpha432.oyvey.features.modules.player;


import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.events.PacketEvent;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.oyvey.EntityUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockTweaks extends Module
{
    private static BlockTweaks INSTANCE;
    public Setting<Boolean> autoTool;
    public Setting<Boolean> autoWeapon;
    public Setting<Boolean> noFriendAttack;
    public Setting<Boolean> noBlock;
    public Setting<Boolean> noGhost;
    public Setting<Boolean> destroy;
    private int lastHotbarSlot;
    private int currentTargetSlot;
    private boolean switched;

    public BlockTweaks() {
        super("BlockTweaks",  "Some tweaks for blocks.",  Module.Category.PLAYER,  true,  false,  false);
        this.autoTool = (Setting<Boolean>)this.register(new Setting("AutoTool", false));
        this.autoWeapon = (Setting<Boolean>)this.register(new Setting("AutoWeapon", false));
        this.noFriendAttack = (Setting<Boolean>)this.register(new Setting("NoFriendAttack", false));
        this.noBlock = (Setting<Boolean>)this.register(new Setting("NoHitboxBlock", true));
        this.noGhost = (Setting<Boolean>)this.register(new Setting("NoGlitchBlocks", false));
        this.destroy = (Setting<Boolean>)this.register(new Setting("Destroy", false,  v -> this.noGhost.getValue()));
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
        this.setInstance();
    }

    public static BlockTweaks getINSTANCE() {
        if (BlockTweaks.INSTANCE == null) {
            BlockTweaks.INSTANCE = new BlockTweaks();
        }
        return BlockTweaks.INSTANCE;
    }

    private void setInstance() {
        BlockTweaks.INSTANCE = this;
    }

    public void onDisable() {
        if (this.switched) {
            this.equip(this.lastHotbarSlot,  false);
        }
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
    }

    @SubscribeEvent
    public void onBreak(final BlockEvent.BreakEvent event) {
        if (fullNullCheck() || !this.noGhost.getValue() || !this.destroy.getValue()) {
            return;
        }
        if (!(BlockTweaks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            final BlockPos pos = BlockTweaks.mc.player.getPosition();
            this.removeGlitchBlocks(pos);
        }
    }

    @SubscribeEvent
    public void onBlockInteract(final PlayerInteractEvent.LeftClickBlock event) {
        if (this.autoTool.getValue() && (Speedmine.getInstance().mode.getValue() != Speedmine.Mode.PACKET || Speedmine.getInstance().isOff() || !Speedmine.getInstance().tweaks.getValue()) && !fullNullCheck()) {
            event.getPos();
            this.equipBestTool(BlockTweaks.mc.world.getBlockState(event.getPos()));
        }
    }

    @SubscribeEvent
    public void onAttack(final AttackEntityEvent event) {
        if (this.autoWeapon.getValue() && !fullNullCheck() && event.getTarget() != null) {
            this.equipBestWeapon(event.getTarget());
        }
    }

    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send event) {
        if (fullNullCheck()) {
            return;
        }
        final Entity entity;
        if (this.noFriendAttack.getValue() && event.getPacket() instanceof CPacketUseEntity && (entity = ((CPacketUseEntity)event.getPacket()).getEntityFromWorld((World)BlockTweaks.mc.world)) != null && OyVey.friendManager.isFriend(entity.getName())) {
            event.setCanceled(true);
        }
    }

    public void onUpdate() {
        if (!fullNullCheck()) {
            if (BlockTweaks.mc.player.inventory.currentItem != this.lastHotbarSlot && BlockTweaks.mc.player.inventory.currentItem != this.currentTargetSlot) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            if (!BlockTweaks.mc.gameSettings.keyBindAttack.isKeyDown() && this.switched) {
                this.equip(this.lastHotbarSlot,  false);
            }
        }
    }

    private void removeGlitchBlocks(final BlockPos pos) {
        for (int dx = -4; dx <= 4; ++dx) {
            for (int dy = -4; dy <= 4; ++dy) {
                for (int dz = -4; dz <= 4; ++dz) {
                    final BlockPos blockPos = new BlockPos(pos.getX() + dx,  pos.getY() + dy,  pos.getZ() + dz);
                    if (BlockTweaks.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.AIR)) {
                        BlockTweaks.mc.playerController.processRightClickBlock(BlockTweaks.mc.player,  BlockTweaks.mc.world,  blockPos,  EnumFacing.DOWN,  new Vec3d(0.5,  0.5,  0.5),  EnumHand.MAIN_HAND);
                    }
                }
            }
        }
    }

    private void equipBestTool(final IBlockState blockState) {
        int bestSlot = -1;
        double max = 0.0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            float speed;
            if (!stack.isEmpty && (speed = stack.getDestroySpeed(blockState)) > 1.0f) {
                final int eff;
                if ((speed += (float)(((eff = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY,  stack)) > 0) ? (Math.pow(eff,  2.0) + 1.0) : 0.0)) > max) {
                    max = speed;
                    bestSlot = i;
                }
            }
        }
        this.equip(bestSlot,  true);
    }

    public void equipBestWeapon(final Entity entity) {
        int bestSlot = -1;
        double maxDamage = 0.0;
        EnumCreatureAttribute creatureAttribute = EnumCreatureAttribute.UNDEFINED;
        if (EntityUtil.isLiving(entity)) {
            final EntityLivingBase base = (EntityLivingBase)entity;
            creatureAttribute = base.getCreatureAttribute();
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            if (!stack.isEmpty) {
                if (stack.getItem() instanceof ItemTool) {
                    final double damage = ((ItemTool)stack.getItem()).attackDamage + (double)EnchantmentHelper.getModifierForCreature(stack,  creatureAttribute);
                    if (damage > maxDamage) {
                        maxDamage = damage;
                        bestSlot = i;
                    }
                }
                else if (stack.getItem() instanceof ItemSword) {
                    final double damage;
                    if ((damage = ((ItemSword)stack.getItem()).getAttackDamage() + (double)EnchantmentHelper.getModifierForCreature(stack,  creatureAttribute)) > maxDamage) {
                        maxDamage = damage;
                        bestSlot = i;
                    }
                }
            }
        }
        this.equip(bestSlot,  true);
    }

    private void equip(final int slot,  final boolean equipTool) {
        if (slot != -1) {
            if (slot != BlockTweaks.mc.player.inventory.currentItem) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            this.currentTargetSlot = slot;
            BlockTweaks.mc.player.inventory.currentItem = slot;
            this.switched = equipTool;
        }
    }

    static {
        BlockTweaks.INSTANCE = new BlockTweaks();
    }
}