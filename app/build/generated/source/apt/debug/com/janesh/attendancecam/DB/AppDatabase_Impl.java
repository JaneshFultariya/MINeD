package com.janesh.attendancecam.DB;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class AppDatabase_Impl extends AppDatabase {
  private volatile CourseDao _courseDao;

  private volatile StudentDao _studentDao;

  private volatile AttendanceDao _attendanceDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Course` (`courseId` TEXT NOT NULL, `courseName` TEXT, `year` TEXT, `numberOfClasses` INTEGER NOT NULL, `courseCode` TEXT, PRIMARY KEY(`courseId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Student` (`studentId` TEXT NOT NULL, `courseId` TEXT, `studentName` TEXT, `regNo` TEXT NOT NULL, `faceArrayJson` TEXT, PRIMARY KEY(`studentId`))");
        _db.execSQL("CREATE UNIQUE INDEX `index_Student_regNo` ON `Student` (`regNo`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Attendance` (`regNo` TEXT NOT NULL, `courseId` TEXT NOT NULL, `attendanceNumber` INTEGER NOT NULL, PRIMARY KEY(`regNo`, `courseId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7b85ae23ecaa2d1bb1bf5f9dd6849a63\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Course`");
        _db.execSQL("DROP TABLE IF EXISTS `Student`");
        _db.execSQL("DROP TABLE IF EXISTS `Attendance`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCourse = new HashMap<String, TableInfo.Column>(5);
        _columnsCourse.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 1));
        _columnsCourse.put("courseName", new TableInfo.Column("courseName", "TEXT", false, 0));
        _columnsCourse.put("year", new TableInfo.Column("year", "TEXT", false, 0));
        _columnsCourse.put("numberOfClasses", new TableInfo.Column("numberOfClasses", "INTEGER", true, 0));
        _columnsCourse.put("courseCode", new TableInfo.Column("courseCode", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourse = new TableInfo("Course", _columnsCourse, _foreignKeysCourse, _indicesCourse);
        final TableInfo _existingCourse = TableInfo.read(_db, "Course");
        if (! _infoCourse.equals(_existingCourse)) {
          throw new IllegalStateException("Migration didn't properly handle Course(com.janesh.attendancecam.DB.Course).\n"
                  + " Expected:\n" + _infoCourse + "\n"
                  + " Found:\n" + _existingCourse);
        }
        final HashMap<String, TableInfo.Column> _columnsStudent = new HashMap<String, TableInfo.Column>(5);
        _columnsStudent.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 1));
        _columnsStudent.put("courseId", new TableInfo.Column("courseId", "TEXT", false, 0));
        _columnsStudent.put("studentName", new TableInfo.Column("studentName", "TEXT", false, 0));
        _columnsStudent.put("regNo", new TableInfo.Column("regNo", "TEXT", true, 0));
        _columnsStudent.put("faceArrayJson", new TableInfo.Column("faceArrayJson", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudent = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudent = new HashSet<TableInfo.Index>(1);
        _indicesStudent.add(new TableInfo.Index("index_Student_regNo", true, Arrays.asList("regNo")));
        final TableInfo _infoStudent = new TableInfo("Student", _columnsStudent, _foreignKeysStudent, _indicesStudent);
        final TableInfo _existingStudent = TableInfo.read(_db, "Student");
        if (! _infoStudent.equals(_existingStudent)) {
          throw new IllegalStateException("Migration didn't properly handle Student(com.janesh.attendancecam.DB.Student).\n"
                  + " Expected:\n" + _infoStudent + "\n"
                  + " Found:\n" + _existingStudent);
        }
        final HashMap<String, TableInfo.Column> _columnsAttendance = new HashMap<String, TableInfo.Column>(3);
        _columnsAttendance.put("regNo", new TableInfo.Column("regNo", "TEXT", true, 1));
        _columnsAttendance.put("courseId", new TableInfo.Column("courseId", "TEXT", true, 2));
        _columnsAttendance.put("attendanceNumber", new TableInfo.Column("attendanceNumber", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttendance = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttendance = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttendance = new TableInfo("Attendance", _columnsAttendance, _foreignKeysAttendance, _indicesAttendance);
        final TableInfo _existingAttendance = TableInfo.read(_db, "Attendance");
        if (! _infoAttendance.equals(_existingAttendance)) {
          throw new IllegalStateException("Migration didn't properly handle Attendance(com.janesh.attendancecam.DB.Attendance).\n"
                  + " Expected:\n" + _infoAttendance + "\n"
                  + " Found:\n" + _existingAttendance);
        }
      }
    }, "7b85ae23ecaa2d1bb1bf5f9dd6849a63");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Course","Student","Attendance");
  }

  @Override
  public CourseDao courseDao() {
    if (_courseDao != null) {
      return _courseDao;
    } else {
      synchronized(this) {
        if(_courseDao == null) {
          _courseDao = new CourseDao_Impl(this);
        }
        return _courseDao;
      }
    }
  }

  @Override
  public StudentDao studentDao() {
    if (_studentDao != null) {
      return _studentDao;
    } else {
      synchronized(this) {
        if(_studentDao == null) {
          _studentDao = new StudentDao_Impl(this);
        }
        return _studentDao;
      }
    }
  }

  @Override
  public AttendanceDao attendanceDao() {
    if (_attendanceDao != null) {
      return _attendanceDao;
    } else {
      synchronized(this) {
        if(_attendanceDao == null) {
          _attendanceDao = new AttendanceDao_Impl(this);
        }
        return _attendanceDao;
      }
    }
  }
}
