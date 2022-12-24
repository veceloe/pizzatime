package veceloe.pizzatime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final JavaMailSender javaMailSender;

    @GetMapping("/")
    public String home() {
        return "views/home";
    }

    @GetMapping("/home")
    public String index() {
        return "views/home";
    }

    @GetMapping("/about")
    public String about() {
        return "views/about";
    }

    @GetMapping("/order")
    public String order() {
        return "views/order";
    }

    @GetMapping("/basket")
    public String basket(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> bskt = new ArrayList<>();
        Basket basket = null;
        int sum = 0;
        for (Cookie cookie : request.getCookies()) {
            try {
                if ("basket".equals(cookie.getName()))
                    basket = mapper.readValue(cookie.getValue().replace("'", "\"").replace("a", ","), Basket.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (basket == null) basket = new Basket();
        for (Long p : basket.products) {
            bskt.add(productService.getById(p));
        }
        request.setAttribute("result", bskt);
        return "views/basket";
    }

    @GetMapping("/politics")
    public String politics() {
        return "views/politics";
    }

    @GetMapping("/catalog")
    public String catalog(HttpServletRequest request) {
        request.setAttribute("pizza", productService.getByType(ProductType.PIZZA));
        request.setAttribute("deserts", productService.getByType(ProductType.DESERTS));
        request.setAttribute("snacks", productService.getByType(ProductType.SNACKS));
        request.setAttribute("drinks", productService.getByType(ProductType.DRINKS));
        return "views/catalog";
    }

    @GetMapping("/menu")
    public String menu() {
        return "views/menu";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product hello(@PathVariable Long id) {
        return productService.getById(id);
    }

    @ResponseBody
    @GetMapping("/basket/{id}")
    public void toBasket(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();

        Basket basket = null;
        for (Cookie cookie : request.getCookies()) {
            try {
                if ("basket".equals(cookie.getName()))
                    basket = mapper.readValue(cookie.getValue().replace("'", "\"").replace("a", ","), Basket.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (basket == null) basket = new Basket();
        basket.products.add(id);

        try {
            String value = mapper.writeValueAsString(basket).replace("\"", "'").replace(",", "a");
            System.out.println(value);
            Cookie cookie = new Cookie("basket", value);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

            response.addCookie(cookie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/basket/remove/{id}")
    public String removeFromBasket(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
        ObjectMapper mapper = new ObjectMapper();

        Basket basket = null;
        for (Cookie cookie : request.getCookies()) {
            try {
                if ("basket".equals(cookie.getName()))
                    basket = mapper.readValue(cookie.getValue().replace("'", "\"").replace("a", ","), Basket.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (basket == null) basket = new Basket();
        basket.products.remove(id);

        try {
            String value = mapper.writeValueAsString(basket).replace("\"", "'").replace(",", "a");
            System.out.println(value);
            Cookie cookie = new Cookie("basket", value);
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            response.addCookie(cookie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "redirect:/basket";
    }

    @PostMapping("/basket/send")
    public String sendOrder(@RequestParam String phone, String email, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        ObjectMapper mapper = new ObjectMapper();

        Basket basket = null;
        for (Cookie cookie : request.getCookies()) {
            try {
                if ("basket".equals(cookie.getName()))
                    basket = mapper.readValue(cookie.getValue().replace("'", "\"").replace("a", ","), Basket.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (basket == null) basket = new Basket();

        String phoneNumberPattern = "(8|\\+7)\\d{10}";
        String emailAddressPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern phonePattern = Pattern.compile(phoneNumberPattern);
        Pattern emailPattern = Pattern.compile(emailAddressPattern);
        Matcher phoneMatcher = phonePattern.matcher(phone);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (phoneMatcher.matches() && emailMatcher.matches()) {
            StringBuilder order = new StringBuilder();
            order.append("телефон: ").append(phone).append('\n');
            order.append("e-mail: ").append(email).append('\n');
            order.append("Заказ: \n");
            for (Long id: basket.products) {
                order.append(productService.getById(id).getName()+" - "+productService.getById(id).getCost()+"\n");
            }
            order.append("Заказ будет скоро готов! Желаем приятного аппетита :)");

            System.out.println(phone);
            System.out.println(basket.products.toString());
            System.out.println(order);

            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
            messageHelper.setFrom("pizzatimegel@gmail.com");
            messageHelper.setTo(email);
            messageHelper.setSubject("Заказ");
            messageHelper.setText(order.toString(), false);
            javaMailSender.send(mail);

            basket.products.clear();
            try {
                String value = mapper.writeValueAsString(basket).replace("\"", "'").replace(",", "a");
                System.out.println(value);
                Cookie cookie = new Cookie("basket", value);
                cookie.setPath("/");
                cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                response.addCookie(cookie);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return "redirect:/order";
        } else {
            return "redirect:/basket";
        }
    }
}