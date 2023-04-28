package com.tutorial.userservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = -6742104428183327682L;

    private String accountId;
    private String currencyCode;
    private String branchCode;
}
