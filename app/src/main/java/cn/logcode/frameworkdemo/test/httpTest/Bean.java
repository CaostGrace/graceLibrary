package cn.logcode.frameworkdemo.test.httpTest;

/**
 * Created by CaostGrace on 2018/5/29 16:35
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.frameworkdemo.test
 * @class_name: Bean
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public class Bean{

    /**
     * code : 100000
     * text : 成都:周二 05月29日,多云转小雨 无持续风向微风,最低气温18度，最高气温29度
     */

    public int code;
    public String text;

    @Override
    public String toString() {
        return "Bean{" +
                "code=" + code +
                ", text='" + text + '\'' +
                '}';
    }
}
