package com.pyt.mylibrary.utils;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.pyt.mylibrary.view.spannable.CircleMovementMethod;
import com.pyt.mylibrary.view.spannable.SpannableClickable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewUtils {

    public enum InterceptType {
        none,//不拦截
        external_vertical_down, //外部拦截竖向下拉
        external_vertical_up, //外部拦截竖向上拉
        external_horizontal, //外部拦截横向
    }

    /**
     * view 模拟一次点击事件
     */
    public static void click(View view) {
        final long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(
                downTime, downTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
        final MotionEvent upEvent = MotionEvent.obtain(
                downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
    }

    public static void move(View view) {
        final long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(
                downTime, downTime, MotionEvent.ACTION_DOWN, 0, 0, 0);
        final MotionEvent moveEvent = MotionEvent.obtain(
                downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_MOVE, 0, -5, 0);
        final MotionEvent upEvent = MotionEvent.obtain(
                downTime, SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, -5, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(moveEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        moveEvent.recycle();
        upEvent.recycle();
    }

    /**
     * 点击返回键
     */
    public static void backClick() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }
        }).start();
    }

    /**
     * 设置textView可以点击URL和电话号码
     */
    public static void formatUrlStringToTextView(TextView textView, String contentStr, final TextViewUrlListener listener) {
        if (textView != null) {
            textView.setText(formatUrlString(contentStr, listener));
            textView.setMovementMethod(new CircleMovementMethod());
        }
    }

    public static SpannableStringBuilder formatUrlString(String contentStr, final TextViewUrlListener listener) {
        SpannableStringBuilder sp;
        if (!TextUtils.isEmpty(contentStr)) {
            sp = new SpannableStringBuilder(contentStr);
            try {
                //处理url匹配
                Pattern urlPattern = Pattern.compile("(http|https|ftp|svn)://([a-zA-Z0-9]+[/?.?])" +
                        "+[a-zA-Z0-9]*\\??([a-zA-Z0-9]*=[a-zA-Z0-9]*&?)*");
                Matcher urlMatcher = urlPattern.matcher(contentStr);
                while (urlMatcher.find()) {
                    final String url = urlMatcher.group();
                    if (!TextUtils.isEmpty(url)) {
                        sp.setSpan(new SpannableClickable() {
                            @Override
                            public void onClick(View widget) {
                                if (listener != null) {
                                    listener.onUrlClick(url);
                                }
                            }
                        }, urlMatcher.start(), urlMatcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
                //处理电话匹配
                Pattern phonePattern = Pattern.compile("[1][34578][0-9]{9}");
                Matcher phoneMatcher = phonePattern.matcher(contentStr);
                while (phoneMatcher.find()) {
                    final String phone = phoneMatcher.group();
                    if (!TextUtils.isEmpty(phone)) {
                        sp.setSpan(new SpannableClickable() {
                            @Override
                            public void onClick(View widget) {
                                Context context = widget.getContext();
                                //用intent启动拨打电话
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        }, phoneMatcher.start(), phoneMatcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sp = new SpannableStringBuilder();
        }
        return sp;
    }

    public interface TextViewUrlListener {
        void onUrlClick(String url);
    }


    public static void scrollviewNestingEditTextScrolling(EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 解决scrollView中嵌套EditText导致不能上下滑动的问题
                if (canVerticalScroll((EditText) v)) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }

    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    private static boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;
        return scrollDifference != 0 && ((scrollY > 0) || (scrollY < scrollDifference - 1));
    }
}
