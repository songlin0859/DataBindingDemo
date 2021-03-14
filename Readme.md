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

`@={}` 表示法（其中重要的是包含“=”符号）可接收属性的数据更改并同时监听用户更新。


### Fragment绑定

### RecyclerView绑定

### 注解
[注解](https://developer.android.google.cn/reference/android/databinding/package-summary?hl=en&authuser=0#annotations)、
1. Bindable 
    The Bindable annotation should be applied to any getter accessor method of an Observable class. 
    Bindable注解应应用于Observable类的任何getter方法。（直接用在字段上也可）
    Bindable将在BR类中生成一个字段以标识已更改的字段。
```kotlin
class LoginBean : BaseObservable() {
    @Bindable
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

    var password: String? = null
        @Bindable
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @Bindable(value = ["name","password"])
    var desc:String? = null
    get() {
        return "${name ?: ""}:${password ?: ""}"
    }
    private set

}
``` 
2. BindingAdapter 	
    BindingAdapter is applied to methods that are used to manipulate how values with expressions are set to views.
    BindingAdapter应用于用于维护如何将带有表达式的值设置为View的方法。 
    其实就是将xml中的某个属性和xml对应View的方法对应起来
3. BindingConversion 	
    Annotate methods that are used to automatically convert from the expression type to the value used in the setter. 
    注释方法，用于自动从表达式类型转换为设置器中使用的值。
报错：`Cannot find a setter for <TextView android:text> that accepts parameter type 'double'`
```xml
    <!--Cannot find a setter for <TextView android:text> that accepts parameter type 'double'-->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@{user.wegiht}"
        />
```
编写一个doubleToString的函数并且用BindingConversion注解 报错就没了
```kotlin
    @BindingConversion
    fun doubleToString(d: Double?): String {
        return d?.toString() ?: ""
    }
```
其实对付报错也可以使用String.valueOf(d)解决 但是使用这个注解的可以自动处理所有需要String类型但是传入的是Double的地方

4. BindingMethod 	Used within an BindingMethods annotation to describe a renaming of an attribute to the setter used to set that attribute. 
5. BindingMethods 	Used to enumerate attribute-to-setter renaming. 
6. InverseBindingAdapter 	InverseBindingAdapter is associated with a method used to retrieve the value for a View when setting values gathered from the View. 
7. InverseBindingMethod 	InverseBindingMethod is used to identify how to listen for changes to a View property and which getter method to call. 
8. InverseBindingMethods 	Used to enumerate attribute, getter, and event association. 
9. InverseMethod 	
    The InverseMethod annotation may be applied to any method used in two-way data binding to declare the method used to invert the call when going from the View's attribute value to the bound data value.
    InverseMethod注解可以应用于双向数据绑定中使用的任何方法，以声明从View的属性值转到绑定的数据值时用于反转调用的方法。
    逆方法必须采用相同数量的参数并且只有最终参数类型可能有所不同。 此方法的最终参数必须匹配其反函数的返回值和此方法的返回值必须与最终值匹配其反参数。  
 ```kotlin
    @InverseMethod("stringToInt")
    fun intToString(i: Int?): String? {
        return i?.toString()
    }
    
    fun stringToInt(str: String?): Int? {
        if (str == null){
            return null
        }
        try {
            return str.toInt()
        }catch (e : Exception){
    
        }
        return null
    
    }
```
```xml
    <EditText
        android:id="@+id/age"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@={com.sl.databindingdemo.anno.InverseMethodUtilKt.intToString(login.age)}"
        android:ems="10" />
```

### 原理
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="1"
    android:tag="layout/activity_user_0"
    android:layout_width="-1"
    android:layout_height="-1">

    <TextView
        android:textSize="dimension(4610)"
        android:tag="binding_1"
        android:padding="dimension(2561)"
        android:layout_width="-1"
        android:layout_height="-2" />

    <TextView
        android:textSize="dimension(4610)"
        android:tag="binding_2"
        android:padding="dimension(2561)"
        android:layout_width="-1"
        android:layout_height="-2" />

    <TextView
        android:tag="binding_3"
        android:layout_width="-1"
        android:layout_height="-2" />
</LinearLayout>
```