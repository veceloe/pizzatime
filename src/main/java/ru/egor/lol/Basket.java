package ru.egor.lol;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Basket {
    List<Long> products = new ArrayList<>();
}