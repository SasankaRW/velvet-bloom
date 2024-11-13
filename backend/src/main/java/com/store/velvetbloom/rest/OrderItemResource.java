package com.store.velvetbloom.rest;

import com.store.velvetbloom.model.OrderItemDTO;
import com.store.velvetbloom.service.OrderItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/orderItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderItemResource {

    private final OrderItemService orderItemService;

    public OrderItemResource(final OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.findAll());
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDTO> getOrderItem(
            @PathVariable(name = "orderItemId") final UUID orderItemId) {
        return ResponseEntity.ok(orderItemService.get(orderItemId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UUID> createOrderItem(
            @RequestBody @Valid final OrderItemDTO orderItemDTO) {
        final UUID createdOrderItemId = orderItemService.create(orderItemDTO);
        return new ResponseEntity<>(createdOrderItemId, HttpStatus.CREATED);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<UUID> updateOrderItem(
            @PathVariable(name = "orderItemId") final UUID orderItemId,
            @RequestBody @Valid final OrderItemDTO orderItemDTO) {
        orderItemService.update(orderItemId, orderItemDTO);
        return ResponseEntity.ok(orderItemId);
    }

    @DeleteMapping("/{orderItemId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrderItem(
            @PathVariable(name = "orderItemId") final UUID orderItemId) {
        orderItemService.delete(orderItemId);
        return ResponseEntity.noContent().build();
    }

}