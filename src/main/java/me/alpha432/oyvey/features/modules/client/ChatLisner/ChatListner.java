package me.alpha432.oyvey.features.modules.client.ChatLisner;

import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ChatListner extends Module {

    DiscordWebhook webhookRestart = cfg.restart;
    Minecraft mc = Minecraft.getMinecraft();




    public ChatListner() {
        super("+ ChatListner", "counts burrows", Category.CLIENT, true, false, false);
    }

    @SubscribeEvent
    public void onEnable(ClientChatReceivedEvent e){
            Date date = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");
            String strDate = dateFormatter.format(date);
            TextComponentString time = new TextComponentString( "[" + strDate  + "]" + " ");
            e.setMessage(time.appendSibling(e.getMessage()));

            String msg = e.getMessage().getUnformattedText();
            String server = mc.getCurrentServerData().populationInfo;
            String server1 = mc.getCurrentServerData().serverName;
            String server2 = mc.getCurrentServerData().playerList;


            System.out.println(server2);

            try {
                webhookRestart.setContent("```" + msg + "```");
                webhookRestart.execute();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



