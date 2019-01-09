[![Release](https://jitpack.io/v/ashLikun/MaterialRatingBar.svg)](https://jitpack.io/#ashLikun/MaterialRatingBar)

## **MaterialRatingBar**
    评论进度的view
## 使用方法

build.gradle文件中添加:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
并且:

```gradle
dependencies {
     //如果不使用内部的Android  designVersion（27.0.2） 可以这样
     implementation ('com.github.ashLikun:MaterialRatingBar:{latest version}'){
         exclude group: 'com.android.support'
     }


}
```

##详细介绍
