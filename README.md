

timberextended
----------------
[**中文文档**](http://www.jianshu.com/p/d4fb5d0fa26e)

extra useful log classes for [timber]
* [ConstTagTree](#consttagtree): with a const tag when set.
* [ReleaseTree](#releasetree): log version for release mode.
* [FileTree](#filetree): log message inot a file.
* [MultiTagTree](#multitagtree): enable multi tag funcation.



## ConstTagTree
Usage:
```java
Timber.plant(new ConstTagTree().setTag("ConstTag"));
Timber.v("watch the tag"); //tag == "ConstTag"
Timber.v("watch the tag haha");//tag == "ConstTag"
Timber.v("watch the tag hehe");//tag == "ConstTag"
```


ReleaseTree
------------
Usage:
```java
Timber.plant(new ReleaseTree());
Timber.v("release log test: Timber.v"); //do not acutal log.
Timber.i("release log test: Timber.i"); //do not acutal log.
Timber.d("release log test: Timber.d"); //do not acutal log.
Timber.w("release log test: Timber.w"); //do log.
Timber.e("release log test: Timber.e"); //do log.
Timber.wtf("release log test: Timber.wtf"); //do log.
```

FileTree
--------
Usage:
```java
String path = Environment.getExternalStorageDirectory().getAbsolutePath();
Timber.plant(new FileTree().name("FileTree.txt").storeAt(path));
Timber.v("log in file"); //this will be store in file
```


## MultiTagTree
Usage:
```java
Timber.plant(new MultiTagTree().addTag("Whyn111")); //then the tag is like "Whyn111-MainActivity"
Timber.v("watch the tag");
```

[timber]:https://github.com/JakeWharton/timber
