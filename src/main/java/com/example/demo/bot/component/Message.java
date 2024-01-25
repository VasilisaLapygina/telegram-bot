package com.example.demo.bot.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    HELLO("Всем привет, я ваш персональный Пиздюк.");

    private String text;
}
