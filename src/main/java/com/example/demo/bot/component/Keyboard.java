package com.example.demo.bot.component;

import com.example.demo.bot.model.Friend;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class Keyboard {

    /**
     * Возвращение кнопок основной функциональности
     */
    public static ReplyKeyboard getDefaultKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow(); // кнопки быстрого доступа
        KeyboardRow row2 = new KeyboardRow(); // кнопки

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        // общие кнопки
        row1.add(Command.HELP.getLocalizedName());
        row1.add(Command.REGISTRATOIN.getLocalizedName());
        row1.add(Command.VALENTINE.getLocalizedName());

        row2.add(Command.ALL.getLocalizedName());
        row2.add(Command.BD.getLocalizedName());
        row2.add(Command.MEETING.getLocalizedName());

        keyboardRows.add(row1);
        keyboardRows.add(row2);

        keyboard.setKeyboard(keyboardRows);
        keyboard.setResizeKeyboard(true);
        return keyboard;
    }

    public static ReplyKeyboard getAll(List<Friend> friends) {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row1;
        for (Friend friend : friends) {
            row1 = new KeyboardRow();
            row1.add(friend.getName());
            keyboardRows.add(row1);
        }

        keyboard.setKeyboard(keyboardRows);
        keyboard.setResizeKeyboard(true);
        return keyboard;
    }
}
