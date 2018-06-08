package cn.logcode.library.mvp;

/**
 * Created by CaostGrace on 2018/6/7 16:18
 *
 * @project_name: FrameworkDemo
 * @package_name: cn.logcode.library.mvp
 * @class_name: IMode
 * @github: https://github.com/CaostGrace
 * @简书: http://www.jianshu.com/u/b252a19d88f3
 * @content:
 */
public interface IModel<T extends IDelegate> {
    void onAttach(T t);

    void deAttach();
}
