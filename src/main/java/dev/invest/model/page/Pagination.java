package dev.invest.model.page;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

@Schema(
        description = "Параметры пагинации",
        example = "{\"page\": 0, \"size\": 100}"
)
public record Pagination(
        @Min(value = 0, message = "Page must be greater than or equal to 0")
        Integer page,
        @Min(value = 1, message = "Size must be greater than or equal to 1")
        Integer size
) {
}
