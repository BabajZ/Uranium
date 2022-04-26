package me.alpha432.oyvey.features.command.commands;


import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.util.oyvey.MessageUtil;
import net.minecraft.client.Minecraft;

public class ServerCommand extends Command {
    public ServerCommand() {

        super ( "server" , new String[0] );}


    public void execute(String[] commands) {

        final Minecraft mc = Minecraft.getMinecraft();

        if (mc.getCurrentServerData() != null) {
            Command.sendMessage("Name: " + mc.getCurrentServerData().serverName +
                    "IP: " + mc.getCurrentServerData().serverIP +
                    "MOTD: " + mc.getCurrentServerData().serverMOTD +
                    "VERSION: " + mc.getCurrentServerData().version +
                    "Players: " + mc.getCurrentServerData().playerList +
                    "PINGED: " + mc.getCurrentServerData().pinged +
                    "POPULATIONINFO: " + mc.getCurrentServerData().populationInfo +
                    "PINGTOSERVER: " + mc.getCurrentServerData().pingToServer);
        } else {
            MessageUtil.sendError("Maybe Join a server first? (No Server Data Found)");
        }
    }
}
