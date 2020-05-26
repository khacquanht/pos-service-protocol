package apigateway.apiunittest;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;
import com.wahsis.common.CommonService;
import com.wahsis.common.DefinedName;

public class NewsletterTemplateUnitTest {
//http://localhost:83/api/newsletter_template?cm=list&dt={template_code:0}
//http://localhost:83/api/newsletter_template?cm=list&dt={template_code:1}
//http://localhost:83/api/newsletter_template?cm=list&dt={template_code:1}

    public void addNewsletterTemplate() {
        com.wahsis.utility_service.NewsletterTemplate newslettertemplate = new com.wahsis.utility_service.NewsletterTemplate();
        StringBuilder vrfContent = new StringBuilder();
        newslettertemplate.setTemplateCode("template_code_ver01");
        newslettertemplate.setTemplateText("template_text_ver01");
        newslettertemplate.setTemplateTextPreprocessed("template_text_preprocessed_ver01");
        newslettertemplate.setTemplateStyles("template_styles_ver01");
        newslettertemplate.setTemplateType(1);
        newslettertemplate.setTemplateSubject("template_subject_ver01");
        newslettertemplate.setTemplateSenderName("template_sender_name_ver01");
        newslettertemplate.setTemplateSenderEmail("template_sender_email_ver01");
        newslettertemplate.setTemplateActual(true);
        newslettertemplate.setCreatedDate("2016-03-16");
        newslettertemplate.setLastModifiedDate("2016-03-16");
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.NEWSLETTER_TEMPLATE, newslettertemplate);
        com.wahsis.utility_service.NewsletterTemplateHandler.getInstance().addNewsletterTemplate(dt, vrfContent);
    }

    public void updateNewsletterTemplate() {
        com.wahsis.utility_service.NewsletterTemplate newslettertemplate = new com.wahsis.utility_service.NewsletterTemplate();
        StringBuilder vrfContent = new StringBuilder();
        newslettertemplate.setTemplateCode("template_code_ver01");
        newslettertemplate.setTemplateText("template_text_ver01");
        newslettertemplate.setTemplateTextPreprocessed("template_text_preprocessed_ver01");
        newslettertemplate.setTemplateStyles("template_styles_ver01");
        newslettertemplate.setTemplateType(1);
        newslettertemplate.setTemplateSubject("template_subject_ver01");
        newslettertemplate.setTemplateSenderName("template_sender_name_ver01");
        newslettertemplate.setTemplateSenderEmail("template_sender_email_ver01");
        newslettertemplate.setTemplateActual(true);
        newslettertemplate.setCreatedDate("2016-03-16");
        newslettertemplate.setLastModifiedDate("2016-03-16");
        String dt = com.wahsis.common.CommonService.FormatResponse(DefinedName.NEWSLETTER_TEMPLATE, newslettertemplate);
        com.wahsis.utility_service.NewsletterTemplateHandler.getInstance().updateNewsletterTemplate(dt, vrfContent);
    }
}
