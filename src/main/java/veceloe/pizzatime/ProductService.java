package veceloe.pizzatime;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private Map<Long, Product> products = new HashMap<>();

    public ProductService() {
        products.put(1L, Product.builder().id(1L).type(ProductType.PIZZA).name("Пепперони").description("Свинина, помидоры, моцарелла").cost(450).img("https://dodopizza-a.akamaihd.net/static/Img/Products/Pizza/ru-RU/2822916b-039c-4dfc-916a-73ca0daa9320.jpg").build());
        products.put(2L, Product.builder().id(2L).type(ProductType.PIZZA).name("Пицца с грибами").description("Грибы, курица, вечина, пармизан").cost(500).img("https://amigo-pizza.ru/images/pim.png").build());
        products.put(3L, Product.builder().id(3L).type(ProductType.PIZZA).name("Маргарита").description("Помидоры, базилик, моцарелла").cost(450).img("https://sergiopizza.ru/upload/iblock/62d/62d1f782510bae92ce57457bac30e989.png").build());
        products.put(4L, Product.builder().id(4L).type(ProductType.PIZZA).name("Итальянская").description("Ветчина, оливки, б.перец, пармизан").cost(450).img("https://sergiopizza.ru/upload/iblock/426/426851cd0950177a042c6bf75514a34a.png").build());
        products.put(5L, Product.builder().id(5L).type(ProductType.DESERTS).name("Чизкейк").description("Творожное суфле, песочная основа, ореховая крошка").cost(100).img("https://lh3.googleusercontent.com/proxy/u9WaNioXU5dxQv6dFCj6wi8Abx1-K8lEK0UFjxJTIQTm_Z7kZGGNaB0YrtqtY0x4Pa2WHtvUztYnfMWdMyGVi9ZkZOwEi2HsopnNbpGsrne-VSrV3ep-c1P4F4wrNsXH17TRxoBUjT1VqIfsxrfdxQ").build());
        products.put(6L, Product.builder().id(6L).type(ProductType.DESERTS).name("Маффин с курагой").description("Курага, изюм, подсыпка сахарная, ваниль").cost(70).img("http://pngimg.com/uploads/muffin/muffin_PNG118.png").build());
        products.put(7L, Product.builder().id(7L).type(ProductType.DESERTS).name("Мороженое с клубникой").description("Клубника, асссорти шариков мороженого, рожок").cost(100).img("https://storage.googleapis.com/multi-static-content/previews/artage-io-thumb-e6972c4ea34284e70a65a0739c11d2a6.png").build());
        products.put(8L, Product.builder().id(8L).type(ProductType.DESERTS).name("Шарлотка").description("Плотное тесто, яблоки, мед, корица, сахар").cost(100).img("https://www.timefry.ru/img/site/mixes_and_dishes/charlotte.png").build());
        products.put(9L, Product.builder().id(9L).type(ProductType.SNACKS).name("Картофель фри").description("Картофель мелконарезанный").cost(50).img("https://dodopizza-a.akamaihd.net/static/Img/Products/e3eeee00e41c4b2cb4f3f5f2fc0f504e_292x292.jpeg").build());
        products.put(10L, Product.builder().id(10L).type(ProductType.SNACKS).name("Картофель по-деревенски").description("Картофель крупнонарезанный").cost(100).img("https://fh-ufa.ru/wp-content/uploads/2019/11/%D0%9A%D0%B0%D1%80%D1%82%D0%BE%D1%88%D0%BA%D0%B0-%D0%BF%D0%BE-%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%B5%D0%BD%D1%81%D0%BA%D0%B8-e1574857001732.png").build());
        products.put(11L, Product.builder().id(11L).type(ProductType.SNACKS).name("Салат греческий").description("Овощи с сиртаки").cost(100).img("https://darpizza.com/image/cache/catalog/products/salats/grecheskij-500x500.png").build());
        products.put(12L, Product.builder().id(12L).type(ProductType.SNACKS).name("Салат овощной").description("Свежие овощи под маслом").cost(80).img("https://lh3.googleusercontent.com/proxy/KB-9unUeaCFtS2lFYdNv22JL6H_U-E1tVxm1caSS8OBiBLAPNgdPOpf9HEFMqtt4rDOmFOmgt8gNP0CvbBSIU3aOY25dYIs5Iw8rQ8xE7ZOHP7VqRwBF24uVbp9BgbkkvPWavx69aVVc93x5DimTihCdy6DfNtgYrxQBif1-AACedfFN0TAnpkSBUitPflWwvttIuvLxwCOMS2N31hflaOokf8-zZLg").build());
        products.put(13L, Product.builder().id(13L).type(ProductType.DRINKS).name("Coca-Cola").description("0,5л").cost(50).img("https://www.officemag.ru/goods/620870/49061b8b93af1bc7a9ff07b2892d49c1_xl.jpg").build());
        products.put(14L, Product.builder().id(14L).type(ProductType.DRINKS).name("Fuzetea").description("1л").cost(70).img("https://mineralka.store/wa-data/public/shop/products/86/90/9086/images/6477/6477.970.jpg").build());
        products.put(15L, Product.builder().id(15L).type(ProductType.DRINKS).name("Sprite").description("1л").cost(60).img("https://avatanplus.com/files/resources/original/579769db455d615627758094.png").build());
        products.put(16L, Product.builder().id(16L).type(ProductType.DRINKS).name("Fanta").description("0,5л").cost(50).img("https://pngimg.com/uploads/fanta/fanta_PNG10.png").build());
    }
    public Product getById(Long id) {
        Product res = new Product();
        for (Product product : products.values())
            if (product.id == id) res=product;
        return res;
    }

    public Product get(Long id) {
        return products.get(id);
    }


    public List<Product> getAll() {
        return new ArrayList<>(products.values());
    }

    public List<Product> getByType(ProductType type) {
        List<Product> result = new ArrayList();
        for (Product product : products.values())
            if (product.getType().equals(type)) result.add(product);
        return result;
    }
}

