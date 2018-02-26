package com.example.a.windowfloatoverstatusbar.model;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.example.a.windowfloatoverstatusbar.R;

/**
 * Created by 01013000 on 2018/02/01.
 */

public class FloatWindow {

  private WindowManager windowManager;
  private static FloatWindow instance;
  private Context context;
  private WindowManager.LayoutParams wmParams;
  private View view;

  public static FloatWindow getInstance(Context context) {
    //if (instance == null) {
    instance = new FloatWindow(context);
    //}
    return instance;
  }

  private FloatWindow(Context context) {
    this.context = context;
    windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
    wmParams = new WindowManager.LayoutParams();
    wmParams.gravity = Gravity.TOP | Gravity.START;
    wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
    wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
    wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
    wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
    wmParams.format = PixelFormat.TRANSLUCENT;
    wmParams.x = 1300;
  }

  /**
   * add
   */
  private boolean addView(View view) {
    try {
      if (this.view != null) {
        windowManager.removeView(this.view);
      }
      windowManager.addView(view, wmParams);
      this.view = view;
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void show(Context context, final FloatWindowCallback callback) {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.float_view, null);
    TextView textView = (TextView) view.findViewById(R.id.textView1);
    textView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Log.v("float", "click");
        if (callback != null) {
          callback.onclick();
        }
      }
    });
    getInstance(context).addView(view);
    Animation a = AnimationUtils.loadAnimation(context, R.anim.right_in);
    textView.startAnimation(a);
  }

  public interface FloatWindowCallback {
    void onclick();
  }
}
