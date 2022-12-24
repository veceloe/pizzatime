package veceloe.pizzatime;

public enum ProductType {
    PIZZA, DESERTS, SNACKS, DRINKS;
    public static ProductType find(String type){
        switch (type){
            case "Пицца":
                return PIZZA;
            case "Десерт":
                return DESERTS;
            case "Закуска":
                return SNACKS;
            case "Напиток":
                return DRINKS;
            default:
                return null;
        }
    }
}
