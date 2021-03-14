# DataBindingDemo

[数据绑定](https://developer.android.google.cn/topic/libraries/data-binding/start?hl=zh_cn&authuser=0)
如需启用数据绑定，请在模块的 build.gradle 文件中将 dataBinding 构建选项设置为 true，如下所示：
```groovy
android {
    //...
    buildFeatures {
        dataBinding true
    }
}
```
貌似还会提示添加 `kotlin-kapt`插件（应该是注解需要用）

---
### 布局和绑定表达式
数据绑定布局文件略有不同，以根标记 layout 开头，后跟 data 元素和 view 根元素。
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <import type="com.sl.databindingdemo.bean.User"/>
        <variable
            name="user"
            type="User" />
    </data>
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".UserActivity">
        <!--android:text="@{user.name}"-->
        <!--布局中的表达式使用“@{}”语法写入特性属性中。-->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="10dp"
            android:textSize="18sp"
            android:text="@{user.name}"
            tools:text="name"
            />

        <!--不能直接将一个int类型的值赋给text-->
        <!--android.content.res.Resources$NotFoundException: String resource ID #0x1e-->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="10dp"
            android:textSize="18sp"
            android:text="@{String.valueOf(user.age)}"
            tools:text="age"
            />
    </LinearLayout>
</layout>
```

### 绑定数据
```
    //替换原来的setContentView
    val mBinding = DataBindingUtil.setContentView<ActivityUserBinding>(this, R.layout.activity_user)
    //通过mBinding绑定页面显示的数据
    mBinding.user = User("Songlin", 30)
    //或者下面这样绑定数据也可
    //mBinding.setVariable(BR.user, User("LL", 26))
```

---

### 双向绑定