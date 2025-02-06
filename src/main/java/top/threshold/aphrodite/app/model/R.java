package top.threshold.aphrodite.app.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class R implements Serializable {

    private String code;
    private String message;
    private Object data;

}
