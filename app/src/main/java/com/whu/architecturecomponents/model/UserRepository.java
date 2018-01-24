package com.whu.architecturecomponents.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.whu.architecturecomponents.model.db.UserDao;
import com.whu.architecturecomponents.model.entity.User;
import com.whu.architecturecomponents.model.http.WebService;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/1/24.
 */

//单例
@Singleton
public class UserRepository {
    //网络服务
    private final WebService webService;
    //数据缓存
    private final UserDao userDao;
    //并发执行
    private final Executor executor;

    @Inject
    public UserRepository(WebService webService, UserDao userDao, Executor executor) {
        this.webService = webService;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userId) {
        //先检测本地数据库中有没有缓存数据，如果没有则发送网络请求
        refreshUser(userId);
        //从本地数据库中获取数据
        return userDao.load(userId);
    }

    private void refreshUser(final String userId) {
        executor.execute(() -> {
            //后台线程中执行，检查是否最近获取过数据
            boolean userExists = userDao.hasUser();
            if (!userExists) {
                //发送网络请求数据
                try {
                    //同步方法，异步方法用enqueue()
                    Response<User> response = webService.getUser(userId).execute();
                    userDao.save(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
