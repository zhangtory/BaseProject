package com.zhangtory.base.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;

/**
 * @Author: ZhangTory
 * @Date: 2020-10-30
 * @Description: 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LmRefundLog implements Serializable {

    /**
     * 记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * lm退款的子订单号
     */
    private String lmSubOrderId;

    /**
     * 发生时间
     */
    private LocalDateTime time;

    /**
     * 事件描述
     */
    @TableField("`desc`")
    private String desc;

}
