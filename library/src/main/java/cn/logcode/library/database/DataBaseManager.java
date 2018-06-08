package cn.logcode.library.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by CaostGrace on 2018/5/29 21:48
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.database
 * @class_name: DataBaseManager
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class DataBaseManager {

    private DBHelper dbHelper;

    private DataBaseManager(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    /**
     * 创建表
     * @param cls
     */
    public void createTable(Class<?> cls){
        try {
            dbHelper.createTable(cls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到dao对象
     * @param cls
     * @param <D>
     * @param <T>
     * @return
     */
    public <D extends Dao<T, ?>, T> D getDao(Class<T> cls){
        Dao<T, ?> dao = null;
        try {
            dao = dbHelper.getDao(cls);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        D castDao = (D) dao;
        return castDao;
    }


    /**
     * 更新数据库
     * @param daos
     * @param <D>
     * @param <T>
     */
    public <D extends Dao<T, ?>, T> void updateDataBase(D... daos){
        for(int i=0;i<daos.length;i++){
            
        }
    }

    public static class Builder{

        private Context context;
        private String dbName;
        private int dbVersion;

        public Builder(Context context){
            this.context = context;

            dbName = "appDb";
            dbVersion = 1;
        }

        public Builder dbName(String dbName){
            this.dbName = dbName;
            return this;
        }

        public Builder dbVersion(int dbVersion){
            this.dbVersion = dbVersion;
            return this;
        }

        public DataBaseManager build(){
            DBHelper dbHelper = new DBHelper(context,dbName,dbVersion);
            return new DataBaseManager(dbHelper);
        }
    }
}
