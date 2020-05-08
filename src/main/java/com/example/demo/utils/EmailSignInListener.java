package com.example.demo.utils;

import cn.hutool.core.lang.Console;

public class EmailSignInListener extends SignInListener {
    @Override
    public boolean signIn() {
        return false;
    }

    @Override
    public boolean support(SignInEvent event) {
        return super.event.equals(event);
    }
}
