package com.example.Back_end.AllControllers;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;


import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;


@Controller
public class PostControllers {



    @GetMapping("/")
    public String comprar(ModelMap modelo) throws MPException, MPApiException {
        MercadoPagoConfig.setAccessToken("TEST-5275192284911339-112519-fb03f621c85e6d24411240c8f8f04546-1564723851");

        PreferenceBackUrlsRequest backUrls =
// ...
                PreferenceBackUrlsRequest.builder()
                        .success("http://localhost:4200/gracias")
                        //.pending("https://www.seu-site/pending")
                        //.failure("https://www.seu-site/failure")
                        .build();

      //  PreferenceRequest request = PreferenceRequest.builder().backUrls(backUrls).build();


        PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                        .id("1234")
                        .title("Games")
                        .description("PS5")
                        .pictureUrl("http://picture.com/PS5")
                        .categoryId("games")
                        .quantity(2)
                        .currencyId("BRL")
                        .unitPrice(new BigDecimal("4000"))
                        .build();
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items).backUrls(backUrls).build();

        PreferenceClient client = new PreferenceClient();

        Preference preference = client.create(preferenceRequest);

       // Map<String,String> mapi = new HashMap<>();
       // mapi.put("llave",preference.getSandboxInitPoint());
        modelo.addAttribute("llave",preference.getSandboxInitPoint());
      return "index.html";
    }
}
