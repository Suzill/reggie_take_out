package com.itheima.reggie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "number")
    private String number;

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 下单用户
     */
    @TableField(value = "user_id")
    private Long user_id;

    /**
     * 地址id
     */
    @TableField(value = "address_book_id")
    private Long address_book_id;

    /**
     * 下单时间
     */
    @TableField(value = "order_time")
    private Date order_time;

    /**
     * 结账时间
     */
    @TableField(value = "checkout_time")
    private Date checkout_time;

    /**
     * 支付方式 1微信,2支付宝
     */
    @TableField(value = "pay_method")
    private Integer pay_method;

    /**
     * 实收金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 
     */
    @TableField(value = "address")
    private String address;

    /**
     * 
     */
    @TableField(value = "user_name")
    private String user_name;

    /**
     * 
     */
    @TableField(value = "consignee")
    private String consignee;

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
        Orders other = (Orders) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNumber() == null ? other.getNumber() == null : this.getNumber().equals(other.getNumber()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUser_id() == null ? other.getUser_id() == null : this.getUser_id().equals(other.getUser_id()))
            && (this.getAddress_book_id() == null ? other.getAddress_book_id() == null : this.getAddress_book_id().equals(other.getAddress_book_id()))
            && (this.getOrder_time() == null ? other.getOrder_time() == null : this.getOrder_time().equals(other.getOrder_time()))
            && (this.getCheckout_time() == null ? other.getCheckout_time() == null : this.getCheckout_time().equals(other.getCheckout_time()))
            && (this.getPay_method() == null ? other.getPay_method() == null : this.getPay_method().equals(other.getPay_method()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getUser_name() == null ? other.getUser_name() == null : this.getUser_name().equals(other.getUser_name()))
            && (this.getConsignee() == null ? other.getConsignee() == null : this.getConsignee().equals(other.getConsignee()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNumber() == null) ? 0 : getNumber().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUser_id() == null) ? 0 : getUser_id().hashCode());
        result = prime * result + ((getAddress_book_id() == null) ? 0 : getAddress_book_id().hashCode());
        result = prime * result + ((getOrder_time() == null) ? 0 : getOrder_time().hashCode());
        result = prime * result + ((getCheckout_time() == null) ? 0 : getCheckout_time().hashCode());
        result = prime * result + ((getPay_method() == null) ? 0 : getPay_method().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getUser_name() == null) ? 0 : getUser_name().hashCode());
        result = prime * result + ((getConsignee() == null) ? 0 : getConsignee().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", number=").append(number);
        sb.append(", status=").append(status);
        sb.append(", user_id=").append(user_id);
        sb.append(", address_book_id=").append(address_book_id);
        sb.append(", order_time=").append(order_time);
        sb.append(", checkout_time=").append(checkout_time);
        sb.append(", pay_method=").append(pay_method);
        sb.append(", amount=").append(amount);
        sb.append(", remark=").append(remark);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", user_name=").append(user_name);
        sb.append(", consignee=").append(consignee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}