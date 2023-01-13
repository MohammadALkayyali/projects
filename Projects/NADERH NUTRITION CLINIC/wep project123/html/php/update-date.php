<!DOCTYPE html>
<html>
<head> <meta charset = "utf-8">
       <title> Update date </title>
<style>
    
     table , h1{
                background:rgb(136,174,19);
                
                text-align:center;
                margin-left:40% ;
                width: 25%;
                height: 50px;
                
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
        $date=$_POST[datee];
$email=$_POST[email];
$phone=$_POST[number];

$db = mysqli_connect("localhost","root","kayyali1");
mysqli_select_db($db,"project");           

         $q = "UPDATE  booking SET  date ='$date'   WHERE email ='$email' and phone_num=$phone  "; 
       mysqli_query($db, $q);
       mysqli_close($db);
 ?>
    <?php
          $database = mysqli_connect( "localhost","root", "kayyali1" );  
        mysqli_select_db($database, "project");
  $query = "SELECT `date`, `first_name`, `email`, `phone_num` FROM `booking` WHERE `phone_num`=$phone and email ='$email' and date ='$date' " ;
$r= mysqli_query($database, $query);
          
    
    mysqli_close($database);
    if(mysqli_num_rows($r)<1){
    die("<h1> you didnt booking yet </h1> <a href='../book.html'><button> Appointment Booking</button></a></body> </html>");
    }
      else{
      print("<table border='1'>");
          print("<h1>the date updateted </h1>");
         print("<caption>This is your information and your new date </caption>");
    
            
            while ( $a = mysqli_fetch_row($r) )
            {
        
               print( "<tr>" );
               for ($i=0;$i<count($a) ;$i++ ) 
                  print( "<td>$a[$i]</td>");
               print( "</tr>" );
            } 
         
      print("</table>");
      }
        ?>
        <br>
         <a href="../wep%20main.html"><button>Go Home Page</button></a>
        
    </body>

</html>