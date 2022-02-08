package ai.james.utils.result;

/**
 * @author pengmf
 * @since 2021/9/22
 */
public interface IResultCode {

    String getCode();

    String getMsg();

    /**
     * 获取业务码描述
     *
     * @return 业务码描述
     */
    default String getDesc() {
        return getMsg();
    }
}
