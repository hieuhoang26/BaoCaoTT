package org.example.test.dto;

import lombok.*;
import org.example.test.util.OrderStatus;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrder {

    private OrderStatus status;

    private Double subTotal;

    private Double shipping;

    private Double total;

    private Double discount;

    private Double grandTotal;

    private String mobile;

    private String address;

    private String content;

    List<UpdateOrderItem> items;
}
