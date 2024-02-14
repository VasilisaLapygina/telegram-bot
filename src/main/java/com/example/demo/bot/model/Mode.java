package com.example.demo.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Mode {
    VALENTINE,
    SEND_VALENTINE,
    NONE;
}
