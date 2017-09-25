package com.sineverything.news.api;

/**
 * author Created by harrishuang on 2017/9/19.
 * email : huangjinping@hdfex.com
 */

public interface HTMLConstants {
    String head = "<!DOCTYPE html>\n" +
            "<html lang=\"zh\">\n" +
            "<head>\n" +
            "<meta charset=\"utf-8\" />\n" +
            "<meta name=\"viewport\" content=\"width=device-width,inital-scale=1.0,maximum-scale=1.0,user-scalable=no\" />\n" +
            "<meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n" +
            "<meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black-translucent\">\n" +
            "<!-- <base href=\"http://www.singaporetong.com/\" /> -->\n" +
            "<script type=\"text/javascript\" src=\"file:///android_asset/js/jquery.min.js\"></script>\n" +
            "<script type=\"text/javascript\" src=\"file:///android_asset/js/lazysizes.min.js\"></script>\n" +
            "<script type=\"text/javascript\">\n" +
            "$(function(){\n" +
            "    //遍历所有的图片节点\n" +
            "    $(\"img\").each(function(index,obj){\n" +
            "    \tvar $img=$(this);\n" +
            "    \tvar reSrc = $img.attr(\"src\");\n" +
            "        $img.attr(\"src\",\"file:///android_asset/images/loading.gif\");\n" +
            "        \t\n" +
            "        \t$img.attr(\"data-src\",reSrc).addClass(\"lazyload\");\n" +
            "        \t\n" +
            "    });\n" +
            "})\n" +
            "</script>\n" +
            "<style>\n" +
            "\t* { padding:0px; margin:0px;}\n" +
            "\thtml{ height:100%;font-size:20px}\n" +
            "\tbody{ color:#000; font-family: Heiti, Heiti SC, DroidSans, DroidSansFallback, Arial, \"Microsoft YaHei\"; background-color:#f5f5f5; font-size:0.625rem;}\n" +
            "\t/*多分辨率设定rem设定*/\n" +
            "\t/*Note3*/\n" +
            "\t@media only screen and (min-width: 360px) {\n" +
            "\thtml { font-size: 22px !important; }\n" +
            "\t}\n" +
            "\t/*iPhone6*/\n" +
            "\t@media only screen and (min-width: 376px) {\n" +
            "\thtml { font-size: 23px !important; }\n" +
            "\t}\n" +
            "\t/*iPhone6 plus*/\n" +
            "\t@media only screen and (min-width: 414px) {\n" +
            "\thtml { font-size: 25px !important; }\n" +
            "\t}\n" +
            "\t/*big Resolution*/\n" +
            "\t@media only screen and (min-width: 641px) {\n" +
            "\thtml { font-size: 25px !important; }\n" +
            "\t}\n" +
            "\th1,h2,h3,h4,h5,h6{font-size:0.875em;}\n" +
            "\t.phone_main { max-width:640px; min-width:320px; margin:0px auto; background-color:#f5f5f5; overflow:hidden;}\n" +
            "\t.deal { width:96%; margin-top:2rem; line-height:1rem; padding:0px 2%; color:#666;}\n" +
            "img{-ms-interpolation-mode:bicubic; max-width:100%;}/*防止windows缩放图片时，图像失真*/\n" +
            "\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>";
    
    String footer = "</body>\n" +
            "</html>\n";



}
