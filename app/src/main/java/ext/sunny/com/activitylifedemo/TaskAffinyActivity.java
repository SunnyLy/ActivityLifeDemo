package ext.sunny.com.activitylifedemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

/**
 * @Annotation <p>描述</p>
 * @Auth Sunny
 * @date 2020/6/14
 * @Version V1.0.0
 */
public class TaskAffinyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_affiny);

        Intent intent = getIntent();
        try {
            String stringExtra = intent.getStringExtra("test");
            Log.d("test","result:"+stringExtra);
        }catch (Exception e){
            e.printStackTrace();
        }

        Serializable serializable = intent.getSerializableExtra("serializableExtra");
        Log.d("test","serializableExtra result:"+serializable);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
