#App 介绍

Gank.io  这个网站收集了许多前沿精品的Android 学习文章并提供了Api 接口供人使用。本app 内容来源于http://www.gank.io/api

欢迎Star       -  -

Gank.io 这个App 采用了Material  风格。
- 主界面采用 CoordingLayout 和AppBarLayout和Tablayout 和viewPager
- 三种RecyclerView 的布局：线性，网格，瀑布流。
- 图片加载是用的是picasso ，真的好用。
- 网络框架使用的是okHttp 加Retrofit。
- RecyclerView 的item 布局使用的是cardView

效果如下：
![这里写图片描述](https://github.com/v1210012100/MyImages/blob/master/GIF.gif)

##内容显示界面

内容显示界面是头部AppBarLayout 加CollapsingToolbarLayout实现可伸缩效果。内容部分是 WebView。

- 三种Material 的Activity 切换动画。共享元素，滑动，渐隐。

效果图：
![这里写图片描述](https://github.com/v1210012100/MyImages/blob/master/GIF11.gif)

##踩过的坑

- 实现 CoordinatorLayout 和WebView 的双层滑动。要在WebView 外面嵌套一层NestedScrollView。
- Android Fragment  getActivity()空指针问题http://www.jianshu.com/p/d9143a92ad94
- SwipeRefreshLayout 调用 setRefreshing(true) 不显示。
http://stackoverflow.com/questions/26858692/swiperefreshlayout-setrefreshing-not-showing-indicator-initially

#后话
今天是母亲节，祝天下母亲都身体健康，开心快乐。
