package projeto.integrador.univeps.franco.da.rocha.grupo4n6.a2020;

import android.app.Application;

import com.developer.base.utils.lib.tool.BaseDevice;

public class MyApp extends Application {
    public MyApp() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BaseDevice.getApp(this);
    }
}
