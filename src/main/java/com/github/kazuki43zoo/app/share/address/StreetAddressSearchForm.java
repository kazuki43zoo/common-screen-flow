package com.github.kazuki43zoo.app.share.address;

import java.io.Serializable;

@lombok.Data
public class StreetAddressSearchForm implements Serializable {
    private static final long serialVersionUID = 1L;
    private String zipCode;
    private String address;
}