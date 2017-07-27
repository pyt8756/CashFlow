package com.pyt.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.pyt.mylibrary.R;

import java.util.concurrent.ExecutionException;

import static com.pyt.mylibrary.BaseApplication.getApplication;


/**
 * 图片加载工具类
 * Created by pengyutao on 16/4/28.
 */
public class ImageUtils {

    public static final String IMAGE_BASE_URL = "http://img.haoq360.com/";
    //默认图片
    private static final int DEFAULT_DRAWABLE = R.drawable.default_image_load;
    /**
     * 图片宽高 30*30
     */
    public static final String TYPE_30 = "@!0.9k";

    /**
     * 图片宽高 60*60
     */
    public static final String TYPE_60 = "@!3.6k";
    /**
     * 图片宽高 90*90
     */
    public static final String TYPE_90 = "@!8.1k";
    /**
     * 图片宽高 100*100
     */
    public static final String TYPE_100 = "@!10k";
    /**
     * 图片宽高 120*120
     */
    public static final String TYPE_120 = "@!14.4k";
    /**
     * 图片宽高 180*180
     */
    public static final String TYPE_180 = "@!32.4k";
    ;
    /**
     * 图片宽高 200*200
     */
    public static final String TYPE_200 = "@!40k";
    /**
     * 图片宽高 600*400
     */
    public static final String TYPE_400 = "@!240k";
    /**
     * 图片原宽高
     */
    public static final String TYPE_RAW = "";
    public static final String TYPE_DD = "@!dd";

    private RequestManager manager;
    private ImageLoadListener listener;
    private String imgUrl;
    private DrawableRequestBuilder<String> builder;

    private ImageUtils(Context context) {
        manager = Glide.with(context);
    }

    private ImageUtils(Activity activity) {
        manager = Glide.with(activity);
    }

    private ImageUtils(FragmentActivity activity) {
        manager = Glide.with(activity);
    }

    private ImageUtils(android.app.Fragment fragment) {
        manager = Glide.with(fragment);
    }

    private ImageUtils(Fragment fragment) {
        manager = Glide.with(fragment);
    }

    public static ImageUtils with(Context context) {
        return new ImageUtils(context);
    }

    public static ImageUtils with(Activity activity) {
        return new ImageUtils(activity);
    }

    public static ImageUtils with(FragmentActivity activity) {
        return new ImageUtils(activity);
    }

    public static ImageUtils with(android.app.Fragment fragment) {
        return new ImageUtils(fragment);
    }

    public static ImageUtils with(Fragment fragment) {
        return new ImageUtils(fragment);
    }

    public static ImageUtils load(String url) {
        return new ImageUtils(getApplication()).mload(url);
    }

    public static ImageUtils load(String url, String size) {
        return new ImageUtils(getApplication()).mload(url, size);
    }

    public ImageUtils mload(String url) {
        return mload(url, TYPE_RAW);
    }

    public ImageUtils mload(String url, String size) {
        this.imgUrl = getImageUrl(url, size);
        builder = manager.load(imgUrl)
                .placeholder(DEFAULT_DRAWABLE)
                .error(DEFAULT_DRAWABLE)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        return this;
    }

