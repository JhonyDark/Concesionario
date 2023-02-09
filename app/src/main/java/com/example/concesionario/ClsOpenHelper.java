package com.example.concesionario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClsOpenHelper extends SQLiteOpenHelper {
    public ClsOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Tblciente(Identificacion text primary key, nombre text not null, activo text not null default 'Si')");
        db.execSQL("create table TblVehiculo(placa text primary key, marca text not null, modelo text not null, activo text not null default 'Si')");
        db.execSQL("create table TblVenta(codigo text primary key, fecha text not null, Identificacion text not null, placa text default 'Si', constraint pk_venta foreign key (Identificacion) references Tblciente(Identificacion), foreign key (placa) references TblVehiculo(placa))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Tblcliente");
        db.execSQL("DROP TABLE TblVehiculo");
        db.execSQL("DROP TABLE TblVenta"); {
            onCreate(db);
        }

    }
}
