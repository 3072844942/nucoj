package com.q7g.judgehost.controller;

import com.q7g.judgehost.exception.http.ForbiddenException;
import com.q7g.judgehost.core.common.UnifiedResponse;
import com.q7g.judgehost.dto.AuthorizationDTO;
import com.q7g.judgehost.dto.AccessTokenDTO;
import com.q7g.judgehost.service.AuthorizationService;
import com.q7g.judgehost.vo.AuthorizationVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 权限处理相关接口
 */

@RestController
@RequestMapping("/auth")
@Validated
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    /**
     * 获取判题服务器接口调用凭据，以供后续接口调用使用
     *
     * @author yuzhanglong
     * @date 2020-7-1 11:24
     */
    @PostMapping("/access_token")
    public AuthorizationVO getAccessToken(@RequestBody @Validated AuthorizationDTO authorizationDTO) {
        String accessToken = authorizationService.getAccessToken(authorizationDTO);
        return new AuthorizationVO(accessToken, authorizationService.getExpiredTime());
    }

    /**
     * 验证判题服务器接口调用凭据
     *
     * @author yuzhanglong
     * @date 2020-7-1 11:24
     */
    @PostMapping("/examination")
    public UnifiedResponse checkAuthToken(@RequestBody AccessTokenDTO accessTokenDTO) {
        Boolean isPass = authorizationService.checkAccessToken(accessTokenDTO);
        if (!isPass) {
            throw new ForbiddenException("A0003");
        }
        return new UnifiedResponse("00000", "ACCESS_TOKEN_PASS!", null);
    }
}