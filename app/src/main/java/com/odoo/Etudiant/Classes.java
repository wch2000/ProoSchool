package com.odoo.Etudiant;

import android.content.Context;
import android.net.Uri;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 13/03/2018.
 */

public class Classes extends OModel {
    public static final String AUTHORITY = "com.odoo.Etudiant";

    public static final String TAG = Classes.class.getSimpleName();
    OColumn code = new OColumn("code", OText.class).setRequired();;



    public Classes(Context context,  OUser user) {
        super(context, "proschool.classes", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
