package com.dongtian.shopcommon.entity;

import lombok.Data;
/*
@Data：注解在类上，其中包含了@Getter、@Setter、@ToString、@EqualsAndHashCode、@RequiredArgsConstructor等；
* */
@Data
public class User extends BaseEntity{
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;

    public User() { }
}
