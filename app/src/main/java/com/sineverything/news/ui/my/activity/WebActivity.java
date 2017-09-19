package com.sineverything.news.ui.my.activity;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.api.HTMLConstants;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/9/18.
 * email : huangjinping@hdfex.com
 */

public class WebActivity extends BaseActivity {


    @Bind(R.id.webview)
    WebView webview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
//        file:///android_asset/xxx.js


        String local = "file:///android_asset";



        String center = "\n" +
                "<div class=\"phone_main\">\n" +
                "    <div class=\"deal\">\n" +
                "        <div class=\"information_detail_left_center\" style=\"font-size:14px\">\n" +
                "          \n" +
                "            <div class=\"va_con _j_master_content\" data-cs-t=\"ginfo_kw_hotel\">\n" +
                "                <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"91051508\">对于清迈，我和summer先森也就从11月份才开始了解~由于我是韩星脑残粉○(&nbsp;＾皿＾)っHiahia…一直想去韩国旅拍，所以对泰国不是很了解，但11月份订了婚期，时间、天气的关系只好让我放弃了韩国的旅拍，选择了气候相对比较热的国家=￣ω￣=出发前一天我的心还突突的~特别害怕旅行的地点选的不完美~NONONO(⊙&nbsp;▽&nbsp;⊙)~~事实证明！！！我这心突突的完全木有意义~清迈真的有自己独特的味道~先来几发美腻的照片哈~</h6>\n" +
                "                <h6 id=\"_j_photo_93051638\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"93051639\"\n" +
                "                    data-pid=\"93051638\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://b1-q.mafengwo.net/s9/M00/44/2C/wKgBs1adiCGACRpWABIZo6S0NfU41.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://b1-q.mafengwo.net/s9/M00/44/2C/wKgBs1adiCGACRpWABIZo6S0NfU41.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://b1-q.mafengwo.net/s9/M00/44/2C/wKgBs1adiCGACRpWABIZo6S0NfU41.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"93051638\"></h6>\n" +
                "                <h6 id=\"_j_photo_91051606\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91051607\"\n" +
                "                    data-pid=\"91051606\" data-poiid=\"5424032\"><img class=\"_j_lazyload\"\n" +
                "                                                                  src=\"https://n4-q.mafengwo.net/s9/M00/40/CE/wKgBs1aLcjKAESONAA8Y4ddIfXs42.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  alt=\"\"\n" +
                "                                                                  data-src=\"https://n4-q.mafengwo.net/s9/M00/40/CE/wKgBs1aLcjKAESONAA8Y4ddIfXs42.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                                                  data-rt-src=\"https://n4-q.mafengwo.net/s9/M00/40/CE/wKgBs1aLcjKAESONAA8Y4ddIfXs42.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  data-pid=\"91051606\"><span class=\"pic_tag\">塔佩门</span>\n" +
                "                </h6>\n" +
                "                <h6 id=\"_j_photo_91051983\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91051984\"\n" +
                "                    data-pid=\"91051983\" data-poiid=\"6116560\"><img class=\"_j_lazyload\"\n" +
                "                                                                  src=\"https://n3-q.mafengwo.net/s9/M00/41/76/wKgBs1aLcsWABmZvAA6OVUwIbw801.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  alt=\"\"\n" +
                "                                                                  data-src=\"https://n3-q.mafengwo.net/s9/M00/41/76/wKgBs1aLcsWABmZvAA6OVUwIbw801.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                                                  data-rt-src=\"https://n3-q.mafengwo.net/s9/M00/41/76/wKgBs1aLcsWABmZvAA6OVUwIbw801.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  data-pid=\"91051983\"><span class=\"pic_tag\">宁曼路</span>\n" +
                "                </h6>\n" +
                "                <h6 id=\"_j_photo_91052481\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91052482\"\n" +
                "                    data-pid=\"91052481\" data-poiid=\"5424032\"><img class=\"_j_lazyload\"\n" +
                "                                                                  src=\"https://c1-q.mafengwo.net/s9/M00/41/FF/wKgBs1aLczeAT1YmAAtMiGIH4gY53.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  alt=\"\"\n" +
                "                                                                  data-src=\"https://c1-q.mafengwo.net/s9/M00/41/FF/wKgBs1aLczeAT1YmAAtMiGIH4gY53.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                                                  data-rt-src=\"https://c1-q.mafengwo.net/s9/M00/41/FF/wKgBs1aLczeAT1YmAAtMiGIH4gY53.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  data-pid=\"91052481\"><span class=\"pic_tag\">塔佩门</span>\n" +
                "                </h6>\n" +
                "                <h6 id=\"_j_photo_92280054\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92280055\"\n" +
                "                    data-pid=\"92280054\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a4-q.mafengwo.net/s9/M00/E3/E4/wKgBs1aV9lSAazldAAXTh0yWEAo52.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a4-q.mafengwo.net/s9/M00/E3/E4/wKgBs1aV9lSAazldAAXTh0yWEAo52.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a4-q.mafengwo.net/s9/M00/E3/E4/wKgBs1aV9lSAazldAAXTh0yWEAo52.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92280054\"></h6>\n" +
                "                <h6 id=\"_j_photo_91052738\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91052739\"\n" +
                "                    data-pid=\"91052738\" data-poiid=\"12544\"><img class=\"_j_lazyload\"\n" +
                "                                                                src=\"https://b3-q.mafengwo.net/s9/M00/42/67/wKgBs1aLc5SASEWxAA_QnCyLroA20.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                alt=\"\"\n" +
                "                                                                data-src=\"https://b3-q.mafengwo.net/s9/M00/42/67/wKgBs1aLc5SASEWxAA_QnCyLroA20.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                                                data-rt-src=\"https://b3-q.mafengwo.net/s9/M00/42/67/wKgBs1aLc5SASEWxAA_QnCyLroA20.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                data-pid=\"91052738\"><span class=\"pic_tag\">契迪龙寺</span>\n" +
                "                </h6>\n" +
                "                <h6 id=\"_j_photo_92293168\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92293169\"\n" +
                "                    data-pid=\"92293168\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n1-s.mafengwo.net/c_zoom,w_1360,q_90/s9/M00/AF/02/wKgBs1aPKqmARBeAAA4cmE_jGEM39.jpeg\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n1-s.mafengwo.net/c_zoom,w_680,q_90/s9/M00/AF/02/wKgBs1aPKqmARBeAAA4cmE_jGEM39.jpeg\"\n" +
                "                                             data-rt-src=\"https://n1-s.mafengwo.net/c_zoom,w_1360,q_90/s9/M00/AF/02/wKgBs1aPKqmARBeAAA4cmE_jGEM39.jpeg\"\n" +
                "                                             data-pid=\"92293168\"></h6>\n" +
                "                <h6 id=\"_j_photo_92111727\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92111728\"\n" +
                "                    data-pid=\"92111727\" data-poiid=\"6511615\"><img class=\"_j_lazyload\"\n" +
                "                                                                  src=\"https://n4-q.mafengwo.net/s9/M00/66/35/wKgBs1aUoAiAHmZKAAoMLer5zx093.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  alt=\"\"\n" +
                "                                                                  data-src=\"https://n4-q.mafengwo.net/s9/M00/66/35/wKgBs1aUoAiAHmZKAAoMLer5zx093.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                                                  data-rt-src=\"https://n4-q.mafengwo.net/s9/M00/66/35/wKgBs1aUoAiAHmZKAAoMLer5zx093.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  data-pid=\"92111727\"><span class=\"pic_tag\">温佳华酒店</span>\n" +
                "                </h6>\n" +
                "                <h6 id=\"_j_photo_92112879\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92112880\"\n" +
                "                    data-pid=\"92112879\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://b1-q.mafengwo.net/s9/M00/66/E1/wKgBs1aUoX-AVmGqAAroTP2Z-7U36.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://b1-q.mafengwo.net/s9/M00/66/E1/wKgBs1aUoX-AVmGqAAroTP2Z-7U36.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://b1-q.mafengwo.net/s9/M00/66/E1/wKgBs1aUoX-AVmGqAAroTP2Z-7U36.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92112879\"></h6>\n" +
                "                <h6 id=\"_j_photo_92116554\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92116555\"\n" +
                "                    data-pid=\"92116554\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a3-q.mafengwo.net/s9/M00/68/20/wKgBs1aUo_6ABZWzAAgoadZvN5c43.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a3-q.mafengwo.net/s9/M00/68/20/wKgBs1aUo_6ABZWzAAgoadZvN5c43.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a3-q.mafengwo.net/s9/M00/68/20/wKgBs1aUo_6ABZWzAAgoadZvN5c43.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92116554\"></h6>\n" +
                "                <h6 id=\"_j_photo_91584025\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91584041\"\n" +
                "                    data-pid=\"91584025\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a3-q.mafengwo.net/s9/M00/E1/E1/wKgBs1aPmzqAa-8BAAmuyEnaUgE43.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a3-q.mafengwo.net/s9/M00/E1/E1/wKgBs1aPmzqAa-8BAAmuyEnaUgE43.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a3-q.mafengwo.net/s9/M00/E1/E1/wKgBs1aPmzqAa-8BAAmuyEnaUgE43.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91584025\"></h6>\n" +
                "\n" +
                "\n" +
                "                <h6 id=\"_j_photo_91396924\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91396930\"\n" +
                "                    data-pid=\"91396924\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://c4-q.mafengwo.net/s9/M00/3D/54/wKgBs1aOG3mAH08iAAY9SdGp_Jk04.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://c4-q.mafengwo.net/s9/M00/3D/54/wKgBs1aOG3mAH08iAAY9SdGp_Jk04.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://c4-q.mafengwo.net/s9/M00/3D/54/wKgBs1aOG3mAH08iAAY9SdGp_Jk04.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91396924\"></h6>\n" +
                "                <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"91398045\">推荐大家吃小菠萝，三口一个，槽甜呦~~四星！！！</h6>\n" +
                "                <h6 id=\"_j_photo_91396925\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91396931\"\n" +
                "                    data-pid=\"91396925\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a3-q.mafengwo.net/s9/M00/3D/54/wKgBs1aOG3uAHXn1AAX8vMAfa4Y12.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a3-q.mafengwo.net/s9/M00/3D/54/wKgBs1aOG3uAHXn1AAX8vMAfa4Y12.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a3-q.mafengwo.net/s9/M00/3D/54/wKgBs1aOG3uAHXn1AAX8vMAfa4Y12.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91396925\"></h6>\n" +
                "                <h2 class=\"t5\"><span class=\"_j_anchor\">★12.18★</span></h2>\n" +
                "                <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"91411945\">\n" +
                "                    忙忙活活的第一天就这么过去了o(￣ε￣*)昨天到了清迈天都黑了，都没有好好欣赏下，早晨本来想睡到自然醒，结果太激动了不到7点就醒了...今天的任务就是好好看看清迈这个城市~但是第一件事情就是必须要租辆摩托车，清迈当地几乎人手一辆摩托~太拉风啦！！酒店的老板帮我们叫来了租车公司的师傅，250株一天，之前看攻略大概在180-200之间，有点小贵了，但好在车很新颜色我也很喜欢~拍了一张summer先森和师傅的合影~额...我只能说太黑了~</h6>\n" +
                "                <h6 id=\"_j_photo_91499759\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91499760\"\n" +
                "                    data-pid=\"91499759\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://b4-q.mafengwo.net/s9/M00/A6/83/wKgBs1aPG5iAXNfkAA5CK3vatu464.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://b4-q.mafengwo.net/s9/M00/A6/83/wKgBs1aPG5iAXNfkAA5CK3vatu464.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://b4-q.mafengwo.net/s9/M00/A6/83/wKgBs1aPG5iAXNfkAA5CK3vatu464.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91499759\"></h6>\n" +
                "\n" +
                "                <h6 id=\"_j_photo_91496317\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496321\"\n" +
                "                    data-pid=\"91496317\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://b2-q.mafengwo.net/s9/M00/A1/14/wKgBs1aPFAeAKtjsAA9Y89MqQWQ76.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://b2-q.mafengwo.net/s9/M00/A1/14/wKgBs1aPFAeAKtjsAA9Y89MqQWQ76.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://b2-q.mafengwo.net/s9/M00/A1/14/wKgBs1aPFAeAKtjsAA9Y89MqQWQ76.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496317\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496314\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496318\"\n" +
                "                    data-pid=\"91496314\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n2-q.mafengwo.net/s9/M00/A1/11/wKgBs1aPFAKALiVlAAkerC6jkAM01.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n2-q.mafengwo.net/s9/M00/A1/11/wKgBs1aPFAKALiVlAAkerC6jkAM01.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://n2-q.mafengwo.net/s9/M00/A1/11/wKgBs1aPFAKALiVlAAkerC6jkAM01.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496314\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496315\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496319\"\n" +
                "                    data-pid=\"91496315\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n1-s.mafengwo.net/c_zoom,w_1360,q_90/s9/M00/A1/12/wKgBs1aPFASAETB4AAYpH2znVLk13.jpeg\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n1-s.mafengwo.net/c_zoom,w_680,q_90/s9/M00/A1/12/wKgBs1aPFASAETB4AAYpH2znVLk13.jpeg\"\n" +
                "                                             data-rt-src=\"https://n1-s.mafengwo.net/c_zoom,w_1360,q_90/s9/M00/A1/12/wKgBs1aPFASAETB4AAYpH2znVLk13.jpeg\"\n" +
                "                                             data-pid=\"91496315\"></h6>\n" +
                "                <h6 id=\"_j_photo_92244884\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92244885\"\n" +
                "                    data-pid=\"92244884\" data-poiid=\"9957822\"><img class=\"_j_lazyload\"\n" +
                "                                                                  src=\"https://b2-q.mafengwo.net/s9/M00/CA/C8/wKgBs1aVxDOAW7pAAAqzlztGeco50.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  alt=\"\"\n" +
                "                                                                  data-src=\"https://b2-q.mafengwo.net/s9/M00/CA/C8/wKgBs1aVxDOAW7pAAAqzlztGeco50.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                                                  data-rt-src=\"https://b2-q.mafengwo.net/s9/M00/CA/C8/wKgBs1aVxDOAW7pAAAqzlztGeco50.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                                                  data-pid=\"92244884\"><span\n" +
                "                        class=\"pic_tag\">清迈别墅精品酒店</span></h6>\n" +
                "                <h6 id=\"_j_photo_92140863\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92140864\"\n" +
                "                    data-pid=\"92140863\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n3-q.mafengwo.net/s9/M00/76/1C/wKgBs1aUuVuARQI_AAis79vaRQk51.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n3-q.mafengwo.net/s9/M00/76/1C/wKgBs1aUuVuARQI_AAis79vaRQk51.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://n3-q.mafengwo.net/s9/M00/76/1C/wKgBs1aUuVuARQI_AAis79vaRQk51.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92140863\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496042\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496045\"\n" +
                "                    data-pid=\"91496042\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://c4-q.mafengwo.net/s9/M00/A1/02/wKgBs1aPE8yAb_-LAApx1NfYNpc39.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://c4-q.mafengwo.net/s9/M00/A1/02/wKgBs1aPE8yAb_-LAApx1NfYNpc39.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://c4-q.mafengwo.net/s9/M00/A1/02/wKgBs1aPE8yAb_-LAApx1NfYNpc39.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496042\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496456\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496460\"\n" +
                "                    data-pid=\"91496456\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://c1-q.mafengwo.net/s9/M00/A1/3C/wKgBs1aPFGmAJEy6AAbHlI9yoak83.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://c1-q.mafengwo.net/s9/M00/A1/3C/wKgBs1aPFGmAJEy6AAbHlI9yoak83.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://c1-q.mafengwo.net/s9/M00/A1/3C/wKgBs1aPFGmAJEy6AAbHlI9yoak83.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496456\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496043\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496046\"\n" +
                "                    data-pid=\"91496043\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a3-q.mafengwo.net/s9/M00/A1/03/wKgBs1aPE8-AN3pWAAyhseIJZlQ41.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a3-q.mafengwo.net/s9/M00/A1/03/wKgBs1aPE8-AN3pWAAyhseIJZlQ41.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a3-q.mafengwo.net/s9/M00/A1/03/wKgBs1aPE8-AN3pWAAyhseIJZlQ41.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496043\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496458\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496462\"\n" +
                "                    data-pid=\"91496458\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a4-q.mafengwo.net/s9/M00/A1/3F/wKgBs1aPFHGAQGZOAAiNVXOgAs481.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a4-q.mafengwo.net/s9/M00/A1/3F/wKgBs1aPFHGAQGZOAAiNVXOgAs481.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a4-q.mafengwo.net/s9/M00/A1/3F/wKgBs1aPFHGAQGZOAAiNVXOgAs481.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496458\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496457\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496461\"\n" +
                "                    data-pid=\"91496457\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://c2-q.mafengwo.net/s9/M00/A1/3D/wKgBs1aPFGyABFzQAAV93D8Jg6417.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://c2-q.mafengwo.net/s9/M00/A1/3D/wKgBs1aPFGyABFzQAAV93D8Jg6417.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://c2-q.mafengwo.net/s9/M00/A1/3D/wKgBs1aPFGyABFzQAAV93D8Jg6417.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496457\"></h6>\n" +
                "                <h6 id=\"_j_photo_91496459\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91496463\"\n" +
                "                    data-pid=\"91496459\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://b2-q.mafengwo.net/s9/M00/A1/3F/wKgBs1aPFHSAaQk4AAqUmmJ9Vdw03.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://b2-q.mafengwo.net/s9/M00/A1/3F/wKgBs1aPFHSAaQk4AAqUmmJ9Vdw03.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://b2-q.mafengwo.net/s9/M00/A1/3F/wKgBs1aPFHSAaQk4AAqUmmJ9Vdw03.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"91496459\"></h6>\n" +
                "                <h6 id=\"_j_photo_92141651\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92141652\"\n" +
                "                    data-pid=\"92141651\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n3-q.mafengwo.net/s9/M00/76/6E/wKgBs1aUuaeAHy2hAA71L3tFWxQ25.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n3-q.mafengwo.net/s9/M00/76/6E/wKgBs1aUuaeAHy2hAA71L3tFWxQ25.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://n3-q.mafengwo.net/s9/M00/76/6E/wKgBs1aUuaeAHy2hAA71L3tFWxQ25.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92141651\"></h6>\n" +
                "                <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"91502045\">\n" +
                "                    两个人拍合照很耽误时间，支架子，调构图，时间就这么流逝啦~o(￣ε￣*)不开森~肚子也饿了，我们准备骑着小摩托粗发吃美食啦~我太喜欢这边的小摩托啦~超可爱~忍不住拿手机狂拍了几张我俩自拍~哈哈~\\(≧▽≦)/~</h6>\n" +
                "                <h6 id=\"_j_photo_91502908\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"91502909\"\n" +
                "                    data-pid=\"91502908\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n1-s.mafengwo.net/c_zoom,w_1360,q_90/s9/M00/A9/2F/wKgBs1aPH_eASUWZAAOWobkTsKs10.jpeg\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n1-s.mafengwo.net/c_zoom,w_680,q_90/s9/M00/A9/2F/wKgBs1aPH_eASUWZAAOWobkTsKs10.jpeg\"\n" +
                "                                             data-rt-src=\"https://n1-s.mafengwo.net/c_zoom,w_1360,q_90/s9/M00/A9/2F/wKgBs1aPH_eASUWZAAOWobkTsKs10.jpeg\"\n" +
                "                                             data-pid=\"91502908\"></h6>\n" +
                "\n" +
                "\n" +
                "                <h6 id=\"_j_photo_92428899\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92428903\"\n" +
                "                    data-pid=\"92428899\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://a2-q.mafengwo.net/s9/M00/53/0B/wKgBs1aXRCqAG9oqAAbAM9sWct039.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://a2-q.mafengwo.net/s9/M00/53/0B/wKgBs1aXRCqAG9oqAAbAM9sWct039.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://a2-q.mafengwo.net/s9/M00/53/0B/wKgBs1aXRCqAG9oqAAbAM9sWct039.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92428899\"></h6>\n" +
                "                <h6 id=\"_j_photo_92428900\" class=\"add_pic _j_anchorcnt _j_seqitem\" data-seq=\"92428904\"\n" +
                "                    data-pid=\"92428900\"><img class=\"_j_lazyload\"\n" +
                "                                             src=\"https://n4-q.mafengwo.net/s9/M00/53/0C/wKgBs1aXRCuAbWscAAShCgQvfPo16.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             alt=\"\"\n" +
                "                                             data-src=\"https://n4-q.mafengwo.net/s9/M00/53/0C/wKgBs1aXRCuAbWscAAShCgQvfPo16.jpeg?imageView2%2F2%2Fw%2F680%2Fq%2F90\"\n" +
                "                                             data-rt-src=\"https://n4-q.mafengwo.net/s9/M00/53/0C/wKgBs1aXRCuAbWscAAShCgQvfPo16.jpeg?imageView2%2F2%2Fw%2F1360%2Fq%2F90\"\n" +
                "                                             data-pid=\"92428900\"></h6>\n" +
                "                <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"92429009\">艾特我的summer先森~下站是哪里嘞？？伦家好期待~哇哈哈~\n" +
                "                    拜拜啦芭娜娜大芒GO!~拜拜啦我的摩托~拜拜啦我的蓝天白云~\n" +
                "                    ♪♪〓〓〓雾霾I'm&nbsp;Back~！！yo&nbsp;yo&nbsp;yo！〓〓〓♪♪♪♪</h6>\n" +
                "            </div>\n" +
                "            <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"92429009\"></h6>\n" +
                "            <h6 class=\"_j_note_content _j_seqitem\" data-seq=\"92429009\">感谢作者：小排排酱 (梵蒂冈)</h6></div>\n" +
                "    </div>\n" +
                "</div>";


//        webview.loadDataWithBaseURL(null, htmlData, "text/html", "utf-8", null);

        webview.loadDataWithBaseURL(local, HTMLConstants.head + center + HTMLConstants.footer, "text/html", "utf-8", null);


    }


}
