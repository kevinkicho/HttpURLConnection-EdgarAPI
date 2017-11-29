package com.example.kevin.httpurlconnectionapp112817;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 11/28/2017.
 */

public class LvArrayAdapter extends android.widget.ArrayAdapter<String> {
    private Context context;
    private List<String> strings;

    HashMap<String,Integer> mIdMap = new HashMap<String, Integer>();
    public LvArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public LvArrayAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public LvArrayAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

    public LvArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public LvArrayAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        for(int i= 0; i< objects.size(); ++i){
            mIdMap.put(objects.get(i),i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}