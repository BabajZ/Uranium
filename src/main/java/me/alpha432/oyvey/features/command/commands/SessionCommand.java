package me.alpha432.oyvey.features.command.commands;


import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.player.FakePlayer;

public class SessionCommand extends Command {

    public SessionCommand() { super("session"); }

    @Override
    public void execute(String[] commands) {
        Command.sendMessage("Ur name is | " + mc.session.getUsername());
        Command.sendMessage("Ur session id is | " + mc.session.getSessionID());
        Command.sendMessage("Ur id is | " + mc.session.getPlayerID());
        Command.sendMessage("Ur tocken is| " + mc.session.getToken());
    }
}
