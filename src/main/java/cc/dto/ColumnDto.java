package cc.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ColumnDto {
    String columnName;
    String dbColumnName;
    String dataType;
    String comment;
}
