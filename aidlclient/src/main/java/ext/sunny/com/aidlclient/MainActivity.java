package ext.sunny.com.aidlclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ext.sunny.com.activitylifedemo.IPerson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnRead;
    private Button mBtnStartService;
    private TextView mTvInfo;

    private ServiceConnection mServiceConn;

    private IPerson iPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRead = findViewById(R.id.btn_aidl_read);
        mBtnStartService = findViewById(R.id.btn_aidl_startservice);
        mTvInfo = findViewById(R.id.tv_aidl_info);

        mServiceConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(MainActivity.this, "服务启动成功", Toast.LENGTH_SHORT).show();
                iPerson = IPerson.Stub.asInterface(service);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                iPerson = null;

            }
        };
        bindRemoteSevice();

        mBtnRead.setOnClickListener(this);
        mBtnStartService.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConn);
    }

    /**
     * 绑定远程服务
     */
    private void bindRemoteSevice() {
        Intent aidlService = new Intent();
        aidlService.setAction("com.ext.sunny.aidl.remoteservice");
//        aidlService.setPackage("ext.sunny.com.activitylifedemo.services");
        aidlService.setPackage("ext.sunny.com.activitylifedemo");
        bindService(aidlService, mServiceConn, Service.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_aidl_read:
                //读数据
                if (iPerson != null) {
                    try {
                        String info = iPerson.getName();
                        mTvInfo.setText("AIDL远程服务返回数据如下：\n" + info);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_aidl_startservice:
                //启动服务
                bindRemoteSevice();
                break;
        }

    }
}
