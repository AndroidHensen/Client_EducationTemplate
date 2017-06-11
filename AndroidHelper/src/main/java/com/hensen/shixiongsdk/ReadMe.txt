1.OKHttp
    1-1.封装Request参数
    1-2.封装Request工厂
    1-3.封装OkHttpClient-支持https、支持重定向
    1-4.自定义Exception、自定义Listener
    1-5.自定义CallBack支持Json自动转换对象

    bug：
    假如有这种情况发生，需要处理一下
    {
        "code": 407,
        "data": false,
        "message": "提交数据为空",
        "pager": 1
    }
    {
        "code": 200,
        "data": {
            "tel_code": 5284
        },
        "message": "短信发送成功!",
        "pager": "1"
    }

2.ImageLoader
    1-1.封装ImageLoader的默认配置
    1-2.封装Image的显示配置
3.Widght
    1-1.自定义BannerView、自定义indication
    1-2.自定义ListView解决ScrollView与ListView嵌套
    1-3.自定义GridView解决ScrollView与ListView嵌套
    1-4.自定义FlowLayout流失布局
4.Utils
    1-1.SizeUtils：dp转化器
    1-2.DateUtils: 字符串时间戳转换成时间
    1-3.PreferenceUtils：配置文件
5.CaptureActivity
    1-1.打开图册、打开闪关灯、生成二维码
6.BaseTemplate
    1-1.BaseActivity
    1-2.BaseFragment
    1-3.BaseLazyFragment
    1-3.BaseApplication
7.Video
    1-1.使用jiecaoplayer作为视频播放源
8.PhotoViewActivity
    1-1.解决ViewPager滑动事件分发报错问题