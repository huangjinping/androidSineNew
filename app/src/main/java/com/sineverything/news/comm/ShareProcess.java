package com.sineverything.news.comm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.utils.FileUtils;
import com.jaydenxiao.common.utils.ShotUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * author Created by harrishuang on 2017/9/10.
 * email : huangjinping@hdfex.com
 */

public class ShareProcess {


    private BaseActivity activity;

    public ShareProcess(BaseActivity activity) {
        this.activity = activity;
    }

    /**
     * @param context
     * @param bmp
     */
    public void saveImageToGalleryNew(final Context context, final Bitmap bmp) {
        if (bmp == null) {
            activity.showLongToast("保存出错了...");
            return;
        }
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                // 首先保存图片
                File appDir = new File(context.getCacheDir(), "ywq");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                } catch (IOException e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                } catch (Exception e) {
                    subscriber.onError(e);
                    e.printStackTrace();
                }
                // 最后通知图库更新
                try {
                    MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(file);
                intent.setData(uri);
                context.sendBroadcast(intent);
                subscriber.onNext(file);

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                activity.showLongToast("截图处理失败");

            }

            @Override
            public void onNext(File file) {
                activity.showLongToast("保存成功了...");

//                shareImage((Activity) context,file);

                Luban.with(context)
                        .load(file)                     //传人要压缩的图片
                        .setCompressListener(new OnCompressListener() { //设置回调

                            @Override
                            public void onStart() {
                                //  压缩开始前调用，可以在方法内启动 loading UI

                            }

                            @Override
                            public void onSuccess(File file) {
//    压缩成功后调用，返回压缩后的图片文件
                                shareImage((Activity) context, file);


                            }

                            @Override
                            public void onError(Throwable e) {
                                //  当压缩过去出现问题时调用
                            }
                        }).launch();    //启动压缩

            }
        });
    }


    private File savefile;


    public void shotAllView(Context context, LinearLayout layout_rootview, View[] views) {

        if (savefile != null) {
            shareImage((Activity) context, savefile);
            return;
        }
        /**
         * 用户点击的速度特别快的时候会主线程内存溢出；哈哈哈哈
         */
        try {
            Bitmap bitmap = ShotUtils.shotMultiView(layout_rootview, views);
            saveImageToGalleryNew(context, bitmap);

        } catch (Exception e) {
            e.printStackTrace();
            /**
             * 是不是应该提示给用户点啥
             */
        }
    }

    /**
     * 分享Image
     *
     * @param context
     * @param file
     */
    public void shareImage(final Activity context, final File file) {
        savefile = file;
        ShareAction mShareAction = new ShareAction(context).setDisplayList(
                SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.QZONE,SHARE_MEDIA.QQ)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                        SnsPlatform snsPlatform = SHARE_MEDIA.WEIXIN.toSnsPlatform();

                        Log.d("okhttp", file.getAbsolutePath().toString());

                        Bitmap bitmap = FileUtils.getfileBitmap(file.getAbsolutePath());
                        UMImage imagelocal = new UMImage(context, bitmap);
//                        imagelocal.setThumb(new UMImage(context, file));
                        imagelocal.setThumb(new UMImage(context, bitmap));

                        new ShareAction(context).withMedia(imagelocal)
                                .setPlatform(snsPlatform.mPlatform)
                                .setCallback(new UMShareListener() {
                                    @Override
                                    public void onStart(SHARE_MEDIA share_media) {
                                    }

                                    @Override
                                    public void onResult(SHARE_MEDIA share_media) {
                                        activity.showLongToast("分享成功");
                                    }

                                    @Override
                                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                        activity.showLongToast("分享错误");
                                    }

                                    @Override
                                    public void onCancel(SHARE_MEDIA share_media) {
                                        activity.showLongToast("分享取消");

                                    }
                                }).share();
                    }
                });
        mShareAction.open();
    }

}
