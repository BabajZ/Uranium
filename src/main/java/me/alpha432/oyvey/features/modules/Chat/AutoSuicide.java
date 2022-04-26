package me.alpha432.oyvey.features.modules.Chat;

import me.alpha432.oyvey.features.modules.Module;
import me.alpha432.oyvey.features.setting.Setting;
import net.minecraft.network.play.client.CPacketChatMessage;


public
class AutoSuicide extends Module {

    private final Setting< Boolean > suicide = register ( new Setting < Boolean > ( "suicide" , false ) );


    public
    AutoSuicide ( ) {
        super ( "AutoSuicide" , "commits suicide" , Category.CHAT , true , false , false );
    }

    @Override
    public
    void onEnable ( ) {
        if ( suicide.getValue ( ) ) {
            mc.player.connection.sendPacket ( new CPacketChatMessage ( "/suicide" ) );
            suicide.setValue ( false );
            toggle ( );
        } else
            mc.player.connection.sendPacket ( new CPacketChatMessage ( "/kill" ) );
        toggle ( );
    }
}

