package com.odoo.Etudiant;

import android.content.Context;
import android.net.Uri;

import com.odoo.core.orm.OModel;
import com.odoo.core.orm.fields.OColumn;
import com.odoo.core.orm.fields.types.OText;
import com.odoo.core.support.OUser;

/**
 * Created by Aymen on 14/03/2018.
 */

public class typee  extends OModel {
    public static final String TAG = typee.class.getSimpleName();
    public static final String AUTHORITY = "com.odoo.Etudiant";

    public typee(Context context,  OUser user) {
        super(context, "proschool.sanction.type", user);
    }
    @Override
    public Uri uri() {
        return buildURI(AUTHORITY);
    }

}
