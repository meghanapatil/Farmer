package com.example.Siddharth.farmer;

/**
 * Created by Affan on 2/17/2018.
 */

public class UserInformation {
    String first,last,middle,address,pincode,username,mobile,id,password,confirmpassword, income;
    public UserInformation(){

    }
    public UserInformation(String first,String last, String middle, String address, String pincode,String username,String mobile, String id, String password,String confirmpassword,String income){
        this.first=first;
        this.last=last;
        this.middle=middle;
        this.address=address;
        this.pincode=pincode;
        this.username=username;
        this.mobile=mobile;
        this.id=id;
        this.password=password;
        this.confirmpassword=confirmpassword;
        this.income=income;
    }

}
