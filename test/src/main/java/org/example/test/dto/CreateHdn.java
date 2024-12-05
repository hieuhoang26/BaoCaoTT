package org.example.test.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateHdn {
    private String supplier;

    private Double totalAmount;

    private String note;

    List<CreateHdnDetail> hdnDetail;
}
