package org.example.test.controller;

import lombok.RequiredArgsConstructor;
import org.example.test.dto.CategoryDto;
import org.example.test.dto.CreateHdn;
import org.example.test.service.HdnService;
import org.example.test.util.Uri;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HdnController {
    final HdnService hdnService;

    @PostMapping(value = Uri.hdn)
    public ResponseEntity CreatHdn(@RequestBody CreateHdn rq) {
        hdnService.creatHdn(rq);
        return new ResponseEntity(HttpStatusCode.valueOf(HttpStatus.OK.value()));
    }


}
