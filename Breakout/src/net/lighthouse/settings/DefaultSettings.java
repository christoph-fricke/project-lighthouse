package net.lighthouse.settings;

import java.util.HashMap;

/**
 * Default settings to fall back to when something goes wrong with
 * initiating the user settings.
 *
 * To add a new setting you have to add the settings key the the valid settings array and put a default
 * value in the settings HashMap.
 *
 * @author Christoph Fricke
 */
final class DefaultSettings {

    /**
     * HashMap which stores the settings.
     */
    private HashMap<String, String> settings;

    /**
     * Array with all valid setting keys. This property can be accessed from other classes so it should
     * not be a local variable.
     */
    final String[] validSettings = {
        "user-name", "token", "web-view", "use_new_Viewport", "frametime", "print-frametimes", "save-framebuffer"
    };

    /**
     * Creates new default settings.
     */
    DefaultSettings() {
        settings = new HashMap<>();

        settings.put(validSettings[0], "");
        settings.put(validSettings[1], "");
        settings.put(validSettings[2], "false");
        settings.put(validSettings[3], "true");
        settings.put(validSettings[4], "40");
        settings.put(validSettings[5], "false");
        settings.put(validSettings[6], "false");
        
    }

    /**
     * Returns a HashMap with the default settings.
     *
     * @return Returns the Default settings.
     */
    HashMap<String, String> getSettings() {
        return settings;
    }

}
