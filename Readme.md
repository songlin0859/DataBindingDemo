# DatabindingDemo

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

