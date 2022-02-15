package com.testmbsdk;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.mindboxsdk.MindboxJsDelivery;

import cloud.mindbox.mobile_sdk.Mindbox;

public class MainActivity extends ReactActivity {

  private MindboxJsDelivery mJsDelivery;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ReactInstanceManager mReactInstanceManager = getReactNativeHost().getReactInstanceManager();

    mReactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
      @Override
      public void onReactContextInitialized(ReactContext context) {
        Intent intent = context.getCurrentActivity().getIntent();

        Mindbox.INSTANCE.onNewIntent(intent);
        Mindbox.INSTANCE.onPushClicked(context, intent);

        mJsDelivery = MindboxJsDelivery.Shared.getInstance(context);
        mJsDelivery.sendPushClicked(context.getCurrentActivity().getIntent());

        mReactInstanceManager.removeReactInstanceEventListener(this);
      }
    });
  }

  @Override
  public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);

    Mindbox.INSTANCE.onNewIntent(intent);
    Mindbox.INSTANCE.onPushClicked(this, intent);

    mJsDelivery.sendPushClicked(intent);
  }

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */


  @Override
  protected String getMainComponentName() {
    return "testMbSdk";
  }
}
