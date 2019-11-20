package com.dongtian.shopmobileweb.feign;

import com.dongtian.shopmember_api.service.UserService;
import org.springframework.cloud.openfeign.FeignClient;

//调用服务的application
@FeignClient(name = "member")
public interface UserFeign extends UserService {

}
