package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.features.command.Command;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * @author ligmaballz
 */
public final class IpCommand extends Command {

    public IpCommand() {
        super("ip", new String[0] );
    }

    @Override
    public
    void execute ( String[] commands ) {

        final Minecraft mc = Minecraft.getMinecraft();

        if (mc.getCurrentServerData() != null) {
            final StringSelection contents = new StringSelection(mc.getCurrentServerData().serverIP);
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(contents, null);
            Command.sendMessage("Copied IP to clipboard");
        } else {
            Command.sendMessage("Error, Join a server");
        }
    }
}