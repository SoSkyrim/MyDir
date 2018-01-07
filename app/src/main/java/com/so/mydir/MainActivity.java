package com.so.mydir;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.so.mydir.utils.PermissionsUtil.verifyStoragePermissions;

public class MainActivity extends AppCompatActivity {

    private EditText et_input_path;
    private String TAG = "sorrower";
    private String mFileDir;
    private String mFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //6.0适配
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
     * @param v select按钮
     */
    public void SelectFile(View v) {
        Intent intent = new Intent(this, SelectFileActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            ArrayList<String> dirAndFile = data.getStringArrayListExtra("dirAndFile");

            mFileDir = dirAndFile.get(0);
            mFilePath = dirAndFile.get(1);
//            //确认返回的内容
//            StringBuffer buffer = new StringBuffer();
//            for (int i = 0; i < dirAndFile.size(); i++) {
//                buffer.append(dirAndFile.get(i)).append("---");
//            }
//            String tmpStr = buffer.toString();
//            Log.i(TAG, tmpStr);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 授予结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "成功获取权限");
                } else {
                    Toast.makeText(this, "拒绝权限, 将无法使用程序.", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
        }
    }
}