    /**
     * 获取bitmap
     */
    public static Bitmap getBitmap(String url, int width, int height) {
        try {
            return Glide.with(getApplication()).load(url).asBitmap().into(width, height).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 清除内存缓存：
     * 必须在UI线程中调用
     */
    public static void clearMemory() {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplication()).clearMemory();
            }
        });
    }

    /**
     * 清除磁盘缓存：
     * 必须在后台线程中调用
     */
    public static void clearDiskCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplication()).clearDiskCache();
            }
        }).start();
    }

    /**
     * 设置资源将被加载到的ImageView。取消该ImageView之前所有的加载并释放资源。
     */
    public ImageUtils into(final ImageView imageView) {
        builder.into(new GlideDrawableImageViewTarget(imageView) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
                if (listener != null) {
                    listener.onLoadStarted(imgUrl, imageView);
                }
            }

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                super.onResourceReady(resource, animation);
                if (listener != null) {
                    listener.onLoadComplete(imgUrl, imageView);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (listener != null) {
                    listener.onLoadFailed(imgUrl, imageView);
                }
            }
        });
        return this;
    }

    public ImageUtils listener(ImageLoadListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 占位图
     */
    public ImageUtils placeholder(int resourceId) {
        builder.placeholder(resourceId);
        return this;
    }

    /**
     * 占位图
     */
    public ImageUtils placeholder(Drawable drawable) {
        builder.placeholder(drawable);
        return this;
    }

    /**
     * 错误图
     */
    public ImageUtils error(int resourceId) {
        builder.error(resourceId);
        return this;
    }

    /**
     * 错误图
     */
    public ImageUtils error(Drawable drawable) {
        builder.error(drawable);
        return this;
    }

    @SafeVarargs
    public final ImageUtils bitmapTransform(Transformation<Bitmap>... bitmapTransformations) {
        if (bitmapTransformations.length != 0) {
            builder.bitmapTransform(bitmapTransformations);
        }
        return this;
    }

    /**
     * 请求给定系数的缩略图。如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示。系数sizeMultiplier必须在(0,1)之间
     */
    public ImageUtils thumbnail(float sizeMultiplier) {
        builder.thumbnail(sizeMultiplier);
        return this;
    }

    /**
     * 设置缓存策略。
     * DiskCacheStrategy.SOURCE：缓存原始数据，
     * DiskCacheStrategy.RESULT：缓存变换后的资源数据，
     * DiskCacheStrategy.NONE：什么都不缓存，
     * DiskCacheStrategy.ALL：缓存SOURCE和RESULT。
     */
    public ImageUtils diskCacheStrategy(DiskCacheStrategy strategy) {
        builder.diskCacheStrategy(strategy);
        return this;
    }

    /**
     * 指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。
     * Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW。默认为Priority.NORMAL
     */
    public ImageUtils priority(Priority priority) {
        builder.priority(priority);
        return this;
    }

    /**
     * 移除所有的动画。
     */
    public ImageUtils dontAnimate() {
        builder.dontAnimate();
        return this;
    }

    /**
     * 在异步加载资源完成时会执行该动画。
     */
    public ImageUtils animate(int animationId) {
        builder.animate(animationId);
        return this;
    }

    /**
     * 在异步加载资源完成时会执行该动画。
     */
    public ImageUtils animate(ViewPropertyAnimation.Animator animator) {
        builder.animate(animator);
        return this;
    }

    /**
     * 设置model为空时要显示的Drawable。
     * 如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
     */
    public ImageUtils fallback(int resourceId) {
        builder.fallback(resourceId);
        return this;
    }

    /**
     * 设置model为空时要显示的Drawable。
     * 如果没设置fallback，model为空时将显示error的Drawable，如果error的Drawable也没设置，就显示placeholder的Drawable。
     */
    public ImageUtils fallback(Drawable drawable) {
        builder.fallback(drawable);
        return this;
    }

    /**
     * 重新设置Target的宽高值（单位为pixel）
     */
    public ImageUtils override(int width, int height) {
        builder.override(width, height);
        return this;
    }

    private synchronized static String getImageUrl(String imgUrl, String size) {
        if (StringUtils.isEmpty(imgUrl)) {
            return "";
        }
        if (imgUrl.toLowerCase().startsWith("http") || imgUrl.startsWith("/storage") || imgUrl.startsWith("/data")) {
            return imgUrl;
        }
        return IMAGE_BASE_URL + imgUrl + (StringUtils.isEmpty(size) ? "" : size);
    }

    public interface ImageLoadListener {

        void onLoadStarted(String url, ImageView imageView);

        void onLoadComplete(String url, ImageView imageView);

        void onLoadFailed(String url, ImageView imageView);

    }

}
