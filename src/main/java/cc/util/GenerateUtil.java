package cc.util;

import cc.config.PgConfig;
import cc.dto.ColumnDto;
import cc.dto.TableInfo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class GenerateUtil {
    private final PgConfig pgConfig;

    public static void main(String[] args) {
        System.out.println(getAbsolutePath("controller"));
        System.out.println(getAbsolutePath("util"));
        System.out.println(getAbsolutePath("dto"));
    }

    public static String getAbsolutePath(String module) {
        String currentDir = System.getProperty("user.dir");
        return printDirectoryContents(null, new File(currentDir), module);
    }

    public static String printDirectoryContents(String res, File dir, String module) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (file.getName().equals(module)) {
                    res = file.getAbsolutePath();
                    return res;
                }
                res = printDirectoryContents(res, file, module);
            }
        }
        return res;
    }

    public void generateEntity(String module, String tableName, String pre) throws IOException {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                if (!t.getTableName().equals(tableName)) {
                    continue;
                }
                markerBean(getAbsolutePath(module), t.getClassName(), getEntityInfo(t));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void generateMapper(String module, String tableName, String pre) throws IOException {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                if (!t.getTableName().equals(tableName)) {
                    continue;
                }
                markerBean(getAbsolutePath(module), t.getClassName() + "Mapper", getMapper(t));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void generateService(String module, String tableName, String pre) throws IOException {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                if (!t.getTableName().equals(tableName)) {
                    continue;
                }
                markerBean(getAbsolutePath(module), "I" + t.getClassName() + "Service", getService(t));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void generateServiceImpl(String module, String tableName, String pre) throws IOException {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                if (!t.getTableName().equals(tableName)) {
                    continue;
                }
                markerBean(getAbsolutePath(module), t.getClassName() + "ServiceImpl", getServiceImpl(t));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void generateXml(String module, String tableName, String pre) throws IOException {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                if (!t.getTableName().equals(tableName)) {
                    continue;
                }
                markerXml(getAbsolutePath(module), t.getClassName() + "Mapper", getXml(t));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void generateMapperAndService(String tableName, String pre) throws IOException {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                if (!t.getTableName().equals(tableName)) {
                    continue;
                }
                //生成Mapper类
                markerBean(getAbsolutePath("mapper"), t.getClassName() + "Mapper", getMapper(t));
                //生成Service类
                markerBean(getAbsolutePath("service"), "I" + t.getClassName() + "Service", getService(t));
                //生成ServiceImpl类
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //    "/Users/wangxiuxiu/ideaProject/wang-xiuxius-private-warehouse"
    public void generateAll(String pre) {
        List<TableInfo> tableInformationList = getTableInformation(pgConfig, pre);
        for (TableInfo t : tableInformationList) {
            try {
                //生成实体类
                markerBean(getAbsolutePath("entity"), t.getClassName(), getEntityInfo(t));
                //生成Mapper类
                markerBean(getAbsolutePath("mapper"), t.getClassName() + "Mapper", getMapper(t));
                //生成Service类
                markerBean(getAbsolutePath("service"), "I" + t.getClassName() + "Service", getService(t));
                //生成ServiceImpl类
                markerBean(getAbsolutePath("Impl"), t.getClassName() + "ServiceImpl", getServiceImpl(t));
                //生成Controller类
                markerBean(getAbsolutePath("controller"), t.getClassName() + "Controller", getController(t));
                //生成xml
                markerXml(getAbsolutePath("xml"), t.getClassName() + "Mapper", getXml(t));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getController(TableInfo info) {
        StringBuffer sb = new StringBuffer();
        sb.append("package cc.controller;\r\n");
        sb.append("\r\n");
        sb.append("import cc.entity." + info.getClassName() + ";\r\n");
        sb.append("import cc.service." + "I" + info.getClassName() + "Service;\r\n");
        sb.append("import cc.util.ReturnJson;\r\n");
        sb.append("import cn.hutool.core.bean.BeanUtil;\r\n");
        sb.append("import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;\r\n");
        sb.append("import io.swagger.annotations.Api;\r\n");
        sb.append("import io.swagger.annotations.ApiOperation;\r\n");
        sb.append("import lombok.AllArgsConstructor;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("import cc.util.PageUtil;\r\n");
        sb.append("import lombok.extern.slf4j.Slf4j;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("\r\n");
        sb.append("@Api(tags = \"" + info.getTableName() + "\")\r\n");
        sb.append("@RestController\r\n");
        sb.append("@Slf4j\r\n");
        sb.append("@AllArgsConstructor(onConstructor_ = @Autowired)\r\n");
        sb.append("public class " + info.getClassName() + "Controller {\r\n");
        sb.append("    private final I" + info.getClassName() + "Service " + toCamelCase(info.getTableName().replace("t_", ""), false) + "Service;\r\n");
        sb.append("\r\n");
        sb.append("    @ApiOperation(\"增加与修改\")\r\n");
        sb.append("    @PostMapping(path = \"/api/" + toCamelCase(info.getTableName().replace("t_", ""), false) + "/add\")\r\n");
        sb.append("    public ReturnJson add(@RequestBody " + info.getClassName() + " " + toCamelCase(info.getTableName().replace("t_", ""), false) + ") {\r\n");
        sb.append("        " + info.getClassName() + " one = " + toCamelCase(info.getTableName().replace("t_", ""), false) + "Service.getOne(new QueryWrapper<" + info.getClassName() + ">().eq(\"id\", " + toCamelCase(info.getTableName().replace("t_", ""), false) + ".getId()));\r\n");
        sb.append("        if (one == null) {\r\n");
        sb.append("            " + toCamelCase(info.getTableName().replace("t_", ""), false) + "Service.save(" + toCamelCase(info.getTableName().replace("t_", ""), false) + ");\r\n");
        sb.append("            return ReturnJson.success();\r\n");
        sb.append("        } else {\r\n");
        sb.append("            BeanUtil.copyProperties(" + toCamelCase(info.getTableName().replace("t_", ""), false) + ", one, \"id\");\r\n");
        sb.append("            " + toCamelCase(info.getTableName().replace("t_", ""), false) + "Service.updateById(one);\r\n");
        sb.append("            return ReturnJson.success();\r\n");
        sb.append("        }\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @ApiOperation(\"删除\")\r\n");
        sb.append("    @DeleteMapping(path = \"/api/" + toCamelCase(info.getTableName().replace("t_", ""), false) + "{id}/delete\")\r\n");
        sb.append("    public ReturnJson delete(@PathVariable(\"id\") Long id) {\r\n");
        sb.append("        " + toCamelCase(info.getTableName().replace("t_", ""), false) + "Service.removeById(id);\r\n");
        sb.append("        return ReturnJson.success();\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        sb.append("    @ApiOperation(\"查询\")\r\n");
        sb.append("    @GetMapping(path = \"/api/" + toCamelCase(info.getTableName().replace("t_", ""), false) + "/list\")\r\n");
        sb.append("    public ReturnJson list(@RequestParam(defaultValue = \"1\") Integer current,\r\n");
        sb.append("                           @RequestParam(defaultValue = \"10\") Integer size) {\r\n");
        sb.append("        return ReturnJson.success(PageUtil.page(" + toCamelCase(info.getTableName().replace("t_", ""), false) + "Service.list(),current, size));\r\n");
        sb.append("    }\r\n");
        sb.append("}\r\n");
        sb.append("\r\n");
        return sb.toString();
    }


    private String getXml(TableInfo info) {
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n");
        sb.append("<mapper namespace=\"cc.mapper." + info.getClassName() + "Mapper\">\r\n");
        sb.append("\r\n");
        sb.append("    <resultMap type=\"cc.entity." + info.getClassName() + "\" id=\"" + info.getClassName() + "Map\">\r\n");
        for (ColumnDto c : info.getColumnDtoList()) {
            sb.append("        <result property=\"" + c.getColumnName() + "\" column=\"" + c.getDbColumnName() + "\" jdbcType=\"" + getJdbcType(c.getDataType()) + "\"/>\r\n");
        }
        sb.append("    </resultMap>\r\n");
        sb.append("    <sql id=\"Base_Column_List_New\">\r\n");
        for (ColumnDto c : info.getColumnDtoList()) {
            sb.append("    " + info.getClassName().toLowerCase().substring(0, 1) + "." + c.getDbColumnName() + ",\r\n");
        }
        sb.append("    </sql>\r\n");
        sb.append("</mapper>");
        return sb.toString();
    }

    private String getJdbcType(String dataType) {
        if (dataType.equals("bigserial") || dataType.equals("int8")) {
            return "BIGINT";
        } else if (dataType.equals("varchar") || dataType.equals("text")) {
            return "VARCHAR";
        } else if (dataType.equals("int4")) {
            return "INTEGER";
        } else if (dataType.equals("timestamp")) {
            return "TIMESTAMP";
        } else if (dataType.equals("numeric")) {
            return "NUMERIC";
        }
        return null;
    }

    private String getServiceImpl(TableInfo info) {
        StringBuffer sb = new StringBuffer();
        sb.append("package cc.service.impl;\r\n");
        sb.append("\r\n");
        sb.append("import cc.entity." + info.getClassName() + ";\r\n");
        sb.append("import cc.mapper." + info.getClassName() + "Mapper;\r\n");
        sb.append("import cc.service.I" + info.getClassName() + "Service;\r\n");
        sb.append("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\r\n");
        sb.append("import lombok.extern.slf4j.Slf4j;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("\r\n");
        sb.append("@Service\r\n");
        sb.append("@Slf4j\r\n");
        sb.append("public class " + info.getClassName() + "ServiceImpl extends ServiceImpl<" + info.getClassName() + "Mapper, " + info.getClassName() + "> implements I" + info.getClassName() + "Service {\r\n");
        sb.append("}");
        return sb.toString();
    }

    private String getService(TableInfo info) {
        StringBuffer sb = new StringBuffer();
        sb.append("package cc.service;\r\n");
        sb.append("\r\n");
        sb.append("import cc.entity." + info.getClassName() + ";\r\n");
        sb.append("import com.baomidou.mybatisplus.extension.service.IService;\r\n");
        sb.append("\r\n");
        sb.append("public interface I" + info.getClassName() + "Service extends IService<" + info.getClassName() + "> {\r\n");
        sb.append("}");
        return sb.toString();
    }

    private String getMapper(TableInfo info) {
        StringBuffer sb = new StringBuffer();
        sb.append("package cc.mapper;\r\n");
        sb.append("\r\n");
        sb.append("import cc.entity." + info.getClassName() + ";\r\n");
        sb.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\r\n");
        sb.append("\r\n");
        sb.append("public interface " + info.getClassName() + "Mapper extends BaseMapper<" + info.getClassName() + "> {\r\n");
        sb.append("}");
        return sb.toString();
    }

    private String getEntityInfo(TableInfo info) {
        StringBuffer sb = new StringBuffer();
        sb.append("package cc.entity;\r\n");
        sb.append("\r\n");
        sb.append("import com.baomidou.mybatisplus.annotation.IdType;\r\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
        sb.append("import com.baomidou.mybatisplus.annotation.TableId;\r\n");
        sb.append("import com.baomidou.mybatisplus.annotation.TableName;\r\n");
        sb.append("import io.swagger.annotations.ApiModelProperty;\r\n");
        sb.append("import lombok.Data;\r\n");
        sb.append("import lombok.EqualsAndHashCode;\r\n");
        sb.append("import lombok.experimental.Accessors;\r\n");
        sb.append("import java.util.Date;\r\n");
        sb.append("\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("\r\n");
        sb.append("@Data\r\n");
        sb.append("@EqualsAndHashCode\r\n");
        sb.append("@Accessors(chain = true)\r\n");
        sb.append("@TableName(value = \"" + info.getTableName() + "\", autoResultMap = true)\r\n");
        sb.append("public class " + info.getClassName() + " implements Serializable" + "{\r\n");
        sb.append("    private static final long serialVersionUID = 1L;\r\n");
        processAllColumn(sb, info.getColumnDtoList());
        sb.append("}\r\n");
        return sb.toString();
    }

    private void processAllColumn(StringBuffer sb, List<ColumnDto> columnDtoList) {
        columnDtoList.forEach(c -> {
            sb.append("\r\n");
            if (c.getComment() != null) {
                sb.append("    @ApiModelProperty(value=\"" + c.getComment() + "\")\r\n");
            }
            if (c.getColumnName().equals("id")) {
                sb.append("    @TableId(value = \"id\", type = IdType.AUTO)\r\n");
            }
            if (c.getDataType().equals("timestamp")) {
                sb.append("    @JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")\r\n");
            }
            sb.append("    private " + getColumnType(c.getDataType()) + " " + c.getColumnName() + ";");
            sb.append("\r\n");
        });
    }

    private String getColumnType(String dataType) {
        if (dataType.equals("bigserial") || dataType.equals("int8")) {
            return "Long";
        } else if (dataType.equals("varchar") || dataType.equals("text")) {
            return "String";
        } else if (dataType.equals("int4")) {
            return "Integer";
        } else if (dataType.equals("int2")) {
            return "Short";
        } else if (dataType.equals("timestamp")) {
            return "Date";
        } else if (dataType.equals("numeric")) {
            return "BigDecimal";
        }
        return null;
    }

    private List<TableInfo> getTableInformation(PgConfig pgConfig, String pre) {
        Connection conn = null;
        try {
            List<TableInfo> tableInfoList = new ArrayList<>();
            Class.forName(pgConfig.getDriverClassName());

            String url = pgConfig.getUrl();
            String user = pgConfig.getUsername();
            String password = pgConfig.getPassword();
            conn = DriverManager.getConnection(url, user, password);

            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rsTables = meta.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Tables:");
            while (rsTables.next()) {
                String tableName = rsTables.getString("TABLE_NAME");
                System.out.println("\t" + tableName);
                TableInfo info = new TableInfo();
                info.setTableName(tableName);
                info.setClassName(toCamelCase(tableName.replace(pre, ""), true));
                // Get a list of all columns in the table
                ResultSet rsColumns = meta.getColumns(null, null, tableName, null);
                System.out.println("\t\tColumns:");
                while (rsColumns.next()) {
                    ColumnDto columnDto = new ColumnDto();
                    columnDto.setColumnName(toCamelCase(rsColumns.getString("COLUMN_NAME"), false));
                    columnDto.setDbColumnName(rsColumns.getString("COLUMN_NAME"));
                    columnDto.setDataType(rsColumns.getString("TYPE_NAME"));
                    columnDto.setComment(rsColumns.getString("REMARKS"));
                    info.getColumnDtoList().add(columnDto);
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    String columnType = rsColumns.getString("TYPE_NAME");
                    String columnComment = rsColumns.getString("REMARKS");
                    System.out.println("\t\t\t" + columnName + " (" + columnType + ")");
                    System.out.println("\t\t\tComment: " + columnComment);
                }
                rsColumns.close();
                tableInfoList.add(info);
            }
            rsTables.close();
            // Close the database connection
            conn.close();
            return tableInfoList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下划线变驼峰
     *
     * @param name
     * @param isFirstUpper
     * @return
     */
    public String toCamelCase(String name, boolean isFirstUpper) {
        if (name == null) {
            return null;
        }
//        name = name.toLowerCase();
        StringBuilder sb = new StringBuilder(name.length());
        boolean upperCase = isFirstUpper;
        for (int i = 0; i < name.length(); i++) {
            char k = name.charAt(i);

            if ('_' == k) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(k));
                upperCase = false;
            } else {
                sb.append(k);
            }
        }
        return sb.toString();
    }

    /**
     * 创建实体类文件
     *
     * @param className 类名
     * @param content   添加的内容(字段注释等)
     */
    public static void markerBean(String path, String className, String content) throws IOException {
        FileWriter fw = new FileWriter(path + "/" + className + ".java");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
    }

    public static void markerXml(String path, String name, String content) throws IOException {
        FileWriter fw = new FileWriter(path + "/" + name + ".xml");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
    }
}
