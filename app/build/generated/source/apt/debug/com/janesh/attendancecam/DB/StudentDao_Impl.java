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

public class StudentDao_Impl implements StudentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfStudent;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByStudentId;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByStudentRegNo;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllStudents;

  public StudentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudent = new EntityInsertionAdapter<Student>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Student`(`studentId`,`courseId`,`studentName`,`regNo`,`faceArrayJson`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Student value) {
        if (value.studentId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.studentId);
        }
        if (value.courseId == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.courseId);
        }
        if (value.studentName == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.studentName);
        }
        if (value.regNo == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.regNo);
        }
        if (value.faceArrayJson == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.faceArrayJson);
        }
      }
    };
    this.__preparedStmtOfDeleteByStudentId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Student where courseId=? and studentId=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByStudentRegNo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from Student where courseId=? and regNo=?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllStudents = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Student";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Student... students) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudent.insert(students);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByStudentId(String courseId, String studentId) {
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
      if (studentId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, studentId);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByStudentId.release(_stmt);
    }
  }

  @Override
  public void deleteByStudentRegNo(String courseId, String regNo) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByStudentRegNo.acquire();
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
      __preparedStmtOfDeleteByStudentRegNo.release(_stmt);
    }
  }

  @Override
  public void deleteAllStudents() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllStudents.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllStudents.release(_stmt);
    }
  }

  @Override
  public List<Student> getAllByCourseId(String courseId) {
    final String _sql = "SELECT * FROM Student where courseId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (courseId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, courseId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("studentId");
      final int _cursorIndexOfCourseId = _cursor.getColumnIndexOrThrow("courseId");
      final int _cursorIndexOfStudentName = _cursor.getColumnIndexOrThrow("studentName");
      final int _cursorIndexOfRegNo = _cursor.getColumnIndexOrThrow("regNo");
      final int _cursorIndexOfFaceArrayJson = _cursor.getColumnIndexOrThrow("faceArrayJson");
      final List<Student> _result = new ArrayList<Student>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Student _item;
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        final String _tmpCourseId;
        _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
        final String _tmpStudentName;
        _tmpStudentName = _cursor.getString(_cursorIndexOfStudentName);
        final String _tmpRegNo;
        _tmpRegNo = _cursor.getString(_cursorIndexOfRegNo);
        final String _tmpFaceArrayJson;
        _tmpFaceArrayJson = _cursor.getString(_cursorIndexOfFaceArrayJson);
        _item = new Student(_tmpStudentId,_tmpCourseId,_tmpStudentName,_tmpRegNo,_tmpFaceArrayJson);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Student getStudentFromId(String studentId) {
    final String _sql = "SELECT * FROM Student where studentId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("studentId");
      final int _cursorIndexOfCourseId = _cursor.getColumnIndexOrThrow("courseId");
      final int _cursorIndexOfStudentName = _cursor.getColumnIndexOrThrow("studentName");
      final int _cursorIndexOfRegNo = _cursor.getColumnIndexOrThrow("regNo");
      final int _cursorIndexOfFaceArrayJson = _cursor.getColumnIndexOrThrow("faceArrayJson");
      final Student _result;
      if(_cursor.moveToFirst()) {
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        final String _tmpCourseId;
        _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
        final String _tmpStudentName;
        _tmpStudentName = _cursor.getString(_cursorIndexOfStudentName);
        final String _tmpRegNo;
        _tmpRegNo = _cursor.getString(_cursorIndexOfRegNo);
        final String _tmpFaceArrayJson;
        _tmpFaceArrayJson = _cursor.getString(_cursorIndexOfFaceArrayJson);
        _result = new Student(_tmpStudentId,_tmpCourseId,_tmpStudentName,_tmpRegNo,_tmpFaceArrayJson);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int countStudents() {
    final String _sql = "SELECT COUNT(*) from Student";
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
  public int checkRegNoUnique(String regNo) {
    final String _sql = "SELECT COUNT(*) from Student where regNo = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (regNo == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, regNo);
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
}
