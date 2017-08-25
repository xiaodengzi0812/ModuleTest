我们自己搭一个ioc框架

使用：
1、在onCreat()方法中初始化ioc框架
   BindUtil.inject(this);

2、注解属性
    @BindView(R.id.tv1)
    private TextView tv1;
    @BindView(R.id.tv2)
    private TextView tv2;

3、注解点击事件
    @BindClick({R.id.tv1, R.id.tv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                Toast.makeText(IocActivity.this, "click hello1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv2:
                Toast.makeText(IocActivity.this, "click hello2", Toast.LENGTH_SHORT).show();
                break;
        }
    }