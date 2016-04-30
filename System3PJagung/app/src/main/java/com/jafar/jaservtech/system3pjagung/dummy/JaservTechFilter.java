package com.jafar.jaservtech.system3pjagung.dummy;

/**
 * Created by Jafar on 4/9/2016.
 */
public class JaservTechFilter {
    public static boolean isEmpty(String nama){
        return (nama.length() ==0);
    }
    public static boolean isNameValid(String nama){
        int jk;
        for(int i = 0; i<nama.length();i++){
            jk = (int)nama.charAt(i);
            if(jk < 65 ){
                if(jk != 32)
                    if(jk != 39)
                        return false;
            }else if(jk > 90){
                if(jk <97)
                    return false;
                else if(jk > 122)
                    return false;
            }
        }
        return true;
    }
    public static boolean isNameFieldValid(String nama){
        int jk;
        for(int i = 0; i<nama.length();i++){
            jk = (int)nama.charAt(i);
            if(jk < 65 ){
                if (jk < 48){
                    if(jk != 32)
                        return false;
                }else if(jk > 57){
                    return false;
                }
            }else if(jk > 90){
                if(jk <97)
                    return false;
                else if(jk > 122)
                    return false;
            }
        }
        return true;
    }
    public static boolean isAddressValid(String address){
        int jk;
        for(int i = 0; i<address.length();i++){
            jk = (int)address.charAt(i);
            if(jk < 65 ){
                if(jk < 48){
                    if(jk != 32)
                        if (jk != 46)
                            if(jk != 44)
                                return false;
                }else if(jk > 57) {
                    return false;
                }
            }else if(jk > 90){
                if(jk <97)
                    return false;
                else if(jk > 122)
                    return false;
            }
        }
        return true;
    }
    public static boolean isContainMoreSpace(String nama){
        nama+="";
        int com,k;
        int error=0;
        for(int i=0;i<nama.length();i++){
            if((int)nama.charAt(i) == 32){
                com=0;
                for(k=i+1;k<nama.length();k++){
                    if((int)nama.charAt(k) == 32)
                    {
                        com++;
                    }
                    else{
                        i=k-1;
                        if(com>0)
                            error++;
                        break;
                    }
                }
                if(k == nama.length()){
                    if(com>0)
                        error++;
                }
            }
        }
        if(error > 0){
            return true;
        }else{
            return false;
        }
    }
}
