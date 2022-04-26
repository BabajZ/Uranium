package me.alpha432.oyvey.event.events;


import me.alpha432.oyvey.event.EventStage;

public class UpdateEvent
        extends EventStage {
    private final int stage;

    public UpdateEvent(int stage) {
        this.stage = stage;
    }

    public final int getStage() {
        return this.stage;
    }
}