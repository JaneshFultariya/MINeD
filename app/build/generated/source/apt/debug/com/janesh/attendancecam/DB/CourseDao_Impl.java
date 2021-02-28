package com.janesh.attendancecam.DB;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class CourseDao_Impl implements CourseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCourse;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByCourseId;

  public CourseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Course`(`courseId`,`courseName`,`year`,`numberOfClasses`,`courseCode`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        if (value.courseId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.courseId);
        }
        if (value.courseName == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.courseName);
        }
        if (value.year == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.year);
        }
        stmt.bindLong(4, value.numberOfClasses);
        if (value.courseCode == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.courseCode);
        }
      }
    };
    this.__preparedStmtOfDeleteByCourseId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Course where courseId=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Course... courses) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourse.insert(courses);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByCourseId(String courseId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByCourseId.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (courseId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, courseId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByCourseId.release(_stmt);
    }
  }

  @Override
  public List<Course> getAll() {
    final String _sql = "SELECT * FROM Course";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCourseId = _cursor.getColumnIndexOrThrow("courseId");
      final int _cursorIndexOfCourseName = _cursor.getColumnIndexOrThrow("courseName");
      final int _cursorIndexOfYear = _cursor.getColumnIndexOrThrow("year");
      final int _cursorIndexOfNumberOfClasses = _cursor.getColumnIndexOrThrow("numberOfClasses");
      final int _cursorIndexOfCourseCode = _cursor.getColumnIndexOrThrow("courseCode");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final String _tmpCourseId;
        _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
        final String _tmpCourseName;
        _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
        final String _tmpYear;
        _tmpYear = _cursor.getString(_cursorIndexOfYear);
        final int _tmpNumberOfClasses;
        _tmpNumberOfClasses = _cursor.getInt(_cursorIndexOfNumberOfClasses);
        final String _tmpCourseCode;
        _tmpCourseCode = _cursor.getString(_cursorIndexOfCourseCode);
        _item = new Course(_tmpCourseId,_tmpCourseName,_tmpYear,_tmpNumberOfClasses,_tmpCourseCode);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countCourses() {
    final String _sql = "SELECT COUNT(*) from Course";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getNumberOfClasses(String courseId) {
    final String _sql = "SELECT numberOfClasses from Course where courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (courseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, courseId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getCourseNameById(String courseId) {
    final String _sql = "SELECT courseName from Course where courseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (courseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, courseId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
