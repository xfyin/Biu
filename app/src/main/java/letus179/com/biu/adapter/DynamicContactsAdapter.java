package letus179.com.biu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import letus179.com.biu.R;
import letus179.com.biu.bean.Contacts;


/**
 * Created by xfyin on 2017/10/14.
 * <p>
 * 联系人列表 适配器
 */

public class DynamicContactsAdapter extends BaseAdapter {
    private List<Contacts> contactsList;
    private LayoutInflater inflater;

    public DynamicContactsAdapter(Context context, List<Contacts> contactsList) {
        inflater = LayoutInflater.from(context);
        this.contactsList = contactsList;
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.dynamic_contacts_item, null);
            holder.dynamic_contacts_classify = (TextView) convertView.findViewById(R.id.dynamic_contacts_classify);
            holder.dynamic_contacts_name = (TextView) convertView.findViewById(R.id.dynamic_contacts_name);
            holder.dynamic_contacts_img = (ImageView) convertView.findViewById(R.id.dynamic_contacts_img);
            holder.dynamic_contacts_check = (CheckBox) convertView.findViewById(R.id.dynamic_contacts_check);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contacts contacts = contactsList.get(position);
        String word = contacts.getHeaderWord();
        holder.dynamic_contacts_classify.setText(word);
        holder.dynamic_contacts_name.setText(contacts.getName());
        holder.dynamic_contacts_img.setImageResource(contacts.getImageId());
        holder.dynamic_contacts_img.setTag(contacts.getImageId());
        holder.dynamic_contacts_check.setChecked(contacts.isChecked());

        //将相同字母开头的合并在一起
        if (position == 0) {
            //第一个是一定显示的
            holder.dynamic_contacts_classify.setVisibility(View.VISIBLE);
        } else {
            //后一个与前一个对比,判断首字母是否相同，相同则隐藏
            String headerWord = contactsList.get(position - 1).getHeaderWord();
            if (word.equals(headerWord)) {
                holder.dynamic_contacts_classify.setVisibility(View.GONE);
            } else {
                holder.dynamic_contacts_classify.setVisibility(View.VISIBLE);
            }
        }
        return convertView;
    }


    private class ViewHolder {
        private TextView dynamic_contacts_classify;
        private TextView dynamic_contacts_name;
        private CheckBox dynamic_contacts_check;
        private ImageView dynamic_contacts_img;
    }
}
