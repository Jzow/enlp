package ai.iston.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 字段自动填充
 *
 * @link https://mp.baomidou.com/guide/auto-fill-metainfo.html
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增填充创建时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {


        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        try {
            this.strictInsertFill(metaObject, "createBy", () -> "admin", String.class);
        }catch (RuntimeException exception){
            this.strictInsertFill(metaObject, "createBy", () -> "admin", String.class);
        }


    }

    /**
     * 更新填充更新时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        try {
            this.strictUpdateFill(metaObject, "updateBy",  () -> "admin", String.class);
        }catch (RuntimeException exception){
            this.strictUpdateFill(metaObject, "updateBy", () -> "admin", String.class);
        }

    }

}
