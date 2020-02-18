package com.jex.config;

import com.jex.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验配置
 * @author Jex
 *
 */
@Service(value = "jx")
public class JXPermissionConfig {

    @Value("${interface.permission.enabled}")
    private Boolean interfacePermission;

    public Boolean check(String ...permissions){

        if(!interfacePermission) {
            return true;
        }

        List<String> jxPermissions = SecurityUtils.getUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return jxPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(jxPermissions::contains);
    }
}
