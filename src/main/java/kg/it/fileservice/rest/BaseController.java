package kg.it.fileservice.rest;

import kg.it.fileservice.response.ResponseModel;
import kg.it.fileservice.response.ResultCode;

public abstract class BaseController {

    protected <R>ResponseModel<R> successResponse(R result) {
        return new ResponseModel<>(result, ResultCode.SUCCESS);
    }

    protected ResponseModel<String> exceptionResponse(String details) {
        return new ResponseModel<>(null, ResultCode.EXCEPTION, details);
    }

}
