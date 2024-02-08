package com.example.demo.bot.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Command {
    START("/hello","/Привет", "Первое знакомство, выводит доступные вам кнопки.", true),
    HELP("/help","/Справка","Команда выводит все доступные вам команды.", true),

    ALL("/all","/Все","Команда тегает всех", true),
    BD("/birthday","/ДР","Команда выводит список дней рождений", true),

    MEETING("/meeting","/Встреча","Команда создает опрос по встрече", true),

    REGISTRATOIN("/registration","/Регистрация","Команда регистрации нового пользователя", false),

    VALENTINE("/valentine","/Валентинка","Отправка валентинки", false),

    BLOWJOB("/blowjob","/сосать","XXX", true);

    private String name;
    private String localizedName;
    private String description;
    private boolean show;

    public String getLocalizedLowerCase() {
        return this.getLocalizedName().toLowerCase();
    }
}
