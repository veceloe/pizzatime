package veceloe.pizzatime;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int cost;
    @Column
    private String img;
    @Column
    private ProductType type;

    public Product(ProductType type, String name, String description, int cost, String src) {
        this.type = type;
        this.description = description;
        this.name = name;
        this.cost = cost;
        img = src;
    }
    private void setId(long id){
    }
}