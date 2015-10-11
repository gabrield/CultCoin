package hackathon.cultcoin;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class User {

    //private variables
    int _id;
    String _name;
    String _cpf;
    String _password;
    float _coins;

    // Empty constructor
    public User(){

    }
    // constructor
    public User(int id, String name, String cpf, String password){
        this._id = id;
        this._name = name;
        this._cpf = cpf;
        this._password = password;
        //this._coins = cpf;
    }


    public User(int id, String name, String cpf){
        this._id = id;
        this._name = name;
        this._cpf = cpf;
    }

    public User(String name, String cpf, String password){
        this._name = name;
        this._cpf = cpf;
        this._password = password;
    }

    // constructor
    //public User(String name, String _phone_number){
    //    this._name = name;
    //    this._phone_number = _phone_number;
    //}
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }


    public void setName(String name){
        this._name = name;
    }

    public String getCPF(){
        return this._cpf;
    }


    public void setCPF(String cpf){
        this._cpf = cpf;
    }


    public float getCoins(){
        return this._coins;
    }


    public void setCoins(float coins){
        this._coins = coins;
    }


    public void setPassword(String password) {
        this._password = password;
    }


    public String getPassword() {
        return this._password;
    }






}
