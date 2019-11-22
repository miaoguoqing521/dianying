package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 16:22
 */
public class AttentMovieBean {

    /**
     * result : [{"director":"黄渤","imageUrl":"http://172.17.8.100/images/movie/stills/ychx/ychx1.jpg","movieId":13,"name":"一出好戏","score":9.6,"starring":"黄渤,舒淇,王宝强,张艺兴"},{"director":"刘阔","imageUrl":"http://172.17.8.100/images/movie/stills/fyz/fyz1.jpg","movieId":12,"name":"风语咒","score":9.7,"starring":"路知行,阎萌萌,褚珺"},{"director":"乔·德特杜巴","imageUrl":"http://172.17.8.100/images/movie/stills/jcs/jcs1.jpg","movieId":11,"name":"巨齿鲨","score":9.6,"starring":"杰森·斯坦森,李冰冰,雷恩·威尔森"},{"director":"韦正","imageUrl":"http://172.17.8.100/images/movie/stills/aqgy/aqgy1.jpg","movieId":15,"name":"爱情公寓","score":9.2,"starring":"陈赫,娄艺潇,邓家佳,孙艺洲"},{"director":"罗森·马歇尔·瑟伯","imageUrl":"http://172.17.8.100/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","score":8.5,"starring":"道恩·强森,昆凌,文峰,黄经汉"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director : 黄渤
         * imageUrl : http://172.17.8.100/images/movie/stills/ychx/ychx1.jpg
         * movieId : 13
         * name : 一出好戏
         * score : 9.6
         * starring : 黄渤,舒淇,王宝强,张艺兴
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "director='" + director + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", movieId=" + movieId +
                    ", name='" + name + '\'' +
                    ", score=" + score +
                    ", starring='" + starring + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AttentMovieBean{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
