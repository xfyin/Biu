package letus179.com.biu;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;

import letus179.com.biu.common.BasicActivity;
import letus179.com.biu.navigation.FootTabDb;

public class MainActivity extends BasicActivity implements OnTabChangeListener {

	private FragmentTabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		//初始化FragmentTabHost
		initHost();
		//初始化底部导航栏
		initTab();
		//默认选中
		mTabHost.onTabChanged(FootTabDb.getTabsTxt()[0]);
	}


	private void initTab() {
		String[] tabs = FootTabDb.getTabsTxt();
		for (int i = 0; i < tabs.length; i++) {
			//新建TabSpec
			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(FootTabDb.getTabsTxt()[i]);
			//设置view
			View view = LayoutInflater.from(this).inflate(R.layout.tab_foot_item, null);
			((ImageView) view.findViewById(R.id.foot_iv)).setImageResource(FootTabDb.getTabsImg()[i]);
			((TextView) view.findViewById(R.id.foot_tv)).setText(FootTabDb.getTabsTxt()[i]);
			tabSpec.setIndicator(view);
			//加入TabSpec
			mTabHost.addTab(tabSpec, FootTabDb.getFragment()[i], null);
		}
	}

	private void initHost() {
		mTabHost = (FragmentTabHost) findViewById(R.id.main_tab);
		//调用setup方法 设置view
		mTabHost.setup(this, getSupportFragmentManager(), R.id.main_view);
		//去除分割线
		mTabHost.getTabWidget().setDividerDrawable(null);
		//监听事件
		mTabHost.setOnTabChangedListener(this);
	}


	@Override
	public void onTabChanged(String arg0) {
		//从分割线中获得多少个切换界面
		TabWidget tabWidget = mTabHost.getTabWidget();
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			View v = tabWidget.getChildAt(i);
			TextView tv = (TextView) v.findViewById(R.id.foot_tv); 
			ImageView iv = (ImageView) v.findViewById(R.id.foot_iv);
			//修改当前的界面按钮颜色图片
			if (i == mTabHost.getCurrentTab()) {
				tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.tab_light_color , null));
				iv.setImageResource(FootTabDb.getTabsImgLight()[i]);
			}else{
				tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.tab_color , null));
				iv.setImageResource(FootTabDb.getTabsImg()[i]);
			}
		}
	}
}
