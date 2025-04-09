package com.simply.simple_life.DTO.Transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;


@Validated
@Data
public class TransactionRequest {

    private int id;

    @NotNull
    private String name;

    private float amount;
    private String description;
    private Date date;

    @NotNull
    @JsonProperty("user_id")
    private int userId;

    @NotNull
    @JsonProperty("category_id")
    private int categoryId;
}
