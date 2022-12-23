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

    @GetMapping("/home2")
    public String home2(HttpServletRequest request) {
        request.setAttribute("pizza", productService.getByType(ProductType.PIZZA));
        request.setAttribute("decerts", productService.getByType(ProductType.DESERTS));
        request.setAttribute("snacks", productService.getByType(ProductType.SNACKS));
        request.setAttribute("drinks", productService.getByType(ProductType.DRINKS));
        return "views/home2";
    }

    @GetMapping("/menu")
    public String menu() {
        return "views/menu";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product hello(@PathVariable Long id) {
        return productService.get(id);
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
    public String removeFromBasket(@RequestParam String phone, String email, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
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

        StringBuilder order = new StringBuilder();
        order.append("телефон: ").append(phone).append('\n');
        order.append("e-mail: ").append(email).append('\n');
        order.append("Заказ: \n");
        for (Long id: basket.products) {
            order.append(productService.getById(id).getName()+" - "+productService.getById(id).getCost()+"\n");
        }

        System.out.println(phone);
        System.out.println(basket.products.toString());
        System.out.println(order);

        /* String phoneNumberPattern = "(8|\\+7)\\d{10}";
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            // input is a valid phone number
        } else {
            // input is not a valid phone number
        } */


        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
        messageHelper.setFrom("pizzatimegel@gmail.com");
        messageHelper.setTo(email);
        messageHelper.setSubject("Заказ");
        messageHelper.setText(order.toString(), false);
        javaMailSender.send(mail);

//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("to_1@gmail.com");
//        msg.setSubject("Заказ");
//        msg.setText(order.toString());
//        javaMailSender.send(msg);

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
    }
}