[![Twitter](https://img.shields.io/badge/Twitter-@selmanozturkk-blue.svg?style=flat)](http://twitter.com/selmanozturkk)
[![Mail](https://opencollective.com/shields/mail/badge.svg)](mailto:info@selman.kim)
![API](https://img.shields.io/badge/API-16%2B-green.svg?style=flat)

Bu bir gradle projesidir. Kaynak koda erişim için aşağıdaki yolu izleyiniz:
--------------------
```
*Android Studio(tavsiye)
   Checkout from Version Control > GitHub
   Git repository URL -> https://github.com/selmanozturk/GetirBiTaksi.git
   Import project from existing model -> Gradle
```
ya da
```
*
git clone https://github.com/selmanozturk/GetirBiTaksi.git
git fetch && git checkout master
```


İmzalı .apk dosyasını [buradan][5] edinebilirsiniz.
--------------------

![Alt Text](https://media.giphy.com/media/xThta2L6nunFKIcPbG/giphy.gif)

Bu bir Gradle projesidir ve aşağıdaki maven dependency'lerini kullanır
--------------------
[Gson][1], 
CardView, 
[Toasty][2], 
[Retrofit][3], 
[ButterKnife][4]

```groovy
buildscript {
  ...
  dependencies {
        ...
        compile 'com.android.support:cardview-v7:27.0.2'
        compile 'com.android.support:recyclerview-v7:27.0.2'
        compile 'com.github.GrenderG:Toasty:1.2.8'
        compile 'com.squareup.retrofit2:converter-gson:2.3.0'
        compile project(':awesome-calendar')
        compile 'com.jakewharton:butterknife:8.8.1'
        annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
  }
}
```
[1]: https://github.com/google/gson
[2]: https://github.com/GrenderG/Toasty
[3]: https://github.com/square/retrofit
[4]: https://github.com/JakeWharton/butterknife
[5]: https://drive.google.com/file/d/1tLJuNSUC4KywA2euQC1s9_hXYc63FdMd/view?usp=sharing
