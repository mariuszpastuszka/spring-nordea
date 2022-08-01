package com.mpas.cems.boot1;

import org.springframework.context.ApplicationContext;

import static com.mpas.cems.BeanManager.asHtml;


//@RestController
public class CtxController {

    //@Autowired
    ApplicationContext ctx;

    //@GetMapping("/")
    public String index() {
        return asHtml.apply(ctx);
    }
}
