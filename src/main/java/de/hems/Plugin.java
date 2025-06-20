package de.hems;

public interface Plugin {

    public void onLoad();
    public void onEnable();
    public void onDisable();
    public String getName();
}
