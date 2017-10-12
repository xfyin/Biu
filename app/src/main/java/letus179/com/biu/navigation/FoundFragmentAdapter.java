package letus179.com.biu.navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FoundFragmentAdapter extends FragmentPagerAdapter {

	private List<Fragment> fmList;

	public FoundFragmentAdapter(FragmentManager fm, List<Fragment> fmList) {
		super(fm);
		this.fmList = fmList;
	}

	@Override
	public Fragment getItem(int arg0) {
		return fmList.get(arg0%fmList.size());
	}

	@Override
	public int getCount() {
		return fmList.size();
	}
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}
}
