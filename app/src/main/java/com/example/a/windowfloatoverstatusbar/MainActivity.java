package com.example.a.windowfloatoverstatusbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.a.windowfloatoverstatusbar.model.FloatWindow;

public class MainActivity extends Activity {
  @BindView(R.id.content) TextView content;
  @BindView(R.id.button) Button button;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        MainActivity.this.startActivity(SubActivity1.getStartIntent(MainActivity.this));
      }
    });
  }

  @Override public void onResume() {
    super.onResume();
    FloatWindow.show(this, new FloatWindow.FloatWindowCallback() {
      @Override public void onclick() {
        content.setText(content.getText() + "\n clicked!");
      }
    });
  }

}
