# graceLibrary
自己搭建的一个便捷的软件架构

## 使用依赖
>  在根项目build.gradle的allprojects -->  repositories 中添加： maven { url "https://jitpack.io" }

>  在项目的build.gradle的dependencies中添加：implementation 'com.github.CaostGrace:graceLibrary:1.1.2'

## 使用注意

> 支持Mvp模式，其中Activity和Fragment为P层，Activity需继承ActivityDelegate，Fragment继承FragmentDelegate，V层及M层分别继承BaseModel及BaseView
> 其中v层Activity和Fragment通用，用法如下：

###### Activity
```
public class TestActivity extends ActivityDelegate<TestView, TestModel> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_mvp);

        mModel.addDisposable(TestModel.mHttpManager
                .createApi(TestApi.class)
                .defaultTest("", "成都天气")
                .compose(RxSchedulers.<Bean>defaultCompose())
                .subscribeWith(new DefaultObserver<Bean>(mView) {
                    @Override
                    public void onHandleSuccess(Bean bean) {
                        ToastUtil.init(TestActivity.this)
                                .makeText(bean.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        );


    }
    @Override
    public Class<?> getViewClass() {
        return TestView.class;
    }

    @Override
    public Class<?> getModelClass() {
        return TestModel.class;
    }

}
```

###### Activity V层
```
public class TestView extends BaseView {
    DialogManager manager;

    LoadDialog mDialog;

    @BindView(R.id.frame_layout)
    public FrameLayout mFrameLayout;

    @Override
    public void showLoadingView(String str) {
        LogUtils.d(mContext.getCacheDir().getAbsolutePath());
        mDialog = LoadDialog.create(mContext)
                .setTitleText("加载中...");
        mDialog.show();
    }

    @Override
    public void showErrorView(String str) {
        Alerter.create((Activity) mContext)
                .setDuration(2000)
                .setTitle("错误信息")
                .setText(str)
                .show();
    }

    @Override
    public void hideLoadingView() {
        if (mDialog != null){
            mDialog.cancel();
        }
    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorMsg(String str) {
        manager = new DialogManager
                .Builder()
                .setFragmentManager(mFragmentManager)
                .create()
                .setTitle("注意，这是一个错误信息", DialogManager.HighMode.HIGH)
                .setMessage(str)
                .build();
        manager.show();
    }

    @Override
    public void initView() {

    }


    @OnClick({R.id.test01,R.id.test02})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.test01:
                showErrorMsg("加载中...");
                break;
            case R.id.test02:
                TestFragment fragment = new TestFragment();
                mFragmentManager.beginTransaction().add(R.id.frame_layout,fragment)
                        .commit();
                break;
                
        }
    }

}

```

###### M 层
```
public class TestModel extends BaseModel {

}
```

###### Fragment
```
public class TestFragment extends FragmentDelegate<FragmentView,TestModel> {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_mvp;
    }

    @Override
    public void init() {
        LogUtils.d("init");
    }

    @Override
    public Class<?> getViewClass() {
        return FragmentView.class;
    }

    @Override
    public Class<?> getModelClass() {
        return TestModel.class;
    }
}
```

###### Fragment V层
```
public class FragmentView extends BaseView {
    @Override
    public void initView() {

    }

    @OnClick({R.id.test01})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.test01:
                ToastUtil.init(mContext).makeSuccessToast("成功", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    @Override
    public void showLoadingView(String str) {

    }

    @Override
    public void showErrorView(String str) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorMsg(String str) {

    }
}

```
#### 注意
如果要使用自带的mvp模式，项目的Application需要继承依赖的ApplicationLibrary重写getBaseUrl方法或者重写BaseModel的getHttpBaseUrl方法
Activity和Fragment均可封装为BaseActivity和BaseFragment 只当子类有自己的需求时在重写具体的两个返回Model层和View层类对象的的方法。
###### 返回Model层对象的方法
> Class<?> getModelClass();

###### 返回View层对象的方法
> Class<?> getViewClass();

#### 例如
###### BaseActivity
```
public class BaseActivity extends ActivityDelegate<BaseView,BaseModel> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

    }


    @Override
    public void setContentView(int layoutResID) {
        mView.setContentView(layoutResID);
    }

    @Override
    public Class<?> getViewClass() {
        return BaseView.class;
    }

    @Override
    public Class<?> getModelClass() {
        return BaseModel.class;
    }
}

```
###### BaseModel
```
public class BaseModel extends cn.logcode.library.mvp.BaseModel {
    @Override
    public String getHttpBaseUrl() {
        return AppConfig.BASE_URL;
    }
}

```
###### BaseView
```
public class BaseView extends cn.logcode.library.mvp.BaseView {

    protected RelativeLayout toolbar;
    protected FrameLayout container;
    protected View content;

    //占位View 可用于设置加载View或者错误View
    protected FrameLayout seat_view;

    @Override
    public void initView() {
        toolbar = get(R.id.toolbar);
        container = get(R.id.container);
        seat_view = get(R.id.seat_view);
    }


    public void setContentView(@LayoutRes int layoutResID) {
        content = View.inflate(mContext,layoutResID,null);
        container.addView(content,0);
    }

    @Override
    public void showLoadingView(String s) {

    }

    @Override
    public void showErrorView(String s) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void hideErrorView() {

    }

    @Override
    public void showErrorMsg(String s) {

    }


    //显示占位View
    public void showSeatView(){
        container.setVisibility(View.GONE);
        seat_view.setVisibility(View.VISIBLE);
    }
    //隐藏占位View
    public void hideSeatView(){
        seat_view.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }

}

```

###### BaseActivity的子类Activity
```
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       

    }
}

```

###### 无具体需求时，子类使用父类的V层和M层，有需求时，子类自己定义V层或者M层，然后重写Class<?> getViewClass();、Class<?> getModelClass();方法

###### 有自己需求的子类Activity
```
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.mHandler.postDelayed(() ->{
            mView.showSeatView();
        },2000);

        App.mHandler.postDelayed(() ->{
            mView.hideSeatView();
        },10000);

    }


    @Override
    public Class<?> getViewClass() {
        return MainView.class;
    }
}
```

###### 子类的V层
```
public class MainView extends BaseView {
    @Override
    public void initView() {
        super.initView();

        App.mHandler.postDelayed(() ->{
            ToastUtil.init(mContext)
                    .makeErrorToast("网络错误啦", Toast.LENGTH_SHORT)
                    .show();
        },2000);


        App.mHandler.postDelayed(() ->{
            ToastUtil.init(mContext)
                    .makeSuccessToast("网络正常啦", Toast.LENGTH_SHORT)
                    .show();
        },10000);

    }
}

```
###### 子类可直接重写父类的V层，添加自己所需的功能，也可以重写框架提供的V层

>  具体例子见app的中例子代码，框架提供的MVP模式，提供了HttpManager实例，可直接用此实例进行网络请求，重写的getHttpBaseUrl方法就是Http地址


## 还有很多其他功能和小工具未写完，下次再行补上，请自行摸索！！！！！。

