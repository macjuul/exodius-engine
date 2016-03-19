package net.exodiusmc.engine.util;

import java.net.URL;

import javafx.scene.image.Image;

public class FileUtils {
    private static String resourceDirectory = "";
    
    public static void setResourceDirectory(String dir) {
        if(!dir.substring(0, 1).equals("/")) dir = "/" + dir;
        if(!dir.substring(dir.length() - 1, dir.length()).equals("/")) dir = dir + "/";
        FileUtils.resourceDirectory = dir;
    }
    
    public static String getResourceDirectory() {
        return FileUtils.resourceDirectory;
    }
    
    public static URL ResolveResource(String file) {
        return FileUtils.class.getResource(resourceDirectory + file);
    }
    
    public static Image LoadImage(String file) {
        return new Image(FileUtils.ResolveResource(file).toString());
    }
}
