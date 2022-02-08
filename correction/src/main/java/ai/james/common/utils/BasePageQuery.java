package ai.james.common.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;

@Data
public class BasePageQuery {

    @ApiModelProperty(value = "当前页", example = "1")
    private transient Integer pageNum = 1;

    @ApiModelProperty(value = "每页记录数", example = "10")
    private transient Integer pageSize = 10;


    /**
     * 获取页面对象
     *
     * @return
     */
    public <E> Page<E> buildMybatisPage() {
        Page<E> page = new Page<>();

        if (pageNum != null) {
            page.setCurrent(pageNum);
        }

        if (pageSize != null) {
            page.setSize(pageSize);
        }

        return page;
    }

    public <E> Page<E> buildMybatisPage(OrderItem... orderItem) {
        Page<E> page = new Page<>();

        if (pageNum != null) {
            page.setCurrent(pageNum);
        }

        if (pageSize != null) {
            page.setSize(pageSize);
        }

        if(orderItem.length>0){
            page.setOrders(Arrays.asList(orderItem));
        }


        return page;
    }
}
