package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/17 13:47
 */
public class QueryMovieBean {

    /**
     * result : {"commentNum":16,"duration":"117分钟","imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","movieActor":[{"name":"徐峥","photo":"http://172.17.8.100/images/movie/actor/wbsys/xuzheng.jpg","role":"程勇"},{"name":"周一围","photo":"http://172.17.8.100/images/movie/actor/wbsys/zhouyiwei.jpg","role":"曹斌"},{"name":"王传君","photo":"http://172.17.8.100/images/movie/actor/wbsys/wangchuanjun.jpg","role":"吕受益"},{"name":"谭卓","photo":"http://172.17.8.100/images/movie/actor/wbsys/tanzhuo.jpg","role":"刘思惠"},{"name":"章宇","photo":"http://172.17.8.100/images/movie/actor/wbsys/zhangyu.jpg","role":"彭浩"},{"name":"杨新鸣","photo":"http://172.17.8.100/images/movie/actor/wbsys/yangxinming.jpg","role":"刘牧师"},{"name":"王砚辉","photo":"http://172.17.8.100/images/movie/actor/wbsys/wangyanhui.jpg","role":"张长林"}],"movieDirector":[{"name":"文牧野","photo":"http://172.17.8.100/images/movie/director/wbsys/1.jpg"}],"movieId":1,"movieType":"剧情 / 喜剧","name":"我不是药神","placeOrigin":"中国","posterList":["http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys4.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys5.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys6.jpg"],"releaseTime":1530720000000,"score":8.9,"shortFilmList":[{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp1.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp2.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys4.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp3.mp4"}],"summary":"一位不速之客的意外到访，打破了神油店老板程勇（徐峥 饰）的平凡人生，他从一个交不起房租的男性保健品商贩，一跃成为印度仿制药\u201c格列宁\u201d的独家代理商。收获巨额利润的他，生活剧烈变化，被病患们冠以\u201c药神\u201d的称号。但是，一场关于救赎的拉锯战也在波涛暗涌中慢慢展开......","whetherFollow":2}
     * message : 查询成功
     * status : 0000
     */

    public ResultBean result;
    public String message;
    public String status;

    public static class ResultBean {
        /**
         * commentNum : 16
         * duration : 117分钟
         * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg
         * movieActor : [{"name":"徐峥","photo":"http://172.17.8.100/images/movie/actor/wbsys/xuzheng.jpg","role":"程勇"},{"name":"周一围","photo":"http://172.17.8.100/images/movie/actor/wbsys/zhouyiwei.jpg","role":"曹斌"},{"name":"王传君","photo":"http://172.17.8.100/images/movie/actor/wbsys/wangchuanjun.jpg","role":"吕受益"},{"name":"谭卓","photo":"http://172.17.8.100/images/movie/actor/wbsys/tanzhuo.jpg","role":"刘思惠"},{"name":"章宇","photo":"http://172.17.8.100/images/movie/actor/wbsys/zhangyu.jpg","role":"彭浩"},{"name":"杨新鸣","photo":"http://172.17.8.100/images/movie/actor/wbsys/yangxinming.jpg","role":"刘牧师"},{"name":"王砚辉","photo":"http://172.17.8.100/images/movie/actor/wbsys/wangyanhui.jpg","role":"张长林"}]
         * movieDirector : [{"name":"文牧野","photo":"http://172.17.8.100/images/movie/director/wbsys/1.jpg"}]
         * movieId : 1
         * movieType : 剧情 / 喜剧
         * name : 我不是药神
         * placeOrigin : 中国
         * posterList : ["http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys4.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys5.jpg","http://172.17.8.100/images/movie/stills/wbsys/wbsys6.jpg"]
         * releaseTime : 1530720000000
         * score : 8.9
         * shortFilmList : [{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp1.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys3.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp2.ts"},{"imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys4.jpg","videoUrl":"http://172.17.8.100/video/movie/wbsys/wbsysygp3.mp4"}]
         * summary : 一位不速之客的意外到访，打破了神油店老板程勇（徐峥 饰）的平凡人生，他从一个交不起房租的男性保健品商贩，一跃成为印度仿制药“格列宁”的独家代理商。收获巨额利润的他，生活剧烈变化，被病患们冠以“药神”的称号。但是，一场关于救赎的拉锯战也在波涛暗涌中慢慢展开......
         * whetherFollow : 2
         */

        public int commentNum;
        public String duration;
        public String imageUrl;
        public int movieId;
        public String movieType;
        public String name;
        public String placeOrigin;
        public long releaseTime;
        public double score;
        public String summary;
        public int whetherFollow;
        public List<MovieActorBean> movieActor;
        public List<MovieDirectorBean> movieDirector;
        public List<String> posterList;
        public List<ShortFilmListBean> shortFilmList;

        public static class MovieActorBean {
            /**
             * name : 徐峥
             * photo : http://172.17.8.100/images/movie/actor/wbsys/xuzheng.jpg
             * role : 程勇
             */

            public String name;
            public String photo;
            public String role;
        }

        public static class MovieDirectorBean {
            /**
             * name : 文牧野
             * photo : http://172.17.8.100/images/movie/director/wbsys/1.jpg
             */

            public String name;
            public String photo;
        }

        public static class ShortFilmListBean {
            /**
             * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys2.jpg
             * videoUrl : http://172.17.8.100/video/movie/wbsys/wbsysygp1.ts
             */

            public String imageUrl;
            public String videoUrl;
        }
    }
}
