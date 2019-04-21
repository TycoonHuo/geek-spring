package com.huo.springbucks.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_MENU")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
//    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
//            parameters = @org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY"))
    private Money price;
    @CreationTimestamp
    @Column(updatable = false)
    private Date createTime;
    @UpdateTimestamp
    private Date updateTime;
}
