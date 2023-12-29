package cc.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengjun
 * @ClassName: ResponseCode
 * @Description: 返回状态码常量类
 * @date 2019年1月10日
 */
public class ResponseCode implements Serializable {

    private static final long serialVersionUID = -2790656454339569762L;

    public static final String OK = "OK";

    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MESG = "请求处理成功";

    public static final String SYSTEM_FAIL_CODE = "500";
    public static final String SYSTEM_FAIL_MESG = "系统异常, 请联系系统管理员";



    //参数相关code码000001开始

    public static final String ARGUMENT_INVALID_CODE = "000001";
    public static final String ARGUMENT_INVALID_MESG = "数据为空，请检查必填参数！";

    public static final String ARGUMENT_TYPE_CODE = "000002";
    public static final String ARGUMENT_TYPE_MESG = "参数数据类型错误！";

    public static final String ARGUMENT_LENGTH_CODE = "000003";
    public static final String ARGUMENT_LENGTH_MESG = "参数长度超过规定长度！";

    public static final String ARGUMENT_FORMAT_CODE = "000004";
    public static final String ARGUMENT_FORMAT_MESG = "参数格式错误,请输入正常数据格式！";

    public static final String ARGUMENT_PASSWORD_FAIL_CODE = "000005";
    public static final String ARGUMENT_PASSWORD_FAIL_MESG = "您输入的原密码不正确，请重新输入！";

    public static final String ARGUMENT_VERIFY_NULL_CODE = "000007";
    public static final String ARGUMENT_VERIFY_NULL_MESG = "校验码已失效，请重新获取！";

    public static final String ARGUMENT_PHONE_REPEAT_CODE = "000009";
    public static final String ARGUMENT_PHONE_REPEAT_MESG = "此号码已被使用，请输入其他电话号码！";

    public static final String ARGUMENT_USER_REPEAT_CODE = "000010";

    public static final String ARGUMENT_PHONE_ERROR_CODE = "000015";

    public static final String ARGUMENT_LOGIN_ERROR_CODE = "000016";
    public static final String ARGUMENT_LOGIN_ERROR_MESG = "当前账户暂未投入使用，请与管理员联系！";


    public static final String OPERATION_FAIL_CODE = "000017";
    public static final String OPERATION_FAIL_MESG = "请求处理失败";

    public static final String  PHONE_EXIT= "000018";

    // 文件操作code起始码 200001
    public static final String ARGUMENT_INVALID_EMAIL_GET_SEND_LOG = "200001";
    public static final String ARGUMENT_INVALID_EMAIL_GET_SEND_MESG = "获取数据异常";

    public static final String ARGUMENT_INVALID_EMAIL_UPLOAD_FILE_LOG = "200002";
    public static final String ARGUMENT_INVALID_EMAIL_UPLOAD_FILE_MESG = "上传文件错误";

    public static final String ARGUMENT_INVALID_EMAIL_DOWNLOAD_FILE_LOG = "200003";
    public static final String ARGUMENT_INVALID_EMAIL_DOWNLOAD_FILE_MESG = "文件没有找到或者文件读取、关闭异常";

    public static final String ARGUMENT_INVALID_EMAIL_AUDIT_STATUS_CODE = "200004";
    public static final String ARGUMENT_INVALID_EMAIL_AUDIT_STATUS_MESG = "邮件已经审核,请勿重复审核";


    public static final String ARGUMENT_IMAGE_CONTENTTYPE_CODE = "200004";

    public static final String ARGUMENT_INVALID_ADD_TOKEN_LOG = "300001";
    public static final String ARGUMENT_INVALID_ADD_TOKEN_MESG = "Token入库异常";
    public static final String INVALID_CODE_ERROR = "300002";
    public static final String INVALID_CODE_INVALID = "300003";

