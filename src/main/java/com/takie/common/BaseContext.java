/**
 * FileName: BaseContext
 * Author: jane
 * Date: 2022/7/27 16:27
 * Description: tool class based on threadLocal, used to save user and get the id of current user
 * Version:
 */

package com.takie.common;

public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);

    }
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
