package letus179.com.biu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import letus179.com.biu.R;
import letus179.com.biu.bean.Contacts;

public class HorizontalListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int selectIndex = -1;

    // 选中的需要@的人
    private List<Contacts> checkedContactList;

    public HorizontalListViewAdapter(Context context, List<Contacts> checkedContactList) {
        this.mContext = context;
        this.checkedContactList = checkedContactList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return checkedContactList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contacts contacts = checkedContactList.get(position);


        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.dynamic_horizontal_list_item, null);
            holder.dynamic_horizontal_list_item_img = (ImageView) convertView.findViewById(R.id.dynamic_horizontal_list_item_img);
            holder.dynamic_horizontal_list_item_name = (TextView) convertView.findViewById(R.id.dynamic_horizontal_list_item_name);
            holder.dynamic_horizontal_list_item_delete = (TextView) convertView.findViewById(R.id.dynamic_horizontal_list_item_delete);
            holder.dynamic_horizontal_list_item_delete.bringToFront();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == selectIndex) {
            convertView.setSelected(true);
        } else {
            convertView.setSelected(false);
        }

        holder.dynamic_horizontal_list_item_name.setText(contacts.getName());
        holder.dynamic_horizontal_list_item_img.setImageResource(contacts.getImageId());

        return convertView;
    }

    private static class ViewHolder {
        private TextView dynamic_horizontal_list_item_name;
        private ImageView dynamic_horizontal_list_item_img;
        private TextView dynamic_horizontal_list_item_delete;
    }

    public void setSelectIndex(int i) {
        selectIndex = i;
    }
}