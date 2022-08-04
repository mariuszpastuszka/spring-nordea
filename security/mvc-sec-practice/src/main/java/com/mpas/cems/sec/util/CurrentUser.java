package com.mpas.cems.sec.util;

import org.springframework.core.annotation.AliasFor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;


@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {

    @AliasFor(annotation = AuthenticationPrincipal.class)
    boolean errorOnInvalidType() default false;

    @AliasFor(annotation = AuthenticationPrincipal.class)
    String expression() default "";
}
