<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

        <title>Skina App - Olhou pro lado negÃ³cio fechado - Aplicativo Skina</title>   
        <meta name="description" content="Skina App - Olhou pro lado negÃ³cio fechado" />
        <meta name="keywords"  content="Skina, App , comprar, vender, compra e venda, Comprar perto,skina compra e venda perto, comprar , vender " />

        <meta name="Resource-type" content="Document" />

        <meta name="msvalidate.01" content="1317B5949E30645932E0C6527E889F65" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta property="og:title" content="Skina App" />
        <meta property="og:type" content="Site" />
        <meta property="og:image" content="http://skina-app.com/imgs/facebook-skina.png" />
        <meta property="og:url" content="http://skina-app.com" />
        <meta property="og:description" content="Skina App - Olhou pro lado negÃ³cio fechado" />
        <meta name="googlebot" content="noodp"> <meta name="robots" content="index, follow">
        <link rel="canonical" href="http://skina-app.com" />
        <link rel="shortcut icon" href="favicon.png" type="image/x-icon">
        <link rel="icon" href="favicon.png" type="image/x-icon">
        <style type="text/css">
            body {
                font-family: arial;
            }

            #borda{
                width: 420px;
                height: 500px;
                border: 1px dashed;
                 position: relative;
            
            }

            #etiqueta {
                height: 341pt;
                width: 290pt;
                padding: 15px;

                position: absolute;

            }

            #chancela, #futuro_2d {
                float: left;
                margin-right: 30px;
            }

            #logo { float: right; }

            #chancela_img {
                width: 100pt;
                height: 70pt;
            }

            .chancela_text {
                width: 100pt;
                text-align: center;
            }

            #chancela_text1 {
                font-size: 8pt;
                margin-top: -54px;
                position: absolute;
            }

            #chancela_text2 {
                font-size: 10pt;
                margin-top: -40px;
                position: absolute;
                font-weight: bold;
            }

            #futuro_2d_img, #logo img {
                width: 70pt;
                height: 70pt;
            }

            #nf, #pedido {
                font-size: 8pt;
            }
            #peso { font-size: 8pt; text-align: right;}
            #peso span { font-weight: bold; }

            #barcode_text { 
                text-align: center; 
                font-size: 11pt; 
                margin-bottom: 5px;
            }

            #barcode_img {
                background: #000;
                margin-left: -85px;
                height: 100px;
            }

            #destinatario {
                margin-top: 7px;
                border: 1px solid #000;
                padding: 4px;
                font-size: 10pt; 
                text-align: left;
            }

            #destinatario .head {
                font-size: 11pt;
            }

            #destinatario p, #remetente p{
                clear: both;
                margin:0;
            }

            p#modo_envio {
                float: right;
                font-weight: bold;
                margin-top: -15px;
                margin-right: 35px;
            }

            #remetente {
                font-size: 10pt;
            }

            #mini_barcode_img {
                background-position: center;
                height: 56pt;
                width: 127pt;
                margin-left: 12pt;
                float: left;
                background-size: 240px;
            }

            #obs {
                width: 48%;
                float: right;
            }
        </style>
    </head>
    <body>
      
        <div id="etiqueta">

            <div id="chancela">
                <img src="imgs/chancela_sedex.jpg" id="chancela_img" />
                <div id="chancela_text1" class="chancela_text">1234567890</div>
                <div id="chancela_text2" class="chancela_text">Rio 2016</div>
                <div id="nf">NF: 12345678</div>
            </div>
            <div id="futuro_2d">
                <img src="http://localhost:8080/timthumb/?type=matrix&cepOrigem=281789217sjkjlajlkdjaljsdajdljlsaxxxxxx" id="futuro_2d_img" />
                <div id="pedido">Pedido: 12345678</div>
            </div>
            <div id="logo">
                <img src="imgs/logo.jpg" />
                <div id="peso">Peso(g): <span>500</span></div>
            </div>
            <p id="barcode_text">DL123456789BR</p>
           <img src="http://localhost:8080/timthumb/?type=barcode&amp;tc=2817892178974hh" id="barcode_img" style="
    
">
            <div id="destinatario">
                <span class="head" style="float: left"><b>DestinatÃ¡rio:</b></span>
                <span class="head" style="float: right">Volume: 001/001</span>
                <p>DestinatÃ¡rio Teste</p>
                <p>DestinatÃ¡rio Mais detalhes</p>
                <p>DestinatÃ¡rio Detalhe</p>
                <p>70000-009 Rio de Janeiro/RJ</p>
                <p id="modo_envio">AR-MP-DD</p>
                <p id="obs">Obs:</p>
                <div id="mini_barcode_img" style="background-image: url( 'http://localhost:8080/timthumb/?type=barcode&amp;tc=281789');"> </div>
                <br style="clear:both" />
            </div>
            <div id="remetente">
                <p><b>Remetente:</b></p>
                <p>Remetente Teste</p>
                <p>Remetente Mais detalhes</p>
                <p>Remetente Detalhe</p>
                <p>70000-009 Rio de Janeiro/RJ</p>
            </div>
        </div>
          <div  id="borda"></div>
          <div id="mini_etiqueta2" style="position:relative"> <img src="" id="" style="heigth:60px"></div>
    </body>
</html>