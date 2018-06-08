package cn.logcode.frameworkdemo.test.dbTest;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by CaostGrace on 2018/5/30 13:35
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test.dbTest
 * @class_name: UserBean
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
@DatabaseTable(tableName = "UserTable")
public class UserBean {
    @DatabaseField(columnName = "age",dataType = DataType.INTEGER)
    public int age;
    @DatabaseField(columnName = "name")
    public String name;
    @DatabaseField(columnName = "sex")
    public String sex;
}
