package me.itzblacky.ItzPermission.Utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    public static boolean copy(InputStream source, String destination) {
        boolean success = true;
        File dir = new File(new File(destination).getParentFile().getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            source.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            success = false;
        }
        return success;
    }

    public static <T> T loadYaml(String path, Class<T> toCast) {
        T config = null;
        try {
            InputStream stream = new FileInputStream(path);
            Yaml yaml = new Yaml(new Constructor(toCast));
            config = yaml.load(stream);
            stream.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return config;
    }
    public static <T> T loadYaml(File file, Class<T> toCast) {
        return loadYaml(file.getAbsolutePath(), toCast);
    }
}
