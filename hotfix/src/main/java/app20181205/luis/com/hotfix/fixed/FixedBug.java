package app20181205.luis.com.hotfix.fixed;

import app20181205.luis.com.hotfix.annotation.MethodReplace;

public class FixedBug {
    @MethodReplace(clazz="app20181205.luis.com.hotfix.TestBug", method="getRetString")
    public String getRetString() {
        return "已经修复成功";
    }
}
