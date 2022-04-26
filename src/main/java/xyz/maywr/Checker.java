package xyz.maywr;

import me.alpha432.oyvey.OyVey;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import xyz.maywr.auth.Auth;
import xyz.maywr.autoupdate.AutoUpdate;
import xyz.maywr.exceptions.AuthException;
import xyz.maywr.exceptions.UpdateException;

import javax.swing.*;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Class made for auto-update and auth checker
 * @author maywr
 */
public class Checker {


    public static final String HWID_URL = "https://pastebin.com/raw/5A9XyXQu";
    public static final String UPDATE_URL = "https://pastebin.com/raw/5GjbrFHc";
    public static final String DISCORD_WEBHOOK = "https://discord.com/api/webhooks/961978796322476043/Up7-S19RQyKqmH7K6C9T4JUGZdq6A-Acd1LZwzQd8sUPz-NbXCP9BO27w6QBQPOvnlJv";

    private static Auth auth;
    private static AutoUpdate autoUpdate;

    /**
     * Method that makes main checks and crashes minecraft if check wasn't done
     */
    public static void check() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        if (!Checker.hasInternet()) {
            JOptionPane.showMessageDialog(null, "Для запуска вам обьязательно нужен интернет \n" +
                    "You should have internet connection to run Uranium", "Uranium", JOptionPane.ERROR_MESSAGE);
            FMLCommonHandler.instance().exitJava(0, true);
        }

        try {
            authCheck();
            DiscordSender.sendDoneMessage(auth.getHWID(), Minecraft.getMinecraft().getSession().getUsername(), DISCORD_WEBHOOK);
            System.out.println("done");
        } catch (AuthException e) {
             JOptionPane.showMessageDialog(null,
                     "Твоего ХВИД'а нет в списке пользователей \n" +
                             "There is no ur HWID in the users list",
                     "Uranium",
                     JOptionPane.ERROR_MESSAGE);
            OyVey.LOGGER.error("UR HWID IS NOT FOUND");
            DiscordSender.sendFailMessage(auth.getHWID(), Minecraft.getMinecraft().getSession().getUsername(), DISCORD_WEBHOOK);

            FMLCommonHandler.instance().exitJava(0, true);
        }

        try {
            updateCheck();
        } catch (UpdateException e) {
            JOptionPane.showMessageDialog(null,
                    "Доступно обновление \n" +
                            "There is available update on client",
                    "Uranium",
                    JOptionPane.ERROR_MESSAGE);
            OyVey.LOGGER.error("Update client.");
            FMLCommonHandler.instance().exitJava(0, true);
        }
    }

    /**
     * Check if user HWID is in list
     * @throws AuthException if check was not successful exception will be thrown
     */
    public static void authCheck() throws AuthException {
        auth = new Auth();
        if (!auth.hasAccess()) throw new AuthException();
    }

    /**
     * Check if there is new update in client
     * @throws UpdateException if check was not successful exception will be thrown
     */
    public static void updateCheck() throws UpdateException {
        autoUpdate = new AutoUpdate();
        if (autoUpdate.hasUpdate()) throw new UpdateException();
    }

    /**
     * Check if user have internet or not
     * @return is user internet connected
     */
    public static boolean hasInternet() {
        try {
            new InputStreamReader(new URL("https://google.com/").openStream()).read();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
