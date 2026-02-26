package com.documind.common.core.result;

import java.io.Serializable;

public interface IResultCode extends Serializable {
    Integer getCode();
    String getMessage();
}
