/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assigment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class SDES extends JFrame implements ActionListener{
    
 JButton se,d,e;//se:select file d:Decrypt E:Encrypt
    JTextField t1;//t1:path 
    JPanel p1,p2;//p1:for t1 and se   p2 for d and e    
    
     int key[] ,k1 [],k2[];
       
     static int  p10[]={3,5,2,7,4,10,1,9,8,6};//  Apply permutation P10: 
     
     static int  p8[]={6,3,7,4,8,5,10,9};//    Apply permutation P8:
     
    int[] IP = { 2, 6, 3, 1, 4, 8, 5, 7 };//initial permutation
    
    int[] EP = { 4, 1, 2, 3, 2, 3, 4, 1 };  //  Apply expansion/permutation E/P to input 4 bits

    int[] P4 = { 2, 4, 3, 1 }; // Apply permutation P4: 

    int[] IP_i = { 4, 1, 3, 5, 7, 2, 8, 6 }; //inverse permutation of the initial permutation 
     
     static int[][] S0 = { { 1, 0, 3, 2 },
                   { 3, 2, 1, 0 },
                   { 0, 2, 1, 3 },
                   { 3, 1, 3, 2 } };
   
     
     static int[][] S1 = { { 0, 1, 2, 3 },
                   { 2, 0, 1, 3 },
                   { 3, 0, 1, 0 },
                   { 2, 1, 0, 3 } };
     
     String plainText;
     String cipheText;
     String Filename;
    public SDES(){
        key=new int[10];
        k1=new int[8];
        k2=new int[8];
        
        setTitle("SDES");
        setLayout(new BorderLayout());
        se=new JButton("Select Folder");
        d=new JButton("Decrypt");
        e=new JButton("Encrypt");
      
        t1=new JTextField();
        p1=new JPanel();
        p1.setLayout(new BorderLayout());
        p1.add(t1);
        p1.add(se,BorderLayout.WEST);
        p2=new JPanel();
        p2.setLayout(new BorderLayout());
        p2.add(d,BorderLayout.WEST);
        p2.add(e,BorderLayout.EAST);
        add(p1,BorderLayout.NORTH);
        add(p2,BorderLayout.SOUTH);
     
        se.addActionListener(this);
        e.addActionListener(this);
        d.addActionListener(this);
        
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
    }

     

    @Override
    public void actionPerformed(ActionEvent e1) {
        if(e1.getSource()==se){
            JFileChooser f=new JFileChooser();// the process for chose file
            int response =f.showSaveDialog(null);
            
//save the proccses if the user cancel the proscess or he/she select the file
             /*
            cheack if the user select the folder or not if user select it the response will reseve 0 
            if he/she cancel the process it return 1            
            */
            if(response==JFileChooser.APPROVE_OPTION)
        {
    File f12; 
f12=new File(f.getSelectedFile().getAbsolutePath()); //seve the path 
t1.setText(f12.toString());//put the path in text feild
        }
  
        }
        if(e1.getSource()==e){
            
    String me=JOptionPane.showInputDialog(null,"Enter key \n note : the key must be 10 bit and just your input must be  0 or 1");
            for (int i = 0; i < me.length(); i++) {
                if(me.length()==10){//ceack the key size the key must be 10 bits 
                if(me.charAt(i)=='0'|| me.charAt(i)=='1'){//the key must contain just 0 or 1
                  
  
                    key[i]=Integer.parseInt(me.substring(i, i+1));// save the key in array of integer to use it
                    
                }
                else{
                      JOptionPane.showMessageDialog(null,"you enter "+me.charAt(i)+" and you must just enter 1 or 0" ,"Error",JOptionPane.ERROR_MESSAGE );
                   /*
                      error messege if the  user send didnt send 0 or 1  he/ she send onther char or number 
                      */
                   System.exit(0);
                      break;
                }                }
                else{
                JOptionPane.showMessageDialog(null,"the key must be just 10 bits" ,"Error",JOptionPane.ERROR_MESSAGE );    
               /*\
                of the user put the key more than 10 bits
                */
               System.exit(0);
                break;
                }  
            }
     
       this.Filename=JOptionPane.showInputDialog(null,"Enter the name of the file");
        
        plaintext();
        
        CreateFile();
        KeyGeneration();
    
        Encryption(this.plainText);
    
    
    
        }        
    if(e1.getSource()==d){
            
    String me=JOptionPane.showInputDialog(null,"Enter key \n note : the key must be 10 bit and just your input must be  0 or 1");
            for (int i = 0; i < me.length(); i++) {
                if(me.length()==10){//ceack the key size the key must be 10 bits 
                if(me.charAt(i)=='0'|| me.charAt(i)=='1'){//the key must contain just 0 or 1
                  
  
                    key[i]=Integer.parseInt(me.substring(i, i+1));// save the key in array of integer to use it
                    
                }
                else{
                      JOptionPane.showMessageDialog(null,"you enter "+me.charAt(i)+" and you must just enter 1 or 0" ,"Error",JOptionPane.ERROR_MESSAGE );
                   /*
                      error messege if the  user send didnt send 0 or 1  he/ she send onther char or number 
                      */
                   System.exit(0);
                      break;
                }                }
                else{
                JOptionPane.showMessageDialog(null,"the key must be just 10 bits" ,"Error",JOptionPane.ERROR_MESSAGE );    
               /*\
                of the user put the key more than 10 bits
                */
               System.exit(0);
                break;
                }  
            }
     
       this.Filename=JOptionPane.showInputDialog(null,"Enter the name of the file");
        
        plaintext();
        
        CreateFile();
        KeyGeneration();
     
        Decryption(this.plainText);
        

        
    
        }        
    }   
   public void KeyGeneration(){
       int k[]=new int[10];// to put the key after permutation
           for (int i = 0; i <key.length; i++) {
            k[i]=key[p10[i]-1];
           //   Apply permutation P10 procces
                      }
        
    
        int L[]= new int[5];//the left side of the k
        int R[]=new int[5];// the right side of the k 
        for (int i = 0; i <L.length; i++) {
               L[i]=k[i];
            
               R[i]=k[i+5];
               // the processs that we put the the value of the array of the L and R
              
           }
    
       
        shift(L,1);//the prcess of shifting the left side  LS-1(L)
        shift(R,1);//the prcess of shifting the left side  LS-1(R)
       //1010000010
        
     
        for (int i = 0; i <L.length; i++) {
           k[i]=L[i];
           k[i+5]=R[i];
           /*here we collect L and R toGOTHER again */
        }
       for (int i = 0; i <k1.length; i++) {
           this.k1[i]=k[p8[i]-1];
           //Apply permutation P8 procces and produse the  first key
           
       }
    shift(L,2);//the prcess of shifting the left side  LS-2(L)
    shift(R,2); ///the prcess of shifting the left side  LS-2(R)
     for (int i = 0; i <L.length; i++) {
           k[i]=L[i];
           k[i+5]=R[i];
           
        }
          for (int i = 0; i <k2.length; i++) {
           this.k2[i]=k[p8[i]-1];
                  //Apply permutation P8 procces and produse the  second key
          
          }
          
   
          
   }
   
      public static int[] shift(int[] ar, int n)
    {
        /*
        in this function Responsible for do Left shifting
        we will give it an array (L or R) and number of Left 
        Shifting we need(n)
        */
        while (n > 0) {
            int temp = ar[0];
            for (int i = 0; i < ar.length - 1; i++) {
                ar[i] = ar[i + 1];
            }
            ar[ar.length - 1] = temp;
            n--;
        }
        return ar;
    }


      
   

    public void plaintext() {
        try {            
      File myObj = new File(t1.getText());
      Scanner myReader = new Scanner(myObj);
      this.plainText="";
        while (myReader.hasNextLine()) {
        this.plainText += myReader.nextLine();
        
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      JOptionPane.showMessageDialog(null,"There is an error in your file","Error",JOptionPane.ERROR_MESSAGE); 
      e.printStackTrace();
   }        
    }
    
     public void Encryption(String p){
         String cipher=p;
         
         this.cipheText="";
         int c=2;//becuse we encrypt twice
         int x[]=new int[8];//
         char arr[]=new char[8];         
         int c1[]=new int[8];
        int sw[]=new int[8];
        int c2[]=new int[8];
        int c3[]=new int[8];
        
        PrintWriter pw=null;
         
         while(c>0){
         /* we will encrpt twice so we neeed  it */
         String cipher1="";
        
         for (int i = 0; i <cipher.length(); i++) {
             /* we will take the plain text one character becuse evrey one 
                charcter is 7 bits and we will add 0 in first to be 8 bits and 
                           */
                int z=0;
             String binary=Integer.toBinaryString(cipher.charAt(i));
            // toBinaryString :this function take just char or int
             
            
              if(binary.length()<8){
                   
                 z=8-binary.length();
                 for (int j = 0; j <z; j++) {
                     arr[j]='0';
                 }
              }
             
            for (int j = 0; j <arr.length -z; j++) {
                       
                 arr[j+z]=binary.charAt(j);
             }
           
             for (int j = 0; j <x.length; j++) {
                
                 x[j]=Character.getNumericValue(arr[this.IP[j]-1]);
                 //here we will convert the char to binary
                 
             }
             
         c1=function(x,this.k1); 
           sw=sw(c1);
         c2=function(sw,this.k2);
          
             
             for (int j = 0; j <c3.length; j++) {
                 c3[j]=c2[this.IP_i[j]-1];
             }
             String sa="";
             for (int j = 0; j <c3.length; j++) {
                 sa+=Integer.toString(c3[j]);
             }
            
          int bx=binaryToDecimal(sa);
          char r=(char)bx;
          cipher1+=r;    
  }
         cipher=cipher1;
            c--;              
         }
         this.cipheText=cipher;
         try{
         pw=new PrintWriter(new FileWriter(this.Filename,true));
          pw.print(this.cipheText);
          pw.close();
          }
          catch(Exception E){
              
          }     
     }
             

     public void Decryption(String p){
         String cipher=p;
         this.cipheText="";
         int c=2;//becuse we decrypt twice
         int x[]=new int[8];//
         char arr[]=new char[8];         
         int c1[]=new int[8];
        int sw[]=new int[8];
        int c2[]=new int[8];
        int c3[]=new int[8];
        
        PrintWriter pw=null;
         
         while(c>0){
             String cipher1="";
         /* we will Decrpt twice so we neeed  it */
         
        
         for (int i = 0; i <cipher.length(); i++) {
             /* we will take the plain text one character becuse evrey one 
                charcter is 7 bits and we will add 0 in first to be 8 bits and 
                           */
                int z=0;
             String binary=Integer.toBinaryString(cipher.charAt(i));
            // toBinaryString :this function take just char or int
             
            
              if(binary.length()<8){
                  
                 z=8-binary.length();
                 for (int j = 0; j <z; j++) {
                     arr[j]='0';
                 }
              }
              
            for (int j = 0; j <arr.length -z; j++) {
                       
                 arr[j+z]=binary.charAt(j);
             }
            
             for (int j = 0; j <x.length; j++) {
                
                 x[j]=Character.getNumericValue(arr[this.IP[j]-1]);
                 //here we will convert the char to binary
                  
             }
         c1=function(x,this.k2); 
             
         sw=sw(c1);
         c2=function(sw,this.k1);
          
             
             for (int j = 0; j <c3.length; j++) {
                 c3[j]=c2[this.IP_i[j]-1];
             }
             String sa="";
             for (int j = 0; j <c3.length; j++) {
                 sa+=Integer.toString(c3[j]);
             }
            
          int bx=binaryToDecimal(sa);
          char r=(char)bx;
          cipher1+=r;    
  }
         cipher=cipher1;
            c--;              
         }
         this.cipheText=cipher;
         try{
         pw=new PrintWriter(new FileWriter(this.Filename,true));
          pw.print(this.cipheText);
          pw.close();
          }
          catch(Exception E){
              
          }     
     }
     public int[] function(int x[],int key[]){//1001010101
      int R1[]=new int[4];//the right in first stage 
         int R2[]=new int[4];//the right in second stage 
         int L[]=new int[4];//the Left in first stage
         int L2[]=new int[4];// the left second stage
          int temp[]=new int[8];//to save the right side from r1
         int row;
         int col;
         int c1[]=new int[8];
         for (int i = 0; i <temp.length; i++) {
             temp[i]=x[i];
         }
      for (int j = 0; j <L.length; j++) {
                 //the first stage in encrption we dived the 8 bit in 2 array
                 L[j]=temp[j];
                 R1[j]=temp[j+4];
                 
             }
                for (int j = 0; j <this.EP.length; j++) {
                 temp[j]=R1[this.EP[j]-1];
                 //here we do expansion/permutation E/P to input 4 bits
                 
             }
                for (int j = 0; j <temp.length; j++) {
                 temp[j]=temp[j]^key[j];
                 //we do the second step xor bettwen the key1 and the temp
             }
               
                for (int j = 0; j <L2.length; j++) {
                // we dived the temp in 2 array to be ready in next step Sboxes
                 L2[j]=temp[j];
                 R2[j]=temp[j+4];
             }
                int val;// to save the the the value of the row and col
               row=Integer.parseInt("" + L2[0] + L2[3],2);//to take the value of the row
               col=Integer.parseInt("" + L2[1] + L2[2],2);
              
               
            val=this.S0[row][col];
            String str1=binary_(val);
             
                row = Integer.parseInt("" + R2[0] + R2[3], 2);
                col = Integer.parseInt("" + R2[1] + R2[2], 2);
           val = this.S1[row][col];
        String str2 = binary_(val);
        int arr1[]=new int[4];
             for (int j = 0; j <str1.length(); j++) {
                 arr1[j]=Integer.parseInt(str1.substring(j,j+1));
                 arr1[j+2]=Integer.parseInt(str2.substring(j,j+1));
             }
             int t[]= new int [4];
             for (int j = 0; j <arr1.length; j++) {
                 t[j]=arr1[this.P4[j]-1];
                 L2[j]=t[j]^L[j];
             }
      for (int j = 0; j <L2.length; j++) {
                 c1[j]=L2[j];
                 c1[j+4]=R1[j];
             }
      return c1;
     }
     public int []sw(int c1[]){
         int r[]=new int [4]; 
         int l[]=new int [4];
         int temp[]=new int[8];
         for (int i = 0; i <l.length; i++) {
             l[i]=c1[i];
             r[i]=c1[i+4];
             
         }
         for (int i = 0; i <l.length; i++) {
             temp[i]=r[i];
             temp[i+4]=l[i];
         }
         return temp;
     }
       String binary_(int val)
    {
        if (val == 0)
            return "00";
        else if (val == 1)
            return "01";
        else if (val == 2)
            return "10";
        else
            return "11";
    }
       public void CreateFile(){
        try {
      File myObj = new File(this.Filename);
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
       }
       public static int binaryToDecimal(String binary) {
    int decimal = 0;
    int position = 0;
    for (int x = binary.length() - 1; x >= 0; x--) {
        
        short digit = 1;
        if (binary.charAt(x) == '0') {
            digit = 0;
        }
        double multiplier = Math.pow(2, position);
        decimal += digit * multiplier;
        position++;
    }
    return decimal;
}
    
    
   
  }
     
     
     
         
