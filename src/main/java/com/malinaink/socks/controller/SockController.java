package com.malinaink.socks.controller;

import com.malinaink.socks.dto.SockRequest;
import com.malinaink.socks.model.Color;
import com.malinaink.socks.model.Size;
import com.malinaink.socks.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
@Tag(name = "Носки", description = "CRUD-операции и другие эндпойнты для работы с носками")

public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }
    @PostMapping
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Не удалось добавить товар для учета, попробуйте снова")
    @Operation(summary = "Приход носков", description = "Регистрирует приход товара на склад, добавляя определенный тип товара согласно параметрам")
    public ResponseEntity<String> addSocks(@RequestBody SockRequest sockRequest) {
            sockService.addSock(sockRequest);
            return ResponseEntity.ok().body("Приход товара на склад успешно зарегистрирован");
    }


    @PutMapping
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Не удалось зарегистрировать отпуск носков, попробуйте снова")
    @Operation(summary = "Отпуск носков", description = "Регистрирует отпуск носков со склада, списывая определенный тип товара согласно параметрам")
    public ResponseEntity<String> issueSocks(@RequestBody SockRequest sockRequest) {
            sockService.issueSock(sockRequest);
            return ResponseEntity.ok().body("Отпуск товара со склада успешно зарегистрирован");
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @Operation(summary = "Доступное количество носков", description = "Возвращает количество товара на складе, доступное для выдачи, согласно введенным параметрам")
    public int getSockCount(@RequestParam(required = false, name = "color") Color color,
                            @RequestParam(required = false, name = "size") Size size,
                            @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                            @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Не удалось списать данную партию товара, попробуйте снова")
    @Operation(summary = "Списание бракованных носков", description = "Списывает определенный тип бракованного товара согласно введенным параметрам")
    public ResponseEntity<String> removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
            sockService.removeDefectiveSocks(sockRequest);
            return ResponseEntity.ok().body("Партия товара с указанными параметрами успешно списана");
    }
}


