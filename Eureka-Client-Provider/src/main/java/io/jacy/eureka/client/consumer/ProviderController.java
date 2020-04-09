package io.jacy.eureka.client.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jacy
 */
@RestController
public class ProviderController {
    @GetMapping("/euk/provider")
    public Object provider1() {
        return "hello eureka client provider";
    }
}
