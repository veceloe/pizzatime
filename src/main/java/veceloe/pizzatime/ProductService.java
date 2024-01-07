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

    private List<Product> dataExtract() throws IOException {
        FileInputStream file = new FileInputStream("/app/assets/data.xlsx");
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
            products.add(new Product(ProductType.find(data.get(0)), data.get(1), data.get(2), Integer.parseInt(data.get(3)), data.get(4)));
        }
        System.out.println(products);
        return products;
    }

    public ProductService(ProductRepository productRepository) throws IOException {
        this.productRepository = productRepository;
        List<Product> products = dataExtract();
        productRepository.saveAll(products);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> getByType(ProductType type) {
        List<Product> result = new ArrayList<>();
        for (Product product : productRepository.findAll())
            if (product.getType().equals(type)) result.add(product);
        return result;
    }
}

