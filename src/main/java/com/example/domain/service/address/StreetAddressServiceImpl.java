package com.example.domain.service.address;

import com.example.domain.model.StreetAddress;
import com.example.domain.repository.address.StreetAddressRepository;
import com.example.domain.repository.address.StreetAddressSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
public class StreetAddressServiceImpl implements StreetAddressService {

    @Inject
    StreetAddressRepository streetAddressRepository;

    public Page<StreetAddress> search(StreetAddressSearchCriteria criteria, Pageable pageable) {
        long total = streetAddressRepository.countByCriteria(criteria);
        List<StreetAddress> addresses;
        if (0 < total) {
            addresses = streetAddressRepository.findPageByCriteria(criteria, pageable);
        } else {
            addresses = Collections.emptyList();
        }
        return new PageImpl<>(addresses, pageable, total);
    }

}