package org.example;

import org.example.doa.Db;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Db db=new Db();
       db.printUsers();
       /* db.addUser("Arel","Sergo","12-september");
        System.out.println(db.getUsersCount());*/

    }
}
