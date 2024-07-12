    package com.reznikov.smartenergy.dto;

    import jakarta.validation.Valid;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.PositiveOrZero;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class SupplierFormDto {
        @NotBlank(message = "Name is mandatory")
        private String name;

        @Email(message = "Email should be valid")
        private String email;

        @Valid
        private AddressDto address;

        @PositiveOrZero(message = "Amount of energy should be zero or positive")
        private double energyAmount;
    }
