package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.features.command.Command;
import me.alpha432.oyvey.features.modules.player.FakePlayer;

public class FakePlayerCommand extends Command {

    public FakePlayerCommand() { super("fakeplayer"); }

    @Override
    public void execute(String[] commands) {
        FakePlayer.getInstance().enable();
    }
}
