package org.computerShop.service.impl;

import org.computerShop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Service
public class MailContentBuilder {
    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine){
        this.templateEngine = templateEngine;
    }

    public String createProductTemplate(Product product){
        Context context = new Context();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/mail/");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setTemplateMode(TemplateMode.CSS);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(0);
        context.setVariable("product", product);
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine.process("index.html", context);
    }


}
