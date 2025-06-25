package de.hems;

public interface PluginBase {
    public void onLoad();
    public void onEnable();
    public void onDisable();
    public String getName();
    public String getVersion();
}
