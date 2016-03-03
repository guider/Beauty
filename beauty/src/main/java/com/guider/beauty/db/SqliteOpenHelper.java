package com.guider.beauty.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.guider.beauty.bean.RowImage;
import com.guider.beauty.utils.L;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class SqliteOpenHelper extends OrmLiteSqliteOpenHelper {
	private final String TAG = this.getClass().getSimpleName();
	private static SqliteOpenHelper instance;

	/* 在此声明实体类 */
	private Class<?>[] classes = { RowImage.class };

	public SqliteOpenHelper(Context context) {
		super(context, DBConfig.getFilePath(), null, DBConfig.DATABASE.VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			for (Class<?> clazz : classes) {
				TableUtils.createTableIfNotExists(connectionSource, clazz);
			}
		} catch (SQLException e) {
			L.i(TAG, " 建立数据库失败！" + e.toString());
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVer, int newVer) {
		try {
			for (Class<?> clazz : classes) {
				TableUtils.dropTable(connectionSource, clazz, true);
			}
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			L.i(TAG, "Unable to upgrade database from version " + oldVer
					+ " to new " + newVer);
		}
	}

	// /**
	// * 单例获取该Helper
	// *
	// * @param context
	// * @return
	// */
	// public static synchronized SqliteOpenHelper getHelper(Context context) {
	// context = context.getApplicationContext();
	// if (instance == null) {
	// synchronized (SqliteOpenHelper.class) {
	// if (instance == null)
	// instance = new SqliteOpenHelper(context);
	// }
	// }
	//
	// return instance;
	// }
}
