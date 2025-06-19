package de.hems;

public class Plugin {

    private final String name;

    public Plugin(String name){
        this.name = name;
    }

    public void onLoad(){}
    public void onEnable(){}
    public void onDisable(){}

    public String getName() {
        return name;
    }
}
