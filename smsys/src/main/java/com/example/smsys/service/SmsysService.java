package com.example.smsys.service;

import com.example.smsys.model.Smsys;

import java.io.IOException;
import java.util.Collection;

/**
 * @author srehcs
 */
public interface SmsysService {
    Smsys create(Smsys student);
    Smsys ping(String email) throws IOException;
    Collection<Smsys> list(int limit);
    Smsys get(Long id);
    Smsys update(Smsys student);
    Boolean delete(Long id);


}
