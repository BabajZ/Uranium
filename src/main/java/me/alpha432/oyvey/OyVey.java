package me.alpha432.oyvey;

import me.alpha432.oyvey.manager.*;
import me.alpha432.oyvey.util.Block.Render.IconUtil;
import net.minecraft.util.Util;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import xyz.maywr.Checker;

import java.io.InputStream;
import java.nio.ByteBuffer;

@Mod(modid = "oyvey", name = "OyVey", version = "0.0.3")
public class OyVey {
    public static final String MODID = "uranium1";
    public static final String MODNAME = "UraniumClient";
    public static final String MODVER = "0.0.1";
    public static final Logger LOGGER = LogManager.getLogger("UraniumClient");
    public static CommandManager commandManager;
    public static FriendManager friendManager;
    public static ModuleManager moduleManager;
    public static PacketManager packetManager;
    public static ColorManager colorManager;
    public static HoleManager holeManager;
    public static InventoryManager inventoryManager;
    public static PotionManager potionManager;
    public static PRotationManager ProtationManager;
    public static TimerManager timerManager;
    public static RotationManager rotationManager;
    public static PositionManager positionManager;
    public static SpeedManager speedManager;
    public static ReloadManager reloadManager;
    public static FileManager fileManager;
    public static ConfigManager configManager;
    public static ServerManager serverManager;
    public static EventManager eventManager;
    public static TextManager textManager;
    @Mod.Instance
    public static OyVey INSTANCE;
    private static boolean unloaded;

    static {
        unloaded = false;
    }

    public static void load() {
        LOGGER.info("\n\nLoading UraniumClient by dazed68");
        unloaded = false;
        if (reloadManager != null) {
            reloadManager.unload();
            reloadManager = null;
        }
        textManager = new TextManager();
        commandManager = new CommandManager();
        friendManager = new FriendManager();
        moduleManager = new ModuleManager();
        rotationManager = new RotationManager();
        packetManager = new PacketManager();
        ProtationManager = new PRotationManager();
        eventManager = new EventManager();
        speedManager = new SpeedManager();
        timerManager = new TimerManager();
        potionManager = new PotionManager();
        inventoryManager = new InventoryManager();
        serverManager = new ServerManager();
        fileManager = new FileManager();
        colorManager = new ColorManager();
        positionManager = new PositionManager();
        configManager = new ConfigManager();
        holeManager = new HoleManager();
        LOGGER.info("Managers loaded.");
        moduleManager.init();
        LOGGER.info("Modules loaded.");
        configManager.init();
        eventManager.init();
        LOGGER.info("EventManager loaded.");
        textManager.init(true);
        moduleManager.onLoad();
        LOGGER.info("Uranium successfully loaded!\n");
    }

    public static void unload(boolean unload) {
        LOGGER.info("\n\nUnloading Uranium by dazed68");
        if (unload) {
            reloadManager = new ReloadManager();
            reloadManager.init(commandManager != null ? commandManager.getPrefix() : ".");
        }
        OyVey.onUnload();
        eventManager = null;
        friendManager = null;
        speedManager = null;
        ProtationManager = null;
        holeManager = null;
        timerManager = null;
        positionManager = null;
        rotationManager = null;
        configManager = null;
        commandManager = null;
        colorManager = null;
        serverManager = null;
        fileManager = null;
        potionManager = null;
        inventoryManager = null;
        moduleManager = null;
        textManager = null;
        LOGGER.info("UraniumClient unloaded!\n");
    }

    public static void reload() {
        OyVey.unload(false);
        OyVey.load();
    }

    public static void onUnload() {
        if (!unloaded) {
            eventManager.onUnload();
            moduleManager.onUnload();
            configManager.saveConfig(OyVey.configManager.config.replaceFirst("oyvey/", ""));
            moduleManager.onUnloadPost();
            unloaded = true;
        }
    }


    public static String getVersion() {
        return MODVER;
    }


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("I am gona gas you kike - dazed68");
        Checker.check();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        setWindowIcon();
        OyVey.load();
    }

    public static void setWindowIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try (InputStream inputStream16x = OyVey.class.getClassLoader().getResourceAsStream("ico1.png");
                 InputStream inputStream32x = OyVey.class.getClassLoader().getResourceAsStream("ico2.png")) {
                ByteBuffer[] icons = new ByteBuffer[]{IconUtil.INSTANCE.readImageToBuffer(inputStream16x), IconUtil.INSTANCE.readImageToBuffer(inputStream32x)};
                Display.setIcon(icons);
            } catch (Exception e) {
                OyVey.LOGGER.error("Couldn't set Windows Icon", e);
            }
        }
    }

    private void setWindowsIcon() {
        OyVey.setWindowIcon();
    }
    @Mod.EventHandler
    public void init(FMLEvent event) {
        OyVey.load();
        setWindowsIcon();
    }

}

