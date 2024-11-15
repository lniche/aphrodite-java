package top.threshold.aphrodite.pkg.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseOprDO extends BaseDO {

    private String createdBy;

    private String updatedBy;
}