    public static final String INVALID_CODE_TOKEN_FAILURE = "300004";
    public static final String INVALID_CODE_TOKEN_FAILURE_MESG = "token失效";
    public static final String INVALID_CODE_SESSION_FAILURE = "300005";
    public static final String INVALID_CODE_SESSION_FAILURE_MESG = "会话失效，请重新登录";
    public static final String INVALID_CODE_COMPANY_EXISTS = "300006";
    public static final String INVALID_CODE_COMPANY_EXISTS_MESG = "使用该统一社会信用代码的公司已存在";
    public static final String INVALID_CODE_CLICK_TIME_MORE_FAILURE = "300007";
    public static final String INVALID_CODE_CLICK_TIME_MORE_MESG = "短时间内点击次数过多";


    public static final String ENTERPRICES_PUBLISH_CODE = "300009";
    public static final String ENTERPRICES_PUBLISH_CODE_MESG = "当前企业未认证或冻结，不能作此操作";
    public static final String NEED_STATUS_CODE = "300011";
    public static final String NEED_STATUS_CODE_MESG = "抱歉，该需求已撤销发布或删除";
    public static final String COMPANY_PRODUCT_CODE = "300012";
    public static final String COMPANY_PRODUCT_CODE_MESG = "抱歉，该企业已和该产品正在对接中";
    public static final String FINANCIAL_INFO_CODE = "300013";
    public static final String FINANCIAL_INFO_CODE_MESG = "该金融机构被冻结或不存在";
    public static final String PRODUCT_EXIT_CODE = "300015";
    public static final String PRODUCT_EXIT_CODE_MESG = "该产品未发布或已删除";
    public static final String PRODUCT_AUDIT_CODE = "300016";
    public static final String PRODUCT_AUDIT_CODE_MESG = "该对接已被审核";
    public static final String PRODUCT_AUDIT_PEIMISSON_CODE = "300017";
    public static final String PRODUCT_AUDIT_PEIMISSON_MESG = "抱歉,该申请你无权限审核";

    public static final String NOT_OPERATE_CODE = "300018";
    public static final String NOT_OPERATE_MESG = "抱歉,当前状态下不能进行此操作！";

    public static final String CREDIT_NO_NOT_REPEAT_CODE = "300019";
    public static final String CREDIT_NO_NOT_REPEAT_MESG = "授信编号重复，请核实。";

    public static final String LOAN_NO_NOT_REPEAT_CODE = "300020";
    public static final String LOAN_NO_NOT_REPEAT_MESG = "贷款合同号重复，请核实。";


    //逻辑判断
    public static final String USER_ALREADY_EXISTS_CODE = "400001";
    public static final String USER_ALREADY_EXISTS_MESG = "该用户已存在预警配置";

    //
    public static final String BUSINESS_EXCEPTION_CODE = "400001";
    public static final String BUSINESS_EXCEPTION_MESG = "业务异常";
    /**
     * 响应码MAP
     */
    private static Map<String, String> RESPONSE_CODE_MAPPING;

