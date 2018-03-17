package com.odoo.Etudiant;

import android.content.Context;
import android.net.Uri;

import com.odoo.base.addons.res.ResPartner;
import com.odoo.core.orm.ODataRow;
import com.odoo.core.orm.OModel;
import com.odoo.core.orm.OValues;
import com.odoo.core.orm.annotation.Odoo;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.ODateTime;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.orm.fields.types.OVarchar;
import com.odoo.core.support.OUser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aymen on 08/03/2018.
 */

public class Sanction extends OModel {

    public static final String TAG = Sanction.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.Etudiant";
    OColumn message = new OColumn("message", OText.class).setRequired();;
    OColumn Date = new OColumn("Date", ODateTime.class).setRequired();;
    OColumn type_id= new OColumn("type_id", typee.class, OColumn.RelationType.ManyToOne);
    OColumn name= new OColumn("name", OVarchar.class).setSize(100);
    OColumn student_id= new OColumn("name", ResPartner.class, OColumn.RelationType.ManyToOne);
    OColumn class_id= new OColumn("class_id", Classes.class, OColumn.RelationType.ManyToOne);





    public Sanction(Context context, OUser user) {
        super(context,"proschool.sanction", user);
    //    setHasMailChatter(true);

    }






    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
