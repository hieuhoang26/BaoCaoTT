package org.example.test.dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.example.test.util.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrder {

    private OrderStatus status;

    private Double subTotal;

    private Double shipping;

    private Double total;

    private Double discount;

    private Double grandTotal;

    private String mobile;

    private String address;

    private String content;

    List<CreateOrderItem> items;
}
