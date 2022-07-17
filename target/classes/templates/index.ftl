<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>title</title>
    <style>
        body {margin: 0; padding: 0; font-family: Century Gothic;}
        header {width: 100%;}
        main {margin: 0 auto; width: 100%;}
        footer {margin: 0 auto; width: 100%; padding: 20px 0 0 0; color: grey;}
        a {text-decoration: none;}
        img {text-align:center; padding: 10px; border-radius: 10px;}
        .clr {clear:both; margin:0; padding:0; width:100%; font-size:0; line-height:0;}

        .right {float: right;}

        .left {float: left;}

        .center {text-align:  center;}

        .button{background: white; border-radius: 10px; font-family: Century Gothic; border-color: grey; font-size: 20px;}
        .button a:visited{color: gray;}

        .menu {margin: 0; padding: 20px 0; height: 30px; line-height: 30px; text-align: center;}
        .menu li {display: inline-block; width: 15%; vertical-align: top; font-size: 15px;}
        .menu li a {text-decoration: none; transition: all 0.15s ease; color: black;}
        .menu li a:hover {text-decoration: none; color: grey; font-size: 17px;}
        .menu li ul {position: absolute; top: -1000px; margin: 0 0 0 5px; padding: 0; border-radius: 5px; width: 260px; border-color: black; height: 160px; background: ghostwhite;}
        .menu li ul li {display: block; padding: 10px 15px; font-size: 15px; text-align: left; width: 100%; }
        .menu li:hover ul {top: 50px;}

        arcticle {display: inline-block; margin: 0px auto; width: 100px; vertical-align: top;}
        .text {margin: 0 auto; width: 1080px; padding: 30px 0 0 0; text-align: justify;}

        .title {margin: 0 300px 0 0; padding: 0 10px 0 0; font-size: 15px; font-weight: bold; text-align: center;}
        .title a {text-decoration: none; color: black;}
        .title a:visited {color: black;}
    </style>
</head>
<body>
<div>
    <header>
        <div class="main">
            <ul class="menu">
                <li class="title"><a href="#">Simple Text!</a></li>
                <li><a href="#">Simple Text</a></li>
                <li><a href="#">Simple Text</a>
                    <ul>
                        <li><a href="#">Simple Text</a></li>
                        <li><a href="#">Simple Text</a></li>
                        <li><a href="#">Simple Text</a></li>
                    </ul>
                </li>
                <li><a href="#">О нас</a></li>
            </ul>
        </div>
    </header>
    <article class="text">
        <h1>текст для привлечения внимания!</h1>
        <p>random text random text random text random text random text random text random text random text random text random text random text random text.</p>
    </article>
    <footer>
        <ul class="menu">
            <li><a href="#">политика неконфеденциальности</a></li>
            <li><a href="#">копирайт</a></li>
            <li><a href="#">отказ от детей</a></li>
            <li><a href="#">статья о полезности печенек</a></li>
        </ul>

    </footer>
</div>
</body>
</html>