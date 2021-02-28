package com.janesh.attendancecam.DB;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;

public class AttendanceDao_Impl implements AttendanceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAttendance;

  private final SharedSQLiteStatement __preparedStmtOfIncrementAttendance;

  private final SharedSQLiteStatement __preparedStmtOfDecrementAttendance;

  private final SharedSQLiteStatement __preparedStmtOfSetAttendance;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByStudentId;

  public AttendanceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttendance = new EntityInsertionAdapter<Attendance>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Attendance`(`regNo`,`courseId`,`attendanceNumber`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Attendance value) {
        if (value.regNo == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.regNo);
        }
        if (value.courseId == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.courseId);
        }
        stmt.bindLong(3, value.attendanceNumber);
      }
    };
    this.__preparedStmtOfIncrementAttendance = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Attendance SET attendanceNumber = attendanceNumber + 1 WHERE courseId = ? and regNo = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementAttendance = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Attendance SET attendanceNumber = attendanceNumber - 1 WHERE courseId = ? and regNo = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetAttendance = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Attendance SET attendanceNumber = ? WHERE courseId = ? and regNo = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByStudentId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Attendance where courseId=? and regNo=?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Attendance... attendances) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAttendance.insert(attendances);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void incrementAttendance(String courseId, String regNo) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementAttendance.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (courseId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, courseId);
      }
      _argIndex = 2;
      if (regNo == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, regNo);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfIncrementAttendance.release(_stmt);
    }
  }

  @Override
  public void decrementAttendance(String courseId, String regNo) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementAttendance.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (courseId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, courseId);
      }
      _argIndex = 2;
      if (regNo == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, regNo);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDecrementAttendance.release(_stmt);
    }
  }

  @Override
  public void setAttendance(String courseId, String regNo, int attendanceNumber) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetAttendance.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, attendanceNumber);
      _argIndex = 2;
      if (courseId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, courseId);
      }
      _argIndex = 3;
      if (regNo == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, regNo);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetAttendance.release(_stmt);
    }
  }

  @Override
  public void deleteByStudentId(String courseId, String regNo) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByStudentId.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (courseId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, courseId);
      }
      _argIndex = 2;
      if (regNo == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, regNo);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByStudentId.release(_stmt);
    }
  }

  @Override
  public Attendance getAttendance(String courseId, String regNo) {
    final String _sql = "SELECT * FROM Attendance where courseId = ? and regNo = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (courseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, courseId);
    }
    _argIndex = 2;
    if (regNo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, regNo);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRegNo = _cursor.getColumnIndexOrThrow("regNo");
      final int _cursorIndexOfCourseId = _cursor.getColumnIndexOrThrow("courseId");
      final int _cursorIndexOfAttendanceNumber = _cursor.getColumnIndexOrThrow("attendanceNumber");
      final Attendance _result;
      if(_cursor.moveToFirst()) {
        final String _tmpRegNo;
        _tmpRegNo = _cursor.getString(_cursorIndexOfRegNo);
        final String _tmpCourseId;
        _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
        final int _tmpAttendanceNumber;
        _tmpAttendanceNumber = _cursor.getInt(_cursorIndexOfAttendanceNumber);
        _result = new Attendance(_tmpRegNo,_tmpCourseId,_tmpAttendanceNumber);
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
