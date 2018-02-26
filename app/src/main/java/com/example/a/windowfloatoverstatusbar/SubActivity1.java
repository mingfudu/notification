package com.example.a.windowfloatoverstatusbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.a.windowfloatoverstatusbar.model.FloatWindow;

public class SubActivity1 extends Activity {
  @BindView(R.id.content) TextView content;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sub1);
    ButterKnife.bind(this);
  }

  @Override public void onResume() {
    super.onResume();
    FloatWindow.show(this, new FloatWindow.FloatWindowCallback() {
      @Override public void onclick() {
        content.setText(content.getText() + "\n SubActivity1 clicked!");
        FloatWindow.show(SubActivity1.this, new FloatWindow.FloatWindowCallback() {
          @Override public void onclick() {
            content.setText(content.getText() + "\n SubActivity1 clicked!");
          }
        });
      }
    });
  }

  public static Intent getStartIntent(Context context) {
    final Intent intent = new Intent(context, SubActivity1.class);
    return intent;
  }
}
