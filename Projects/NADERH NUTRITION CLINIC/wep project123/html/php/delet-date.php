<!DOCTYPE html>

<!-- Fig. 19.15: data.html -->
<!-- Form to query a MySQL database. -->
<html>
   <head>
      <meta charset = "utf-8">
      <title></title>
       <style>
           body{
               background-image: url(imges/nad.jpeg);
               background-repeat: no-repeat;
               background-size:1000px
           }
           h1{
               
               
               margin-left: 42%;
               width: 20%;
           }
                  button{
         background:rgb(136,174,19);
         color: white;
          margin-left:47% ;
         width: 150px;
            height: 50px;
            }
    
           
       </style>
   </head>
   <body> 
        <?php
       $email =$_POST["email"];
       $phone=$_POST["number"];
          $database = mysqli_connect( "localhost","root", "kayyali1" );  
        mysqli_select_db($database, "project");
  $query = "SELECT `date`, `first_name`, `email`, `phone_num` FROM `booking` WHERE `phone_num`=$phone and email ='$email' " ;
$r= mysqli_query($database, $query);
          
    
    mysqli_close($database);
    if(mysqli_num_rows($r)<1){
    die("<h1>&nbsp;&nbsp; &nbsp; You Didn't Booking  </h1><br>
       <a href='../wep%20main.html'><button>Go Home Page </button></a> </body> </html>");
    }
       else{
       

       $db=mysqli_connect("localhost","root","kayyali1");
       mysqli_select_db($db, "project");
         $q = "DELETE FROM `booking` WHERE `phone_num`= $phone AND `email`='$email'";
        mysqli_query($db, $q);
        mysqli_close($db);
       print("<h1>&nbsp;&nbsp; &nbsp; your booking is deleted</h1>");
        
      
       
       }?>
       <br>
       <a href="../wep%20main.html"><button>Go Home Page </button></a>
   </body>
  </html>