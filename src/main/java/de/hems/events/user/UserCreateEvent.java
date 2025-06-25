package de.hems.events.user;

import de.hems.events.Event;

import java.util.UUID;

public class UserCreateEvent extends Event {
    private UUID uuid;
    private String name;
    private String email;

    public UserCreateEvent(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
