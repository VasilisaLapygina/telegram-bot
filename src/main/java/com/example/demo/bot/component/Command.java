package com.example.demo.bot.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {
    START("/hello","/Привет", "Первое знакомство, выводит доступные вам кнопки."),
    HELP("/help","/Справка","Команда выводит все доступные вам команды."),

    ALL("/all","/Все","Команда тегает всех"),
    BD("/birthday","/ДР","Команда выводит список дней рождений"),

    MEETING("/meeting","/Встреча","Команда создает опрос по встрече");

    private String name;
    private String localizedName;
    private String description;

    public String getLocalizedLowerCase() {
        return this.getLocalizedName().toLowerCase();
    }
}
