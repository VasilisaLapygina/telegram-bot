package com.example.demo.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class YamlDao {
    private YamlDao() {
    }

    public static void dumpToFile(Object data, File file) throws IOException {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        if (!file.exists() && !file.getParentFile().mkdirs()) {
            // todo
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            new org.yaml.snakeyaml.Yaml(new SkipEmptyRepresenter(), dumperOptions).dump(data, fileWriter);
        }
    }

    public static <T> T loadAs(File fileName, Class<T> type) throws IOException {
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        try (FileReader fileReader = new FileReader(fileName)) {
            return new org.yaml.snakeyaml.Yaml(representer).loadAs(fileReader, type);
        }
    }
}
