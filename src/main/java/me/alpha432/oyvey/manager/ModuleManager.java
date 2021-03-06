package me.alpha432.oyvey.manager;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.alpha432.oyvey.OyVey;
import me.alpha432.oyvey.event.events.Render2DEvent;
import me.alpha432.oyvey.event.events.Render3DEvent;
import me.alpha432.oyvey.features.Feature;
import me.alpha432.oyvey.features.gui.OyVeyGui;
import me.alpha432.oyvey.features.modules.Chat.*;
import me.alpha432.oyvey.features.modules.Exploit.*;
import me.alpha432.oyvey.features.modules.Exploit.BuildHeight;
import me.alpha432.oyvey.features.modules.Exploit.PacketFlyNew;
import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.modules.client.*;
import me.alpha432.oyvey.features.modules.client.ActionsNotifer.ActionsNotifer;
import me.alpha432.oyvey.features.modules.combat.*;
import me.alpha432.oyvey.features.modules.misc.*;
import me.alpha432.oyvey.features.modules.misc.AutiFish.AutoFish;
import me.alpha432.oyvey.features.modules.movement.*;
import me.alpha432.oyvey.features.modules.player.*;
import me.alpha432.oyvey.features.modules.player.scaffold.Scaffold;
import me.alpha432.oyvey.features.modules.render.*;
import me.alpha432.oyvey.features.modules.render.ItemPhysics.ItemPhysics;
import me.alpha432.oyvey.features.modules.render.PigPOV.PigPOV;
import me.alpha432.oyvey.features.modules.render.ToolTips;
import me.alpha432.oyvey.features.modules.render.ViewModel.ItemViewModel;
import me.alpha432.oyvey.util.Util;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ModuleManager
        extends Feature {
    public ArrayList<Module> modules = new ArrayList();
    public List<Module> sortedModules = new ArrayList<Module>();
    public List<String> sortedModulesABC = new ArrayList<String>();
    public Animation animationThread;

    public void init() {
        this.modules.add(new ClickGui());
        this.modules.add(new FontMod());
        this.modules.add(new ExtraTab());
        this.modules.add(new HUD());
        this.modules.add(new AutoGG());
        this.modules.add(new CrystalAura());
        this.modules.add(new AutoArmor());
        this.modules.add(new AutoTrap());
        this.modules.add(new AutoWeb());
        this.modules.add(new HoleFiller());
        this.modules.add(new Offhand());
        this.modules.add(new Selftrap());
        this.modules.add(new Surround());
        this.modules.add(new NoHandShake());
        this.modules.add(new PopCounter());
        this.modules.add(new CustomTime());
        this.modules.add(new SkyColor());
        this.modules.add(new Fullbright());
        this.modules.add(new HoleESP());
        this.modules.add(new AutoCenter());
        this.modules.add(new Anchor());
        this.modules.add(new ItemViewModel());
        this.modules.add(new AntiLevitate());
        this.modules.add(new Step());
        this.modules.add(new ReverseStep());
        this.modules.add(new Replenish());
        this.modules.add(new BoatFly());
        this.modules.add(new Announcer());
        this.modules.add(new GhastNotifier());
        this.modules.add(new AutoSuicide());
        this.modules.add(new BurrowCounter());
        this.modules.add(new GreenText());
        this.modules.add(new Timestamps());
        this.modules.add(new WeaknessLog());
        this.modules.add(new FoodFinder());
        this.modules.add(new FakePlayer());
        this.modules.add(new NoVoid());
        this.modules.add(new NoWeb());
        this.modules.add(new AutoClicker());
        this.modules.add(new BowSpam());
        this.modules.add(new EXPFast());
        this.modules.add(new AntiHunger());
        this.modules.add(new BoatPlace());
        this.modules.add(new EchestBP());
        this.modules.add(new AntiAFK());
        this.modules.add(new AutoLog());
        this.modules.add(new AutoReconnect());
        this.modules.add(new AutoRespawn());
        this.modules.add(new ByteDupe());
        this.modules.add(new BlockHighlight());
        this.modules.add(new NoBob());
        this.modules.add(new ToolTips());
        this.modules.add(new ArmorMessage());
        this.modules.add(new Flatten());
        this.modules.add(new AntiLog4j());
        this.modules.add(new BowMcBomb());
        this.modules.add(new HasteExploit());
        this.modules.add(new RPC());
        this.modules.add(new StrengthDetect());
        this.modules.add(new Animations());
        this.modules.add(new BurrowESP());
        this.modules.add(new PenisESP());
        this.modules.add(new Ranges());
        this.modules.add(new BuildHeight());
        this.modules.add(new Speed());
        this.modules.add(new GUIBlur());
        this.modules.add(new HitMarkers());
        this.modules.add(new Tracers());
        this.modules.add(new AutoBuilder());
        this.modules.add(new SkullBurrow());
        this.modules.add(new Burrow());
        this.modules.add(new Quiver());
        this.modules.add(new ChestSwap());
        this.modules.add(new Blink());
        this.modules.add(new AutoMine());
        this.modules.add(new SmartWalk());
        this.modules.add(new AutoTunnel());
        this.modules.add(new InfWalkSmart());
        this.modules.add(new BowAim());
        this.modules.add(new Aim());
        this.modules.add(new test());
        this.modules.add(new Shaders());
        this.modules.add(new TickShift());
        this.modules.add(new NoSoundLag());
        this.modules.add(new MCP());
        this.modules.add(new PKillaura());
        this.modules.add(new QueueMain());
        this.modules.add(new Overlay()  );
        this.modules.add(new Test()  );
        this.modules.add(new FakeCreative()  );
        this.modules.add(new HightJump()  );
        this.modules.add(new NameTags()  );
        this.modules.add(new ChestESP());
        this.modules.add(new BHOP());
        this.modules.add(new PlayerEntity());
        this.modules.add(new MobOwner());
        this.modules.add(new Spammer());
        this.modules.add(new ArrowESP());
        this.modules.add(new ChinaHat());
        this.modules.add(new MCF());
        this.modules.add(new Particles());
        this.modules.add(new NoForge());
        this.modules.add(new PigPOV());
        this.modules.add(new PacketEat());
        this.modules.add(new WebhookSpam());
        this.modules.add(new WaterLeave());
        this.modules.add(new HandChams());
        this.modules.add(new CrystalScale());
        this.modules.add(new AntiFog());
        this.modules.add(new CameraClip());
        this.modules.add(new EntityControl());
        this.modules.add(new Velocity());
        this.modules.add(new BuildHelper());
        this.modules.add(new AntiBot());
        this.modules.add(new BetterFPS());
        this.modules.add(new GlintModify());
        this.modules.add(new Criticals());
        this.modules.add(new AntiPackets());
        this.modules.add(new ItemPhysics());
        this.modules.add(new EntitySpeed());
        this.modules.add(new PacketFlyNew());
        this.modules.add(new AirJump());
        this.modules.add(new InvWalk());
        this.modules.add(new Scaffold());
        this.modules.add(new GuiGradient());
    }

    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }

    public <T extends Module> T getModuleByClass(Class<T> clazz) {
        for (Module module : this.modules) {
            if (!clazz.isInstance(module)) continue;
            return (T) module;
        }
        return null;
    }

    public void enableModule(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        if (module != null) {
            module.disable();
        }
    }

    public void enableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.enable();
        }
    }

    public void disableModule(String name) {
        Module module = this.getModuleByName(name);
        if (module != null) {
            module.disable();
        }
    }

    public boolean isModuleEnabled(String name) {
        Module module = this.getModuleByName(name);
        return module != null && module.isOn();
    }

    public boolean isModuleEnabled(Class<Module> clazz) {
        Module module = this.getModuleByClass(clazz);
        return module != null && module.isOn();
    }

    public Module getModuleByDisplayName(String displayName) {
        for (Module module : this.modules) {
            if (!module.getDisplayName().equalsIgnoreCase(displayName)) continue;
            return module;
        }
        return null;
    }

    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> enabledModules = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (!module.isEnabled()) continue;
            enabledModules.add(module);
        }
        return enabledModules;
    }

    public ArrayList<String> getEnabledModulesName() {
        ArrayList<String> enabledModules = new ArrayList<String>();
        for (Module module : this.modules) {
            if (!module.isEnabled() || !module.isDrawn()) continue;
            enabledModules.add(module.getFullArrayString());
        }
        return enabledModules;
    }

    public ArrayList<Module> getModulesByCategory(Module.Category category) {
        ArrayList<Module> modulesCategory = new ArrayList<Module>();
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                modulesCategory.add(module);
            }
        });
        return modulesCategory;
    }

    public List<Module.Category> getCategories() {
        return Arrays.asList(Module.Category.values());
    }

    public void onLoad() {
        this.modules.stream().filter(Module::listening).forEach(((EventBus) MinecraftForge.EVENT_BUS)::register);
        this.modules.forEach(Module::onLoad);
    }

    public void onUpdate() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onUpdate);
    }

    public void onTick() {
        this.modules.stream().filter(Feature::isEnabled).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        this.modules.stream().filter(Feature::isEnabled).forEach(module -> module.onRender3D(event));
    }

    public void sortModules(boolean reverse) {
        this.sortedModules = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> this.renderer.getStringWidth(module.getFullArrayString()) * (reverse ? -1 : 1))).collect(Collectors.toList());
    }

    public void sortModulesABC() {
        this.sortedModulesABC = new ArrayList<String>(this.getEnabledModulesName());
        this.sortedModulesABC.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }

    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }

    public void onUnload() {
        this.modules.forEach(MinecraftForge.EVENT_BUS::unregister);
        this.modules.forEach(Module::onUnload);
    }

    public void onUnloadPost() {
        for (Module module : this.modules) {
            module.enabled.setValue(false);
        }
    }

    public void onKeyPressed(int eventKey) {
        if (eventKey == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof OyVeyGui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == eventKey) {
                module.toggle();
            }
        });
    }

    private class Animation
            extends Thread {
        public Module module;
        public float offset;
        public float vOffset;
        ScheduledExecutorService service;

        public Animation() {
            super("Animation");
            this.service = Executors.newSingleThreadScheduledExecutor();
        }

        @Override
        public void run() {
            if (HUD.getInstance().renderingMode.getValue() == HUD.RenderingMode.Length) {
                for (Module module : ModuleManager.this.sortedModules) {
                    String text = module.getDisplayName() + ChatFormatting.GRAY + (module.getDisplayInfo() != null ? " [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]" : "");
                    module.offset = (float) ModuleManager.this.renderer.getStringWidth(text) / HUD.getInstance().animationHorizontalTime.getValue().floatValue();
                    module.vOffset = (float) ModuleManager.this.renderer.getFontHeight() / HUD.getInstance().animationVerticalTime.getValue().floatValue();
                    if (module.isEnabled() && HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (!(module.arrayListOffset > module.offset) || Util.mc.world == null) continue;
                        module.arrayListOffset -= module.offset;
                        module.sliding = true;
                        continue;
                    }
                    if (!module.isDisabled() || HUD.getInstance().animationHorizontalTime.getValue() == 1) continue;
                    if (module.arrayListOffset < (float) ModuleManager.this.renderer.getStringWidth(text) && Util.mc.world != null) {
                        module.arrayListOffset += module.offset;
                        module.sliding = true;
                        continue;
                    }
                    module.sliding = false;
                }
            } else {
                for (String e : ModuleManager.this.sortedModulesABC) {
                    Module module = OyVey.moduleManager.getModuleByName(e);
                    String text = module.getDisplayName() + ChatFormatting.GRAY + (module.getDisplayInfo() != null ? " [" + ChatFormatting.WHITE + module.getDisplayInfo() + ChatFormatting.GRAY + "]" : "");
                    module.offset = (float) ModuleManager.this.renderer.getStringWidth(text) / HUD.getInstance().animationHorizontalTime.getValue().floatValue();
                    module.vOffset = (float) ModuleManager.this.renderer.getFontHeight() / HUD.getInstance().animationVerticalTime.getValue().floatValue();
                    if (module.isEnabled() && HUD.getInstance().animationHorizontalTime.getValue() != 1) {
                        if (!(module.arrayListOffset > module.offset) || Util.mc.world == null) continue;
                        module.arrayListOffset -= module.offset;
                        module.sliding = true;
                        continue;
                    }
                    if (!module.isDisabled() || HUD.getInstance().animationHorizontalTime.getValue() == 1) continue;
                    if (module.arrayListOffset < (float) ModuleManager.this.renderer.getStringWidth(text) && Util.mc.world != null) {
                        module.arrayListOffset += module.offset;
                        module.sliding = true;
                        continue;
                    }
                    module.sliding = false;
                }
            }
        }

        @Override
        public void start() {
            System.out.println("Starting animation thread.");
            this.service.scheduleAtFixedRate(this, 0L, 1L, TimeUnit.MILLISECONDS);
        }
    }
}

