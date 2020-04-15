package org.computerShop.service;

import org.computerShop.model.RemindMe;
import org.springframework.http.ResponseEntity;

public interface RemindMeService {

    ResponseEntity<String> createRemindMe(RemindMe remindMe);

}
