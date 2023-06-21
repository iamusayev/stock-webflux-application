package az.iamusayev.model;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
public class ExceptionDto {

    String code;
    String message;

    public ExceptionDto(String message) {
        this.message = message;
    }
}