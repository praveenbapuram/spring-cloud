package com.in28minutes.microservices.currencyconversionservice;

        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RestController;
        import org.springframework.web.client.RestTemplate;

        import java.lang.invoke.MethodHandleInfo;
        import java.math.BigDecimal;
        import java.util.HashMap;
        import java.util.Map;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity){
        Map<String,String> uriVariables= new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurrencyConversionBean> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversionBean.class,uriVariables);
        CurrencyConversionBean response=responseEntity.getBody();

        return new CurrencyConversionBean(1L,from,to,quantity,response.getConversionMultiple(),quantity.multiply(response.getConversionMultiple()) ,response.getPort());
    }
}
