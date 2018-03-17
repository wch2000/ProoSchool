package com.odoo.Etudiant;

 import android.content.Context;
 import android.content.Intent;
 import android.database.Cursor;
 import android.graphics.Bitmap;
 import android.os.Bundle;
 import android.support.v4.app.LoaderManager;
 import android.support.v4.content.CursorLoader;
 import android.support.v4.content.Loader;
 import android.support.v4.widget.SwipeRefreshLayout;
 import android.view.LayoutInflater;
 import android.view.MenuItem;
 import android.view.View;
import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ListView;
 import android.widget.Toast;

 import com.odoo.R;
 import com.odoo.addons.customers.CustomerDetails;
 import com.odoo.base.addons.res.ResPartner;
 import com.odoo.core.orm.ODataRow;
 import com.odoo.core.orm.fields.OColumn;
 import com.odoo.core.support.addons.fragment.BaseFragment;
 import com.odoo.core.support.addons.fragment.ISyncStatusObserverListener;
 import com.odoo.core.support.drawer.ODrawerItem;
import com.odoo.core.support.list.OCursorListAdapter;
 import com.odoo.core.utils.BitmapUtils;
 import com.odoo.core.utils.IntentUtils;
 import com.odoo.core.utils.OControls;
 import com.odoo.core.utils.OCursorUtils;

 import org.json.JSONArray;
 import org.json.JSONException;
 import org.json.JSONObject;

 import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aymen on 07/03/2018.
 */

public class Student extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> , SwipeRefreshLayout.OnRefreshListener,ISyncStatusObserverListener, OCursorListAdapter.OnViewBindListener ,AdapterView.OnItemClickListener  {
    public static final String TAG = Student.class.getSimpleName();
    private View mView;
    private ListView listView;
    private OCursorListAdapter listAdapter;
    private boolean syncRequested = false;
    private OCursorListAdapter mAdapter = null;
    Context context;

    static String name2;
    @Override
    public List<ODrawerItem> drawerMenus(Context context) {
        List<ODrawerItem> menu = new ArrayList<>();
        menu.add(new ODrawerItem(TAG).setTitle("Sanction and discipline")
                .setInstance(new Student())
                .setIcon(R.drawable.ic_action_customers));

        return menu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_listview, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        context= getContext();
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        listView = (ListView) mView.findViewById(R.id.listview);
        listAdapter = new OCursorListAdapter(getActivity(), null, R.layout.customer_row_item);
        listView.setAdapter(listAdapter);
        listAdapter.setOnViewBindListener(this);
        listView.setOnItemClickListener(this);

        setHasSyncStatusObserver(TAG, this, db());
        getLoaderManager().initLoader(0, null, this);
        listView.setFastScrollAlwaysVisible(true);

    }

    @Override
    public Class<Sanction> database() {
        return Sanction.class;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(), db().uri(), null, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        listAdapter.changeCursor(data);
        if (data.getCount() > 0) {
            OControls.setGone(mView, R.id.loadingProgress);
            OControls.setVisible(mView, R.id.swipe_container);
            OControls.setGone(mView, R.id.data_list_no_item);
            setHasSwipeRefreshView(mView, R.id.swipe_container, this);

        } else {
            OControls.setGone(mView, R.id.loadingProgress);
            OControls.setGone(mView, R.id.swipe_container);
            OControls.setVisible(mView, R.id.data_list_no_item);
            OControls.setText(mView, R.id.title, "No Sanction  found");
            OControls.setText(mView, R.id.subTitle, "Swipe to check new Sanction");
        }
        if (db().isEmptyTable() && !syncRequested) {
            syncRequested = true;
            onRefresh();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        listAdapter.changeCursor(null);

    }

    @Override
    public void onRefresh() {
        if (inNetwork()) {
            parent().sync().requestSync(Sanction.AUTHORITY);
        }
    }

    @Override
    public void onStatusChange(Boolean changed) {
        if(changed){
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    @Override
    public void onViewBind(View view, Cursor cursor, ODataRow row) {


        OControls.setText(view, R.id.name, row.getString("name"));
        OControls.setText(view, R.id.email, row.getString("message"));
        //ODataRow resPartnerRow = row.getM2ORecord("type_id").browse();
        //final String name2 = cursor.getString(cursor.getColumnIndex("student_id"));
         //OControls.setText(view, R.id.company_name, name2);
       // OControls.setText(view, R.id.company_name, row.getString("student_id"));

      //  ODataRow parent_id = row.getM2ORecord("parent_id").browse();

        ResPartner partner = new ResPartner(context, null);
        ODataRow rows = partner.select(
                new String[]{"name"}
        ).get(row.getInt("student_id")-1);

            OControls.setText(view, R.id.company_name, rows.getString("name"));



       /* ODataRow row_part = partner.browse(new String[]{"name","student_id"}, // Projection
                "student_id = ?", // Selection
                new String[]{row.getString("student_id")} // Selection arguments
        );
*/
     //   OControls.setText(view, R.id.company_name, row_part.getString("name"));
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Intent intent;
        intent =  new Intent(view.getContext(), DetailsSanction.class);
        Bundle extras = new Bundle();
        extras.putString("id", "aymen");
        extras.putString("city", "zairi");

        intent.putExtras(extras);
        startActivity(intent);



        view.getContext().startActivity(intent);

        Toast.makeText(view.getContext(), "id"+position, Toast.LENGTH_SHORT).show();

    }
}
