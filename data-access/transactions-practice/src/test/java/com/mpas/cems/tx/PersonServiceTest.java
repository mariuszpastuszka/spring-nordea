
package com.mpas.cems.tx;

import com.mpas.cems.aop.service.PersonService;
import com.mpas.cems.tx.config.AppConfig;
import com.mpas.cems.tx.config.TestTransactionalDbConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
class PersonServiceTest {

    @Autowired
    PersonService personService;


    //@Test
    void testUpdatePassword() {
        // TODO 34. Complete definition of this test in order for it to pass
    }
}
