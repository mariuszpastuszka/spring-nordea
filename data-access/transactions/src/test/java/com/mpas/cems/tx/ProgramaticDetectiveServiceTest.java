
package com.mpas.cems.tx;

import com.mpas.cems.aop.service.DetectiveService;
import com.mpas.cems.tx.config.AppConfig;
import com.mpas.cems.tx.config.TestTransactionalDbConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestTransactionalDbConfig.class, AppConfig.class})
public class ProgramaticDetectiveServiceTest {

    @Autowired
    @Qualifier("programaticDetectiveService")
    DetectiveService detectiveService;

    @Test
    void testFindById(){
        detectiveService.findById(1L).ifPresentOrElse(
                d -> assertEquals("LD112233", d.getBadgeNumber()),
                Assertions:: fail
        );
    }
}