    static {
        RESPONSE_CODE_MAPPING = new HashMap<String, String>();
        // 统一成功码
        RESPONSE_CODE_MAPPING.put(SUCCESS_CODE, SUCCESS_MESG);
        // 统一异常码
        RESPONSE_CODE_MAPPING.put(SYSTEM_FAIL_CODE, SYSTEM_FAIL_MESG);

        // 数据为空，请检查必填参数
        RESPONSE_CODE_MAPPING.put(ARGUMENT_INVALID_CODE, ARGUMENT_INVALID_MESG);
        //参数数据类型错误
        RESPONSE_CODE_MAPPING.put(ARGUMENT_TYPE_CODE, ARGUMENT_TYPE_MESG);
        //参数长度超过规定长度
        RESPONSE_CODE_MAPPING.put(ARGUMENT_LENGTH_CODE, ARGUMENT_LENGTH_MESG);
        //参数格式错误,请输入正常数据格式
        RESPONSE_CODE_MAPPING.put(ARGUMENT_FORMAT_CODE, ARGUMENT_FORMAT_MESG);
        //您输入的原密码不正确，请重新输入
        RESPONSE_CODE_MAPPING.put(ARGUMENT_PASSWORD_FAIL_CODE, ARGUMENT_PASSWORD_FAIL_MESG);
        //校验码已失效，请重新获取
        RESPONSE_CODE_MAPPING.put(ARGUMENT_VERIFY_NULL_CODE, ARGUMENT_VERIFY_NULL_MESG);
        //此号码已被使用，请输入其他电话号码
        RESPONSE_CODE_MAPPING.put(ARGUMENT_PHONE_REPEAT_CODE, ARGUMENT_PHONE_REPEAT_MESG);
        //当前账户暂未投入使用，请与管理员联系
        RESPONSE_CODE_MAPPING.put(ARGUMENT_LOGIN_ERROR_CODE, ARGUMENT_LOGIN_ERROR_MESG);
        //使用该统一社会信用代码的公司已存在
        RESPONSE_CODE_MAPPING.put(INVALID_CODE_COMPANY_EXISTS, INVALID_CODE_COMPANY_EXISTS_MESG);
        //短时间内点击次数过多
        RESPONSE_CODE_MAPPING.put(INVALID_CODE_CLICK_TIME_MORE_FAILURE, INVALID_CODE_CLICK_TIME_MORE_MESG);
        //请求处理失败
        RESPONSE_CODE_MAPPING.put(OPERATION_FAIL_CODE, OPERATION_FAIL_MESG);

        // 邮件审核错误返回码
        RESPONSE_CODE_MAPPING.put(ARGUMENT_INVALID_EMAIL_AUDIT_STATUS_CODE, ARGUMENT_INVALID_EMAIL_AUDIT_STATUS_MESG);

        // 获取数据异常
        RESPONSE_CODE_MAPPING.put(ARGUMENT_INVALID_EMAIL_GET_SEND_LOG, ARGUMENT_INVALID_EMAIL_GET_SEND_MESG);
        // 上传文件错误
        RESPONSE_CODE_MAPPING.put(ARGUMENT_INVALID_EMAIL_UPLOAD_FILE_LOG, ARGUMENT_INVALID_EMAIL_UPLOAD_FILE_MESG);
        // 文件没有找到或者文件读取、关闭异常
        RESPONSE_CODE_MAPPING.put(ARGUMENT_INVALID_EMAIL_DOWNLOAD_FILE_LOG, ARGUMENT_INVALID_EMAIL_DOWNLOAD_FILE_MESG);
        // Token入库异常
        RESPONSE_CODE_MAPPING.put(ARGUMENT_INVALID_ADD_TOKEN_LOG, ARGUMENT_INVALID_ADD_TOKEN_MESG);
        //token失效
        RESPONSE_CODE_MAPPING.put(INVALID_CODE_TOKEN_FAILURE, INVALID_CODE_TOKEN_FAILURE_MESG);
        //session失效
        RESPONSE_CODE_MAPPING.put(INVALID_CODE_SESSION_FAILURE, INVALID_CODE_SESSION_FAILURE_MESG);
        RESPONSE_CODE_MAPPING.put(USER_ALREADY_EXISTS_CODE, USER_ALREADY_EXISTS_MESG);

    }


    /**
     * 响应码
     */
    private String code;

    /**
     * 响应内容
     */
    private String mesg;

    /**
     * 消息代码是否无效
     */
    private boolean isInvalidCode = false;

    public ResponseCode(final String code) {
        this.code = code;
        mesg = null;
    }

    public ResponseCode(final String code, final String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public static String getMessage(final String code) {
        return ResponseCode.getMessage(code, false);
    }

    public static String getMessage(final String code, final boolean showCode) {
        String mesg = RESPONSE_CODE_MAPPING.get(code);
        ResponseCode responseCode = new ResponseCode(code, mesg);

        if (StringUtils.isEmpty(mesg)) {
            responseCode.isInvalidCode = true;
        }

        return responseCode.toString(showCode);
    }

    @Override
    public String toString() {
        return this.toString(true);
    }

    public String toString(final boolean showCode) {
        String returnString = "";

        if (showCode) {
            returnString = "[" + code + "] " + mesg;
        } else {
            returnString = mesg;
        }

        return returnString;
    }

}
