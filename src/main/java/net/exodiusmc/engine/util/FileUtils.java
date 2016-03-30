package net.exodiusmc.engine.util;

import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FileUtils {
    private static String resourceDirectory = "";
    
    public static void setResourceDirectory(String dir) {
        if(dir.substring(0, 1).equals("/")) dir = dir.substring(1);
        if(!dir.substring(dir.length() - 1, dir.length()).equals("/")) dir = dir + "/";
        FileUtils.resourceDirectory = dir;
    }
    
    public static String getResourceDirectory() {
        return FileUtils.resourceDirectory;
    }
    
    public static URL ResolveResource(String file) {
        return FileUtils.class.getClassLoader().getResource(resourceDirectory + file);
    }
    
    public static Image LoadImage(String file) {
        return new Image(FileUtils.ResolveResource(file).toString());
    }
    
    public static Image getSubImage(Image img, int x, int y, int w, int h) {
    	PixelReader reader = img.getPixelReader();
		WritableImage image = new WritableImage(reader, x, y, w, h);
		return image;
    }
    
    public static Image colorizeImage(Image img, Color c, double a) {
    	PixelReader reader = img.getPixelReader();
		WritableImage write = new WritableImage((int) img.getWidth(), (int) img.getHeight());
		PixelWriter writer = write.getPixelWriter();
		
		for(int readY = 0; readY < img.getHeight(); readY++){
			for(int readX = 0; readX < img.getWidth(); readX++) {
				Color color = reader.getColor(readX, readY);
				if(color.getOpacity() == 0) continue;
				color = color.interpolate(c, a);
				writer.setColor(readX, readY, color);
			}
		}
		
		return (Image) write;
    }
}

