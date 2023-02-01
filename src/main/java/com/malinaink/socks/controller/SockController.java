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
    @Operation(summary = "Приход носков", description = "Регистрирует приход товара на склад, добавляя определенный тип товара согласно параметрам")
    public ResponseEntity<String> addSocks(@RequestBody SockRequest sockRequest) {
        try {
            sockService.addSock(sockRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось добавить товар для учета, попробуйте снова");
        }
        return ResponseEntity.ok().body("Приход товара на склад успешно зарегистрирован");
    }


    @PutMapping
    @Operation(summary = "Отпуск носков", description = "Регистрирует отпуск носков со склада, списывая определенный тип товара согласно параметрам")
    public ResponseEntity<String> issueSocks(@RequestBody SockRequest sockRequest) {
        try {
            sockService.issueSock(sockRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось зарегистрировать отпуск носков, попробуйте снова");
        }
        return ResponseEntity.ok().body("Отпуск товара со склада успешно зарегистрирован");
    }

    @GetMapping
    @Operation(summary = "Доступное количество носков", description = "Возвращает количество товара на складе, доступное для выдачи, согласно введенным параметрам")
    public int getSockCount(@RequestParam(required = false, name = "color") Color color,
                            @RequestParam(required = false, name = "size") Size size,
                            @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                            @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    @Operation(summary = "Списание бракованных носков", description = "Списывает определенный тип бракованного товара согласно введенным параметрам")
    public ResponseEntity<String> removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
        try {
            sockService.removeDefectiveSocks(sockRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Не удалось списать данную партию товара, попробуйте снова");
        }
        return ResponseEntity.ok().body("Партия товара с указанными параметрами успешно списана");
    }
}