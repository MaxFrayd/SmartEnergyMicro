    package com.reznikov.smartenergycustomer.dto;

    import jakarta.validation.Valid;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.PositiveOrZero;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import javax.persistence.Column;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public class SupplierRegDto {
        @NotBlank(message = "Name is mandatory")
        private String name;

        @Email(message = "Email should be valid")
        private String email;

        @Valid
        private AddressDto address;

        @PositiveOrZero(message = "Amount of energy should be zero or positive")
        @NotNull
        private double energyAmount;
        @NotNull
        private Double pricePrKwt;

    }