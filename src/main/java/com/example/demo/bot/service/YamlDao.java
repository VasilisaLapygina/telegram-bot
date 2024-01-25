package com.example.demo.bot.service;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class YamlDao {

    private static final DumperOptions DUMPER_OPTIONS;

    static {
        DUMPER_OPTIONS = new DumperOptions();
        DUMPER_OPTIONS.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    }

    private YamlDao() {
    }

    public static void dumpToFile(Object data, File file) throws IOException {
        if (!file.exists() && !file.getParentFile().mkdirs()) {
            // todo
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            new org.yaml.snakeyaml.Yaml(new SkipEmptyRepresenter(DUMPER_OPTIONS), DUMPER_OPTIONS).dump(data, fileWriter);
        }
    }

    public static <T> T loadAs(File fileName, Class<T> type) throws IOException {
        Representer representer = new Representer(DUMPER_OPTIONS);
        representer.getPropertyUtils().setSkipMissingProperties(true);
        try (FileReader fileReader = new FileReader(fileName)) {
            return new org.yaml.snakeyaml.Yaml(representer).loadAs(fileReader, type);
        }
    }
}
