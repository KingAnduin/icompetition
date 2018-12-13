package greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.thinkpad.icompetition.model.entity.user.UserInforBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INFOR_BEAN".
*/
public class UserInforBeanDao extends AbstractDao<UserInforBean, Long> {

    public static final String TABLENAME = "USER_INFOR_BEAN";

    /**
     * Properties of entity UserInforBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property User_num = new Property(0, long.class, "user_num", true, "_id");
        public final static Property User_birthday = new Property(1, String.class, "user_birthday", false, "USER_BIRTHDAY");
        public final static Property User_headimage = new Property(2, String.class, "user_headimage", false, "USER_HEADIMAGE");
        public final static Property User_interest = new Property(3, String.class, "user_interest", false, "USER_INTEREST");
        public final static Property User_name = new Property(4, String.class, "user_name", false, "USER_NAME");
        public final static Property User_roleid = new Property(5, int.class, "user_roleid", false, "USER_ROLEID");
        public final static Property User_sex = new Property(6, String.class, "user_sex", false, "USER_SEX");
    };


    public UserInforBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserInforBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INFOR_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: user_num
                "\"USER_BIRTHDAY\" TEXT," + // 1: user_birthday
                "\"USER_HEADIMAGE\" TEXT," + // 2: user_headimage
                "\"USER_INTEREST\" TEXT," + // 3: user_interest
                "\"USER_NAME\" TEXT," + // 4: user_name
                "\"USER_ROLEID\" INTEGER NOT NULL ," + // 5: user_roleid
                "\"USER_SEX\" TEXT);"); // 6: user_sex
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INFOR_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInforBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUser_num());
 
        String user_birthday = entity.getUser_birthday();
        if (user_birthday != null) {
            stmt.bindString(2, user_birthday);
        }
 
        String user_headimage = entity.getUser_headimage();
        if (user_headimage != null) {
            stmt.bindString(3, user_headimage);
        }
 
        String user_interest = entity.getUser_interest();
        if (user_interest != null) {
            stmt.bindString(4, user_interest);
        }
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(5, user_name);
        }
        stmt.bindLong(6, entity.getUser_roleid());
 
        String user_sex = entity.getUser_sex();
        if (user_sex != null) {
            stmt.bindString(7, user_sex);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInforBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUser_num());
 
        String user_birthday = entity.getUser_birthday();
        if (user_birthday != null) {
            stmt.bindString(2, user_birthday);
        }
 
        String user_headimage = entity.getUser_headimage();
        if (user_headimage != null) {
            stmt.bindString(3, user_headimage);
        }
 
        String user_interest = entity.getUser_interest();
        if (user_interest != null) {
            stmt.bindString(4, user_interest);
        }
 
        String user_name = entity.getUser_name();
        if (user_name != null) {
            stmt.bindString(5, user_name);
        }
        stmt.bindLong(6, entity.getUser_roleid());
 
        String user_sex = entity.getUser_sex();
        if (user_sex != null) {
            stmt.bindString(7, user_sex);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public UserInforBean readEntity(Cursor cursor, int offset) {
        UserInforBean entity = new UserInforBean( //
            cursor.getLong(offset + 0), // user_num
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // user_birthday
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // user_headimage
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // user_interest
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // user_name
            cursor.getInt(offset + 5), // user_roleid
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // user_sex
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInforBean entity, int offset) {
        entity.setUser_num(cursor.getLong(offset + 0));
        entity.setUser_birthday(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUser_headimage(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUser_interest(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUser_name(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUser_roleid(cursor.getInt(offset + 5));
        entity.setUser_sex(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInforBean entity, long rowId) {
        entity.setUser_num(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInforBean entity) {
        if(entity != null) {
            return entity.getUser_num();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}