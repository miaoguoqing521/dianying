package com.bw.movie.model.bean;

import java.util.List;

/**
 * 作者： 姓名
 * 日期： 2019/10/21 13:50
 */
public class SearchBean {

    /**
     * result : [{"director":"文牧野","imageUrl":"http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg","movieId":1,"name":"我不是药神","score":8.9,"starring":"徐峥,周一围,王传君,谭卓,章宇,杨新鸣,王砚辉"}]
     * message : 查询成功
     * status : 0000
     */

    public String message;
    public String status;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * director : 文牧野
         * imageUrl : http://172.17.8.100/images/movie/stills/wbsys/wbsys1.jpg
         * movieId : 1
         * name : 我不是药神
         * score : 8.9
         * starring : 徐峥,周一围,王传君,谭卓,章宇,杨新鸣,王砚辉
         */

        public String director;
        public String imageUrl;
        public int movieId;
        public String name;
        public double score;
        public String starring;
    }
}
