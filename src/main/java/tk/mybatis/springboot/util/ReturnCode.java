/**
 *
 */
package tk.mybatis.springboot.util;

/**
 * @author shipeng.yu
 * @version V1.0
 * @date 2015年8月28日 上午9:41:54
 * @description
 */
public enum ReturnCode {

    SUCCESS(200, "操作正确"),
    /**
     *
     */
    DATA_NOT_FOUND(520, "数据不存在"),
    /**
     *
     */
    PARAM_ISNOTVAILD(570, "参数不正确"),
    /**
     *
     */
    FAIL(574, "内部异常"),
    /**
     *
     */
    NOT_HAVE_CONTACT(575, "请先设置联系方式"),
    /**
     *
     */
    PAGE_OUT_SIDE(576, "该页码无效"),
    /**
     *
     */
    TEMP_MAX(577, "模板已达最多数量"),
    /**
     *
     */
    EMAIL_FORMAT_ERR(578, "邮件格式出错"),
    /**
     *
     */
    TEMP_LAST(579, "请至少保留一个模板"),
    /**
     *
     */
    TEMP_IS_NULL(580, "请填写模板内容"),

    LENGTH_INVALID(581, "超出长度限制"),
    /**
     *
     */
    TELE_FORMAT_ERR(582, "手机号格式出错"),

    PLEASE_WAIT(583, "请一分钟后提交"),

    MOBILE_IS_REGISTERED(585, "手机号已被注册"),

    MOBILE_NOT_REGISTERED(588, "手机号未被注册"),

    REGISTERED_SUCCESSED(586, "注册成功"),

    REGISTERED_FAILED(587, "注册失败"),

    /**
     * 设置头像失败
     */
    USER_SET_AVATER_FAIL(703, "用户设置头像失败"),
    /**
     * 用户不能设置其它用户信息
     */
    USER_SET_OTHER_ERR(704, "用户不能设置其它用户信息"),
    /**
     * 获取用户信息失败
     */
    USER_GET_FAIL(705, "获取用户信息失败"),
    /**
     * 用户操作非个人所有的数据
     */
    USER_SET_IMPERSONAL(706, "用户操作非个人所有的数据"),
    /**
     * 用户未登录
     */
    USER_NOT_LOGIN(710, "用户未登录"),
    /**
     * 登录失败
     */
    LOGIN_FAIL(711, "登录失败,账号或密码错误"),
    /**
     * 账号不能为空
     */
    USER_ACCOUNT_IS_NOT_NULL(715, "账号不能为空"),
    /**
     * 密码不能为空
     */
    USER_PASSWORD_IS_NOT_NULL(716, "密码不能为空"),

    /**
     * 账号不存在
     */
    USER_ACCOUNT_IS_NOT_EXIST(717, "登录失败,账号不存在"),

    USER_EXIST(900, "修改失败，账号已存在"),

    INVITATION_CODE_USED(901, "邀请码已使用"),

    MOBILE_NOT_EXIST(902, "手机号码不存在"),

    CHECK_CODE_TIME_OUT(903, "验证码不存在或已过期，请重新获取"),

    CHECK_CODE_NOT_VALID(904, "请输入正确的验证码"),

    /**
     * 密码错误
     */
    USER_PASSWORD_IS_ERR(718, "密码错误"),
    /**
     * 用户无访问权限
     */
    USER_NOT_ACCESS(719, "用户无访问权限"),

    USER_NO_RIGHT(721, "权限不足"),

    /**
     * 已经绑定
     */
    ALREADY_BIND(720, "已经绑定"),
    /**
     * 上传失败
     */
    UPLOAD_FAIL(801, "上传失败"),
    /**
     * 上传图片失败
     */
    UPLOAD_PIC_FAIL(802, "图片上传失败"),
    /**
     * 上传格式出错
     */
    UPLOAD_FORMAT_FAIL(803, "上传格式出错"),

    NOT_WITNESS(804, "你还未见证该连载，不能催更哦"),

    HAS_URGENT(805, "你今天已经催更过啦，不要太着急哦"),

    HAS_FINISH(806, "该连载已完结，不能催更或见证哦"),

    TAG_INVALID(807, "最多只能有5个标签且每个标签不得超过8个字"),

    LIMIT_ACCOUNT_NUM(808, "手机号或者三方账号至少要保留一个"), CAN_NOT_GATHER(809, "不符合采集条件"), WAIT_AUDIT(810, "先前提交的还未审核，请等待"),
    HAS_GATHER(811, "已经采集过该照片"), NO_PAINTER_PLAN(812, "不存在画照片的连载或该连载为隐私"), NOT_APPLY(813, "为申请画师认证"), ALREADY_IN_GROUP(814, "你已经在该圈子中,不能再次申请"), WAIT_ADMIN_VERIFY(815, "你已经提交,耐心等待管理员审核"), PLAN_ALREADY_IN_GROUP(816, "该连载已存在其他圈子中,不能再次申请"), PLAN_NOT_MATCH(817, "连载不符合入圈条件"), LIMIT_MEMBER(818, "达到人数上限,无法申请"), GROUP_NAME_ALREADY_EXIST(819, "圈子昵称已存在,请更换其他名称"), NOT_GROUP_MEMBER(820, "被转让的用户不在该圈子,请重新选择"), IN_TRANSFING(821, "圈子已经在转让中,等待成员处理"), ALREADY_INVITED(822, "已经邀请,请等待用户回应"), HAS_DEAL(823, "该消息已被处理,不能再次处理"), ADMIN_NO_QUIT(824, "管理员不能直接退出,需要转让后才能退出圈子"), ONLY_ONE_PLAN(825, "只有一个连载,不能撤销"), PLAN_IN_GROUP(826, "连载在圈子中,不可设为隐私或进行回收"), UNDER_REVIEW(827, "该连载正在审核中"), BLACK_LIST(828, "在黑名单中,禁止操作"), THE_SAME_COMMENT(829, "重复评论,10分钟后提交"), ;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
