package wc.xulingyun.com.xly.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.IndexableLayout;


public class ContactsFragment extends Fragment {

    IndexableLayout mIndexableLayout;
    List<ContactsEntity> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        mIndexableLayout = (IndexableLayout) view.findViewById(R.id.indexable_layout);
        initDatas();
        ContactsAdapter lContactsAdapter = new ContactsAdapter(getActivity());
        mIndexableLayout.setAdapter(lContactsAdapter);
        lContactsAdapter.setDatas(list);
        return view;
    }



    private List<ContactsEntity> initDatas() {
        list = new ArrayList<>();
        // 初始化数据
        List<String> contactStrings = Arrays.asList(getResources().getStringArray(R.array.contact_array));
        List<String> mobileStrings = Arrays.asList(getResources().getStringArray(R.array.mobile_array));
        for (int i = 0; i < contactStrings.size(); i++) {
            ContactsEntity contactEntity = new ContactsEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }

}
