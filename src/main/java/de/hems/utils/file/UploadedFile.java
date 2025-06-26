package de.hems.utils.file;

import java.util.UUID;

public class UploadedFile {
    private UUID uuid;
    private String host;
    public UploadedFile(UUID uuid, String host) {
        this.uuid = uuid;
        this.host = host;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getURL() {
        return host + "/api/files/" + uuid;
    }

    public String getHost() {
        return host;
    }
}
