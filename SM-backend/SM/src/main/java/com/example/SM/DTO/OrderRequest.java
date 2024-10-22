package com.example.SM.DTO;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private Long customerId;
    private long quantity;
}
