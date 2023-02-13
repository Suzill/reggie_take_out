package com.itheima.reggie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 订单明细表
 * @TableName order_detail
 */
@TableName(value ="order_detail")
@Data
public class OrderDetail implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 名字
     */
    @TableField(value = "name")
    private String name;

    /**
     * 图片
     */
    @TableField(value = "image")
    private String image;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Long order_id;

    /**
     * 菜品id
     */
    @TableField(value = "dish_id")
    private Long dish_id;

    /**
     * 套餐id
     */
    @TableField(value = "setmeal_id")
    private Long setmeal_id;

    /**
     * 口味
     */
    @TableField(value = "dish_flavor")
    private String dish_flavor;

    /**
     * 数量
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderDetail other = (OrderDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getOrder_id() == null ? other.getOrder_id() == null : this.getOrder_id().equals(other.getOrder_id()))
            && (this.getDish_id() == null ? other.getDish_id() == null : this.getDish_id().equals(other.getDish_id()))
            && (this.getSetmeal_id() == null ? other.getSetmeal_id() == null : this.getSetmeal_id().equals(other.getSetmeal_id()))
            && (this.getDish_flavor() == null ? other.getDish_flavor() == null : this.getDish_flavor().equals(other.getDish_flavor()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getOrder_id() == null) ? 0 : getOrder_id().hashCode());
        result = prime * result + ((getDish_id() == null) ? 0 : getDish_id().hashCode());
        result = prime * result + ((getSetmeal_id() == null) ? 0 : getSetmeal_id().hashCode());
        result = prime * result + ((getDish_flavor() == null) ? 0 : getDish_flavor().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", image=").append(image);
        sb.append(", order_id=").append(order_id);
        sb.append(", dish_id=").append(dish_id);
        sb.append(", setmeal_id=").append(setmeal_id);
        sb.append(", dish_flavor=").append(dish_flavor);
        sb.append(", number=").append(number);
        sb.append(", amount=").append(amount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}