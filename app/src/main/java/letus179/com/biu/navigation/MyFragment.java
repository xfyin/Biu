package letus179.com.biu.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import letus179.com.biu.R;

public class MyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.my_fragment, container,false);
		((TextView) view.findViewById(R.id.fm_text)).setText(FootTabDb.getTabsTxt()[3]);
		return view;
	}
}
