package cn.logcode.frameworkdemo.test.dbTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import cn.logcode.frameworkdemo.R;
import cn.logcode.library.database.DataBaseManager;

/**
 * Created by CaostGrace on 2018/5/29 16:25
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test
 * @class_name: TestActivity
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseManager defaultManger = new DataBaseManager
                .Builder(this)
                .build();
        DataBaseManager customManger = new DataBaseManager
                .Builder(this)
                .dbName("Custom")
                .dbVersion(2)
                .build();

        UserBean bean = new UserBean();
        bean.age = 18;
        bean.name = "张三";
        bean.sex = "男";

        defaultManger.createTable(UserBean.class);
        defaultManger.createTable(UserBean.class);

        customManger.createTable(UserBean.class);
        customManger.createTable(UserBean.class);

        Dao<UserBean,?> defaultDao = defaultManger.getDao(UserBean.class);
        Dao<UserBean,?> customDao = customManger.getDao(UserBean.class);

        for (int i=0;i<10;i++){
            try {
                defaultDao.create(bean);
                customDao.create(bean);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
