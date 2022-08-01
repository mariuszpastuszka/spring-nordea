
package com.mpas.cems.tx;

import com.mpas.cems.aop.service.DetectiveService;
import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.dao.Person;
import com.mpas.cems.tx.config.AppConfig;
import com.mpas.cems.tx.config.TestTransactionalDbConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
class DetectiveServiceTest {

    private Logger logger = LoggerFactory.getLogger(DetectiveServiceTest.class);

    @Autowired
    @Qualifier("detectiveServiceImpl")
    DetectiveService detectiveService;

    @Autowired
    PersonService personService;

    @Test
    void testFindById(){
        detectiveService.findById(1L).ifPresentOrElse(
                d -> assertEquals("LD112233", d.getBadgeNumber()),
                Assertions:: fail
        );
    }

    @Test
    void testDetectiveHtml(){
        detectiveService.findById(1L).ifPresent(
                d -> {
                    Person p = d.getPerson();
                    assertNotNull(p);
                    String html = personService.getPersonAsHtml(p.getUsername());
                    logger.info("Detective personal info: {}", html);
                }
        );
    }

}
