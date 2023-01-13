<!DOCTYPE html>
<html>
    <head>
        <title>masseges php </title>
        <meta charset="utf-8">
        <style>
        
            div{
                 background: rgb(136,174,19);
                text-align:center;
                margin: 25% 40% 0;
                width: 25%
            }
            button{
                width: 150px;
            height: 50px;
            }
        </style>
    </head>
<body>

<?php
$db=(mysqli_connect("localhost","root","kayyali1"));

mysqli_select_db($db,"project");
$first_name=$_POST[fname];
$email=$_POST[email];
$massege=$_POST[massege];
$q="insert into masseges(first_name,email,the_message) values('$first_name','$email','$massege')";
mysqli_query($db,$q);
mysqli_close($db);
print("")
?>
    <div>
    <h1>Thank you,<br> your message has been sent</h1>
        <a href="../wep%20main.html"><button>Go Home Page</button></a>
    </div>
        
        </body>

    
    </html>