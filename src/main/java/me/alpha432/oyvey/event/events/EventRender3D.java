package me.alpha432.oyvey.event.events;

import me.alpha432.oyvey.event.EventStage;


public class EventRender3D
        extends EventStage {
    public float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
    public float getPartialTicks() {
        return this.partialTicks;
    }
}