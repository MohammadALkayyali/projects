<!DOCTYPE html>
<html>
<head>
    <meta  charset="utf-8">
    <style>
  caption{
        font-size: 20px;
    }
    p{
           margin-left:40% ;
        font-size: 20px;
    }
    
     table {
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
$db=(mysqli_connect("localhost","root","kayyali1"));

mysqli_select_db($db,"project");
   $date=$_POST[datee];
$first_name=$_POST[fname];
$email=$_POST[email];
$phone=$_POST[number];
$q="insert into booking (date,first_name,email,phone_num) values('$date','$first_name','$email','$phone')";
mysqli_query($db,$q);
mysqli_close($db);
print("<p>The reservation process has been successful</p>");
 ?>
<?php
          $database = mysqli_connect( "localhost","root", "kayyali1" );  
        mysqli_select_db($database, "project");
  $query = "SELECT `date`, `first_name`, `email`, `phone_num` FROM `booking` WHERE `phone_num`=$phone";
$r = mysqli_query($database, $query);
          
         mysqli_close( $database );
      ?>
      <table border="1">
         <caption>This is your information </caption>
         <?php
            
            while ( $a = mysqli_fetch_row($r) )
            {
        
               print( "<tr>" );
               for ($i=0;$i<count($a) ;$i++ ) 
                  print( "<td>$a[$i]</td>");
               print( "</tr>" );
            } 
         ?>
      </table>
      
      <p>Please  befor you come call us in : +962 79 089 8575 </p>
        <a href="../wep%20main.html"><button>Go Home Page</button></a>
   </body>
</html>
