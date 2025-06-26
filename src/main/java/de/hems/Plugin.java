package de.hems;

import de.hems.events.EventManager;
import de.hems.utils.exeptions.NoInitializationException;
import de.hems.utils.exeptions.SecondInitializationException;
import de.hems.utils.file.FileUpload;
import de.hems.utils.file.TrippleFunction;
import de.hems.utils.file.UploadedFile;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;

public abstract class Plugin implements PluginBase {
    private final EventManager eventManager;
    private FileUpload fileUpload;

    public Plugin() {
        eventManager = new EventManager();
    }

    public void setup(TrippleFunction<byte[], String, String, UploadedFile> fileUploadMethod) throws SecondInitializationException {
        if (fileUpload != null) throw new SecondInitializationException("The plugin " + getName() + " has already been setup.");
        fileUpload = new FileUpload(fileUploadMethod);
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
                .getResourceAsStream("plugin.yml");
        Map<String, Object> obj = yaml.load(inputStream);
        return obj;
    }

    public FileUpload getFileUpload() {
        if (fileUpload == null) throw new NoInitializationException("The plugin " + getName() + " has not been setup yet.");
        return fileUpload;
    }
}
