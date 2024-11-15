package top.threshold.aphrodite.pkg.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BaseDO {

    @Id(keyType = KeyType.Auto)
    private Long id;

    @Column(version = true)
    private Long version;

    @Column(onInsertValue = "now()")
    private OffsetDateTime createdAt;

    @Column(onUpdateValue = "now()")
    private OffsetDateTime updatedAt;

    private OffsetDateTime deletedAt;
}
