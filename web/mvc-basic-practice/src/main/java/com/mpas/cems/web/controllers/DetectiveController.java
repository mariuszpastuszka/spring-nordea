
package com.mpas.cems.web.controllers;

import com.mpas.cems.dj.services.DetectiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO 48. complete the configuration and implementation of this class so that the Detectives part of the application works too.
public class DetectiveController {

    private Logger logger = LoggerFactory.getLogger(DetectiveController.class);

    private DetectiveService detectiveService;

    public DetectiveController(DetectiveService detectiveService) {
        this.detectiveService = detectiveService;
    }

}
