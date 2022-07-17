package ru.egor.lol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    long id;
    private String name;
    private String description;
    private int cost;
    private String img;
    private ProductType type;
}