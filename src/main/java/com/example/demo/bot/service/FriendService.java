package com.example.demo.bot.service;

import com.example.demo.bot.model.Friend;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Component
public class FriendService {
    private static final String FILE_ALL_FRIEND = "friend.yml";

    private static final String FILE_DIRECTORY = "./data";

    private Path path = Paths.get(FILE_DIRECTORY, FILE_ALL_FRIEND);

    /**
     * Метод выводит список всех друзей
     */
    public ArrayList<Friend> get() {
        try {
            return (ArrayList<Friend>) YamlDao.loadAs(path.toFile(), ArrayList.class);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

}
