package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/16 20:06
 */
public class BookMovieBean {

    /**
     * result : [{"imageUrl":"http://172.17.8.100/images/movie/stills/hjxd/hjxd1.jpg","movieId":18,"name":"黄金兄弟","releaseTime":1537545600000,"wantSeeNum":2},{"imageUrl":"http://172.17.8.100/images/movie/stills/mtyj/mtyj1.jpg","movieId":2,"name":"摩天营救","releaseTime":1595174400000,"wantSeeNum":12},{"imageUrl":"http://172.17.8.100/images/movie/stills/drjzsdtw/drjzsdtw1.jpg","movieId":4,"name":"狄仁杰之四大天王","releaseTime":1598457600000,"wantSeeNum":22}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * imageUrl : http://172.17.8.100/images/movie/stills/hjxd/hjxd1.jpg
         * movieId : 18
         * name : 黄金兄弟
         * releaseTime : 1537545600000
         * wantSeeNum : 2
         */

        public String imageUrl;
        public int movieId;
        public String name;
        public long releaseTime;
        public int wantSeeNum;
    }
}
