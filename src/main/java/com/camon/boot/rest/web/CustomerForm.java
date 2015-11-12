package com.camon.boot.rest.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by jooyong on 2015-11-12.
 */
@Data
public class CustomerForm {
    @NotNull
    @Size(min = 1, max = 127)
    private String firstName;

    @NotNull
    @Size(min =1, max = 127)
    private String lastName;
}
