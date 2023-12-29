package cc.dto;

import java.util.ArrayList;
import java.util.List;

public class TableInfo {
    String className;
    String tableName;
    List<ColumnDto> columnDtoList = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnDto> getColumnDtoList() {
        return columnDtoList;
    }

    public void setColumnDtoList(List<ColumnDto> columnDtoList) {
        this.columnDtoList = columnDtoList;
    }
}
