package com.example.demo.controller;

import com.example.demo.dto.TicketPurchaseRequest;
import com.example.demo.dto.TicketStatusResponse;
import com.example.demo.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Entradas", description = "Operaciones relacionadas con la compra y consulta de entradas para eventos en línea")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Permite realizar la compra de una entrada para un evento.
     */
    @Operation(
            summary = "Comprar una entrada",
            description = "Permite a un usuario comprar una entrada para un evento en línea enviando su correo y el nombre del evento.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Datos necesarios para realizar la compra de la entrada.",
                    content = @Content(
                            schema = @Schema(implementation = TicketPurchaseRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de compra",
                                    value = "{\n  \"userEmail\": \"usuario@correo.com\",\n  \"eventName\": \"Concierto Online de Rock\"\n}"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Compra realizada con éxito",
                            content = @Content(schema = @Schema(implementation = TicketStatusResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud inválida o datos incompletos",
                            content = @Content
                    )
            }
    )
    @PostMapping("/purchase")
    public TicketStatusResponse purchaseTicket(@RequestBody TicketPurchaseRequest request) {
        return ticketService.purchaseTicket(request);
    }

    /**
     * Permite consultar el estado actual de una entrada previamente comprada.
     */
    @Operation(
            summary = "Consultar estado de una entrada",
            description = "Permite consultar si una entrada se encuentra confirmada, pendiente o cancelada, usando su ID.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Consulta realizada correctamente",
                            content = @Content(schema = @Schema(implementation = TicketStatusResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontró la entrada con el ID proporcionado",
                            content = @Content
                    )
            }
    )
    @GetMapping("/{id}")
    public TicketStatusResponse getTicketStatus(@PathVariable Long id) {
        return ticketService.getTicketStatus(id);
    }
}
