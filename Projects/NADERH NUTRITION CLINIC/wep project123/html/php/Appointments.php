<!DOCTYPE html>
<html>
    <head>
    <title>Appointments</title>
        <meta charset="utf-8">
        
        <style>
            table{
                background:rgb(136,174,19);
                color: white;
                text-align:center;
                margin-left:40% ;
                width: 25%;

                
            }

        
        </style>
    </head>
<body>


<?php
    
          $database = mysqli_connect( "localhost","root", "kayyali1" );  
        mysqli_select_db($database, "project");
  $query = "SELECT `date`, `first_name`, `email`, `phone_num` FROM `booking` ORDER BY `date` ASC"  ;
$r = mysqli_query($database, $query);
          
         mysqli_close( $database );
      ?>
      <table border="1">
         <caption>This is the  masseges </caption>
         <?php
            print("<tr><th>Date </th><th>First Name </th><th>Email</th> <th>Phone Number</th></tr>");
            while ( $a = mysqli_fetch_row($r) )
            {
        
               print( "<tr>" );
               for ($i=0;$i<count($a) ;$i++ ) 
                  print( "<td>$a[$i]</td>");
               print( "</tr>" );
            } 
         ?>
          
      </table>
      
     
   </body>
</html>