package com.so.mydir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.so.mydir.utils.PermissionsUtil.verifyStoragePermissions;

public class MainActivity extends AppCompatActivity {

    private EditText et_input_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //6.0适配
//        if (Build.VERSION.SDK_INT == 23) {
//            int REQUEST_CODE_CONTACT = 101;
//            String[] permissions = {
//                    "android.permission.READ_EXTERNAL_STORAGE",
//                    "android.permission.WRITE_EXTERNAL_STORAGE",
//                    "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"};
//
//            //验证是否许可权限
//            for (String str : permissions) {
//                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
//                    //申请权限
//                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
//                }
//            }
//        }

        //23+权限获取
        verifyStoragePermissions(this);

        et_input_path = (EditText) findViewById(R.id.et_input_path);
    }

    /**
     * 选择一个文件, 返回其绝对路径
     *
     * @param v
     */
    public void SelectFile(View v) {
        Intent intent = new Intent(this, SelectFileActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            String path = data.getStringExtra("path");
            Toast.makeText(getApplicationContext(),
                    path, Toast.LENGTH_SHORT).show();
            et_input_path.setText(path);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
