package seoham.seohamspring.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

import static seoham.seohamspring.config.BaseResponseStatus.SUCCESS;


@Getter
@Setter
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final int code;
    private final  String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public BaseResponse(T result){
=======
import lombok.AllArgsConstructor;
import lombok.Getter;

import static seoham.seohamspring.config.BaseResponseStatus.SUCCESS;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class BaseResponse<T> {
    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String message;
    private final int code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // 요청에 성공한 경우
    public BaseResponse(T result) {
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e
        this.isSuccess = SUCCESS.isSuccess();
        this.message = SUCCESS.getMessage();
        this.code = SUCCESS.getCode();
        this.result = result;
    }

<<<<<<< HEAD
=======
    // 요청에 실패한 경우
>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e
    public BaseResponse(BaseResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.message = status.getMessage();
        this.code = status.getCode();
    }
}
<<<<<<< HEAD
=======

>>>>>>> 5a12ce1c2bd7eef06011995f838c5ba941d0d21e
