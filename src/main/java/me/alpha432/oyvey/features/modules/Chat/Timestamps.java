package me.alpha432.oyvey.features.modules.Chat;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.alpha432.oyvey.features.modules.Module;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timestamps extends Module {

    public
    Timestamps ( ) {
        super ( "Timestamps" , "" , Category.CHAT , true , false , false );
    }


    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
        String strDate = dateFormatter.format(date);
        TextComponentString time = new TextComponentString(ChatFormatting.DARK_GREEN + "[" + ChatFormatting.GREEN + strDate + ChatFormatting.DARK_GREEN + "]" + ChatFormatting.RESET + " ");
        event.setMessage(time.appendSibling(event.getMessage()));
    }
    public void onDisable() {MinecraftForge.EVENT_BUS.unregister(this);}
}