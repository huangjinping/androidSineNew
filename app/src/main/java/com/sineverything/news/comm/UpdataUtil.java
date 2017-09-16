package com.sineverything.news.comm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.FileCallBack;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.ReleaseInfo;
import com.sineverything.news.comm.widget.DialogUtils.Effectstype;
import com.sineverything.news.comm.widget.DialogUtils.NiftyDialogBuilder;
import com.sineverything.news.comm.widget.progressbar.IconRoundCornerProgressBar;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author Created by harrishuang on 2017/9/11.
 * email : huangjinping@hdfex.com
 */

public class UpdataUtil {
    private Context mContext;
    private String versionUrl;

    //    private SeekBar mProgressBar;
    private TextView mTextView;
    private IconRoundCornerProgressBar ic_progress;

    public UpdataUtil(Context context) {
        this.mContext = context;

    }

    public UpdataUtil(Context context, String versionUrl) {
        this.mContext = context;
        this.versionUrl = versionUrl;
    }

    /**
     * 自定义Dialog
     * @param context
     * @param uri
     */
    public void showMyDialog(Context context, final String uri, final boolean force) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder
                .withTitle("发现新版本")
                .withTitleColor("#FFFFFF")
//                .withDividerColor("#11000000")
                .withMessage("加入新的功能，修复bug")
                .withMessageColor("#000000")
                .withDialogColor("#FFFFFF")
                .withIcon(context.getResources().getDrawable(R.mipmap.icon))
                .isCancelableOnTouchOutside(true)
                .withDuration(700)
                .withEffect(Effectstype.Shake);

        if (!force) {
            dialogBuilder.withButton1Text("取消");
        }

        dialogBuilder.withButton2Text("立刻更新")
                .isCancelable(false)
                .isCancelableOnTouchOutside(false)
//                .setCustomView(R.layout.custom_view, v.getContext())
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();

                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getSDPath() == null) {
                            showAnimDialog(uri);
                            Uri uris = Uri.parse(uri);
                            Intent it = new Intent(Intent.ACTION_VIEW, uris);
                            mContext.startActivity(it);
                        } else {

                            try {
                                getApp(uri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }





    //SD卡是否存在
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();

    }

    //显示对话框
    private AlertDialog showAnimDialog(String s) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .create();
        dialog.show();


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        dialog.getWindow().setContentView(R.layout.widget_dialog_seekbar);
//        mProgressBar = (SeekBar) dialog.getWindow().findViewById(R.id.progressbar);
        ic_progress = (IconRoundCornerProgressBar) dialog.getWindow().findViewById(R.id.ic_progress);
        ic_progress.setProgressColor(mContext.getResources().getColor(R.color.btn_press_color));
        ic_progress.setSecondaryProgressColor(mContext.getResources().getColor(R.color.btn_unpress_color));
        ic_progress.setIconBackgroundColor(mContext.getResources().getColor(R.color.btn_unpress_color));
        ic_progress.setProgressBackgroundColor(mContext.getResources().getColor(R.color.transparent_quarter));

        ic_progress.setMax(100);
//        mProgressBar.setMax(100);
        mTextView = (TextView) dialog.getWindow().findViewById(R.id.tv_progress);

        dialog.getWindow().findViewById(R.id.tv_download_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        return dialog;
    }

    public void getApp(final String url) throws IOException {
        //TODO 删除测试百度APP地址
        // String urls = "http://p.gdown.baidu.com/560c0095b2cf5705b4dbb5302d7637538516695bd34c37c29e5c30b2e1c104f3d31b4b5ffce20b203498f3285df3e4cbd1d4d02a3f8d5fd91e82a4d2724590df51bed47f4638480e55a0e9fca44123afa4fa2ccf8954f6e83c5cd12becb7485d7b1c9f220ed63d08101ea4712225acc4cd3eb0d91f4e822bc76de78c4dc02b9fc1250aa4f4faf97150e184b97734b2c0a8fdcf156abf30ef19ab83204ee7cc60ae0177a4d9d394115c196cb169674fb76fc479b41a477670e4bf042b21f45a1735b24431eee7a703716540a52c2b3109";
        final AlertDialog dialog = showAnimDialog(url);
        OkHttpUtils.get()
                .url(url)
                .build()
                .writeTimeOut(200000)
                .connTimeOut(200000)
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "fenfujun-shangjiaduan.apk") {

                    @Override
                    public void onBefore(Request request) {

                    }

                    @Override
                    public void inProgress(float progress) {
//                LogUtil.e(UpdataUtil.class.getSimpleName(), String.valueOf((int) (progress * 100)));
//                        mProgressBar.setProgress((int) (progress * 100));
                        ic_progress.setProgress(progress * 100);
                        mTextView.setText(String.format("已下载：%d%% %n", (int) (progress * 100)));
                    }

                    @Override
                    public void onError(Call call, Exception e) {

                        dialog.dismiss();
                        call.cancel();
                        // getApp("d");

                        Toast.makeText(mContext, "下载失败，偿试重新下载！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(File response) {
                        //
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(response),
                                "application/vnd.android.package-archive");
                        mContext.startActivity(intent);


                    }
                });

    }


    /**
     * 判断SD卡是否存在
     *
     * @return
     */
    protected boolean sdcardExist() {
        boolean hasSdCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        return hasSdCard;
    }

    private ReleaseInfo mReleaseInfo;

    public void getVersion() {

        // Execute the request and retrieve the response.
        new Thread(new Runnable() {
            @Override
            public void run() {

                final OkHttpClient client = new OkHttpClient();
                // Create request for remote resource.
                final Request request = new Request.Builder()
                        .url(versionUrl)
                        .build();
                try {
                    Response response = null;
                    response = client.newCall(request).execute();
                    // Deserialize HTTP response to concrete type.
                    ResponseBody body = response.body();
                    Reader charStream = body.charStream();
                    String appString = body.string();
//                    LogUtil.e("UpdataUtil", appString);
                    //   final JSONObject appInfoJson = new JSONObject(appString);

                    try {
                        mReleaseInfo = GsonUtil.changeGsonToBean(appString, ReleaseInfo.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    LogUtil.e("UpdataUtil", mReleaseInfo.toString());
                    PackageManager pm = mContext.getPackageManager();
                    PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
                            PackageManager.GET_CONFIGURATIONS);

                    if (mReleaseInfo != null) {

                        if (mReleaseInfo.getCode() == 0) {
//                            LogUtil.e("UpdataUtil", mReleaseInfo.getResult().getVersionCode() + "线上版本号");
                            //大于
                            if (mReleaseInfo.getResult().getVersionCode() > pi.versionCode) {

                                ((Activity) mContext).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        //是否强制更新
                                        if (mReleaseInfo.getResult().getIsForce() > 0) {
                                            //TODO 修改提示框样式  showDialog(mContext, appInfoJson.getString("URL"));
//                                            showCustomDialog(mContext, mReleaseInfo.getResult().getUrl(), true);
                                            showMyDialog(mContext, mReleaseInfo.getResult().getUrl(), true);
                                        } else {
                                            showMyDialog(mContext, mReleaseInfo.getResult().getUrl(), false);

                                            //TODO 修改提示框样式  showDialog(mContext, appInfoJson.getString("URL"));
//                                            showCustomDialog(mContext, mReleaseInfo.getResult().getUrl(), false);
                                        }
                                    }
                                });
                            }
                        }
                    }
                } catch (IOException e) {
//                    LogUtil.e(UpdataUtil.class.getSimpleName(), e.toString());
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
