package me.alpha432.oyvey.features.modules.movement;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import me.alpha432.oyvey.util.oyvey.EntityUtil;
import me.alpha432.oyvey.util.oyvey.MathUtil;
import me.alpha432.oyvey.util.oyvey.PlayerUtil3;
import me.alpha432.oyvey.util.oyvey.Timer;


public class Speed extends Module {
    public Speed() {
        super("Speed", "YPort Speed.", Category.MOVEMENT, false, false, false);
    }

    Setting<Mode> mode = register(new Setting("Mode", Mode.yPort));
    Setting<Double> yPortSpeed = this.register(new Setting<Double>("YPort Speed", 0.6, 0.5, 1.5, v -> (this.mode.getValue() == Mode.yPort)));
    Setting<Boolean> step = this.register(new Setting<Boolean>("Step", true, v -> (this.mode.getValue() == Mode.yPort)));
    Setting<Double> vanillaSpeed = this.register(new Setting<Double>("Vanilla Speed", 1.0, 1.7, 10.0, v -> (this.mode.getValue() == Mode.Vanilla)));

    private double playerSpeed;
    private final Timer timer = new Timer();

    public enum Mode {
        yPort,
        Vanilla
    }

    @Override
    public void onEnable() {
        playerSpeed = PlayerUtil3.getBaseMoveSpeed();
        if (step.getValue()) {
            if (fullNullCheck()) {
                return;
            }
            mc.player.stepHeight = 2.0f;
        }
    }

    @Override
    public void onDisable() {
        EntityUtil.resetTimer();
        this.timer.reset();
        if (step.getValue()) {
            mc.player.stepHeight = 0.6f;
        }
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) {
            this.disable();
            return;
        }

        if (mode.getValue() == Mode.Vanilla) {
            if (mc.player == null || mc.world == null) {
                return;
            }
            double[] calc = MathUtil.directionSpeed(this.vanillaSpeed.getValue() / 10.0);
            mc.player.motionX = calc[0];
            mc.player.motionZ = calc[1];
        }

        if (mode.getValue() == Mode.yPort) {
            if (!PlayerUtil3.isMoving(mc.player) || mc.player.isInWater() && mc.player.isInLava() || mc.player.collidedHorizontally) {
                return;
            }
            if (mc.player.onGround) {
                EntityUtil.setTimer(1.15f);
                mc.player.jump();
                PlayerUtil3.setSpeed(mc.player, PlayerUtil3.getBaseMoveSpeed() + yPortSpeed.getValue() / 10);
            } else {
                mc.player.motionY = -1;
                EntityUtil.resetTimer();
            }
        }
    }
}