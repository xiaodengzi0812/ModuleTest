绕过manifests的检测，来启动一个没有在manifests中配置的activity

可以在启动activity之前调用 : new HookStartActivityUtil(mActivity, ProxyActivity.class).execute();

也可以在application中配置 : new HookStartActivityUtil(mActivity, ProxyActivity.class).execute();

