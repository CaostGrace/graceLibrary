package cn.logcode.library.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


/**
 * Created by 24327 on 2018/4/27.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private int dbVersion;
    public DBHelper(Context context,String dbName,int dbVersion) {
        super(context, dbName, null, dbVersion);
        this.dbVersion = dbVersion;
    }

    public void createTable(Class<?> cls) throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, cls);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
    }
    public int getDbVersion(){
        return dbVersion;
    }
}
