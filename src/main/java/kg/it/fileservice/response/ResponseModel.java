package kg.it.fileservice.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseModel<T> implements Serializable {
    T result;
    ResultCode resultCode;
    String message;

    public ResponseModel(T result, ResultCode resultCode) {
        this.result = result;
        this.resultCode = resultCode;
    }
}
