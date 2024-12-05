package org.example.test.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderItem {
    private Integer orderItemId;

    private Integer productId;

    private Short quantity;

}
