package veceloe.pizzatime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Data
public class Basket {
    List<Long> products = new ArrayList<>();

    static Basket getBasket(HttpServletRequest request, ObjectMapper mapper, Basket basket) {
        if (request.getCookies() == null) return new Basket();
        for (Cookie cookie : request.getCookies()) {
            try {
                if ("basket".equals(cookie.getName()))
                    basket = mapper.readValue(cookie.getValue().replace("'", "\"").replace("a", ","), Basket.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (basket == null) basket = new Basket();
        return basket;
    }

    static void createAndAddCookieFromBasket(HttpServletResponse response, ObjectMapper mapper, Basket basket) {
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

}