package me.alpha432.oyvey.features.command.commands;

import me.alpha432.oyvey.features.command.Command;

public class Clear
        extends Command {
    public Clear() {
        super("clear", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        mc.ingameGUI.getChatGUI().clearChatMessages(true);
    }
}