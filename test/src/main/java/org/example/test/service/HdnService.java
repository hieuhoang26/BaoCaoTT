package org.example.test.service;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CreateHdn;
import org.example.test.dto.CreateOrder;
import org.example.test.model.Hdn;
import org.example.test.model.HdnDetail;
import org.example.test.model.Product;
import org.example.test.repository.HdnDetailRepository;
import org.example.test.repository.HdnRepository;
import org.example.test.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HdnService {
    final HdnRepository hdnRepository;
    final ProductRepository productRepository;
    final HdnDetailRepository hdnDetailRepository;

    public void creatHdn(CreateHdn request) {
        Hdn hdn = new Hdn();
        hdn.setSupplier(request.getSupplier());
        hdn.setTotalAmount(request.getTotalAmount());
        hdn.setNote(request.getNote());
        hdn.setCreatedAt(LocalDate.now());

        hdnRepository.save(hdn);
        List<HdnDetail> hdnDetails = request.getHdnDetail().stream().map(detail -> {
            // Fetch the product from the database using the productId from the request
            Product product = productRepository.findById(detail.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            HdnDetail hdnDetail = new HdnDetail();
            hdnDetail.setQuantity(detail.getQuantity());
            hdnDetail.setUnitPrice(detail.getUnitPrice());
            hdnDetail.setTotalPrice(detail.getTotalPrice());
            hdnDetail.setHdnByPurchaseInvoiceId(hdn);
            hdnDetail.setProductByProductId(product);
            product.setInventory((short) (product.getInventory()+hdnDetail.getQuantity()));
            productRepository.save(product);
            return hdnDetail;
        }).toList();
        hdnDetailRepository.saveAll(hdnDetails);
//        List<Integer> missingProductIds = new ArrayList<>();
//        List<HdnDetail> hdnDetails = request.getHdnDetail().stream()
//                .map(detail -> {
//                    // Fetch the product from the database using the productId from the request
//                    return productRepository.findById(detail.getProductId())
//                            .map(product -> {
//                                // If product exists, create HdnDetail object
//                                HdnDetail hdnDetail = new HdnDetail();
//                                hdnDetail.setQuantity(detail.getQuantity());
//                                hdnDetail.setUnitPrice(detail.getUnitPrice());
//                                hdnDetail.setTotalPrice(detail.getTotalPrice());
//                                hdnDetail.setHdnByPurchaseInvoiceId(hdn);
//                                hdnDetail.setProductByProductId(product);
//                                return hdnDetail;
//                            })
//                            .orElseGet(() -> {
//                                // If product doesn't exist, log or just return null to skip
//                                System.out.println("Product with ID " + detail.getProductId() + " not found. Skipping.");
//                                missingProductIds.add(detail.getProductId());
//                                return null; // Skipping the detail if product not found
//                            });
//                })
//                .filter(Objects::nonNull) // Remove any null values (products not found)
//                .collect(Collectors.toList());


//        return null; // trả về sản phẩm không tồn tại
    }
}
