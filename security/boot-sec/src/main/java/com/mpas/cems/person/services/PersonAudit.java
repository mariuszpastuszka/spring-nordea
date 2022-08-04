
package com.mpas.cems.person.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.*;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PersonAudit {
    private static Logger logger = LoggerFactory.getLogger(PersonAudit.class);

    private Map<LocalDateTime, String> auditMessages;

    public PersonAudit() {
        logger.debug(" ->> Creating  the PersonAudit for this session ...");
        auditMessages = new HashMap<>();
    }

    public void recordAction(String action) {
        auditMessages.put(LocalDateTime.now(),action);
    }

    public Map<LocalDateTime, String> getAuditMessages() {
        return auditMessages;
    }
}
