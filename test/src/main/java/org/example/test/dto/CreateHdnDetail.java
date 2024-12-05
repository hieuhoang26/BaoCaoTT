package org.example.test.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHdnDetail {

    private Integer productId;

    private Short quantity;

    private Double unitPrice;

    private Double totalPrice;

}
