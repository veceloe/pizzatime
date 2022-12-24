package veceloe.pizzatime;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private List<Product> dataExtract(String fileLocation) throws IOException {
        FileInputStream file = new FileInputStream(fileLocation);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<Product> products = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum()==0) continue;
            List<String> data = new ArrayList<>();
            for (Cell cell : row) {
                switch (cell.getCellType()){
                    case STRING: data.add(cell.getStringCellValue()); break;
                    case NUMERIC: data.add(String.valueOf((int)cell.getNumericCellValue())); break;
                }
            }
            products.add(new Product(ProductType.find(data.get(0)), data.get(1), data.get(2), Integer.valueOf(data.get(3)), data.get(4)));
        }
        System.out.println(products);
        return products;
    }

    public ProductService(ProductRepository productRepository) throws IOException {
        this.productRepository = productRepository;
        List<Product> products = dataExtract("/Users/veceloe/GitHub/pizzatime/assets/data.xlsx");
        for (Product product: products) {
            productRepository.save(product);
        }
        /*productRepository.save(new Product(ProductType.PIZZA, "Пепперони", "Свинина, помидоры, моцарелла", 450, "https://dodopizza-a.akamaihd.net/static/Img/Products/Pizza/ru-RU/2822916b-039c-4dfc-916a-73ca0daa9320.jpg"));
        productRepository.save(new Product(ProductType.DESERTS, "Чизкейк", "Творожное суфле, песочная основа, ореховая крошка", 150, "https://lh3.googleusercontent.com/proxy/u9WaNioXU5dxQv6dFCj6wi8Abx1-K8lEK0UFjxJTIQTm_Z7kZGGNaB0YrtqtY0x4Pa2WHtvUztYnfMWdMyGVi9ZkZOwEi2HsopnNbpGsrne-VSrV3ep-c1P4F4wrNsXH17TRxoBUjT1VqIfsxrfdxQ"));
        productRepository.save(new Product(ProductType.SNACKS, "Картофель фри", "Картофель мелконарезанный", 50, "https://dodopizza-a.akamaihd.net/static/Img/Products/e3eeee00e41c4b2cb4f3f5f2fc0f504e_292x292.jpeg"));
        productRepository.save(new Product(ProductType.DRINKS, "Добрый Апельсин", "Напиток сильногазированный, 1л", 100, "https://www.google.com/url?sa=i&url=https%3A%2F%2Flavka.yandex.ru%2F21621%2Fgood%2Fb26cdd6389d24eb9be0605306a0c7730000200010000&psig=AOvVaw38hGrhUAimeY5BMtzZ_pld&ust=1671932712759000&source=images&cd=vfe&ved=0CA8QjRxqFwoTCPipje2QkfwCFQAAAAAdAAAAABAE"));*/    }
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getByType(ProductType type) {
        List<Product> result = new ArrayList();
        for (Product product : productRepository.findAll())
            if (product.getType().equals(type)) result.add(product);
        return result;
    }
}

