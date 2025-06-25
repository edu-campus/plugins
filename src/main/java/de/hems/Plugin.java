package de.hems;

import de.hems.events.EventManager;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public abstract class Plugin implements PluginBase {
    private final EventManager eventManager;

    public Plugin() {
        eventManager = new EventManager();
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    @Override
    public String getName() {
        return (String) getYamlPluginConfig().get("name");
    }

    @Override
    public String getVersion() {
        return (String) getYamlPluginConfig().get("version");
    }

    private Map<String, Object> getYamlPluginConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("customer.yaml");
        Map<String, Object> obj = yaml.load(inputStream);
        return obj;
    }
}
